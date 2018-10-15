package com.example.food.ui.views.mainpage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.util.StringUtils;

import com.example.food.app.security.SecurityUtils;
import com.example.food.backend.domain.Receipt;
import com.example.food.backend.repositories.ReceiptRepository;
import com.example.food.ui.utils.BakeryConst;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Tag("receipts-view")
@PageTitle(BakeryConst.TITLE_STOREFRONT)
@Route(value = BakeryConst.PAGE_STOREFRONT)
public class ReceiptsView extends VerticalLayout {

  private static final long serialVersionUID = 1L;

  private final Grid<Receipt> grid;
  private final DatePicker calendar;
  private final TextField txtMonth;
  private final TextField txtTotalAmount;
  private final TextField txtPersonalExpenses;
  private final TextField txtAmountEach;
  private final Button addNewBtn;
  private final Button btnLogout;
  private ReceiptEditor receiptEditor;
  private ReceiptRepository receiptRepo;

  public ReceiptsView(ReceiptRepository receiptRepo, ReceiptEditor editor) {
    this.receiptRepo = receiptRepo;
    this.receiptEditor = editor;
    this.grid = new Grid<>();
    this.addNewBtn = new Button("New receipt", VaadinIcon.PLUS.create());
    
    this.btnLogout = new Button("logout", VaadinIcon.EXIT.create(), e -> {
      getUI().get().getSession().close();
      getUI().get().getPage().executeJavaScript("window.location.href='"+ BakeryConst.PAGE_LOGIN +"'");
    });
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
    this.calendar = new DatePicker();
    this.txtMonth = new TextField();
    this.txtMonth.setReadOnly(true);
    this.txtMonth.setValue(LocalDate.now().format(dtf));

    this.txtTotalAmount = new TextField();
    this.txtTotalAmount.setReadOnly(true);
    this.txtTotalAmount.setLabel("Total for the month");

    this.txtPersonalExpenses = new TextField();
    this.txtPersonalExpenses.setReadOnly(true);
    this.txtPersonalExpenses.setLabel("Personal Expenses");
    
    this.txtAmountEach = new TextField();
    this.txtAmountEach.setReadOnly(true);
    this.txtAmountEach.setLabel("Each");
    
    setSpacing(true);

    // build layout
    HorizontalLayout actions;
    if (SecurityUtils.isAdmin()) {
      actions = new HorizontalLayout(calendar, txtMonth, addNewBtn, btnLogout);
    }
    else {
      actions = new HorizontalLayout(calendar, txtMonth, btnLogout);
    }
    add(actions, grid, txtTotalAmount, txtPersonalExpenses, txtAmountEach, receiptEditor);

    grid.setHeight("300px");
    // grid.setWidth("800px");
    //grid.setColumns("receiptDate", "amount");

    grid.addColumn(new LocalDateRenderer<>(Receipt::getReceiptDate, "dd/MM/yyyy"))
            .setHeader("Receipt Date").setWidth("200px");

    grid.addColumn(Receipt::getStore).setHeader("Store").setWidth("270px");

    grid.addColumn(new NumberRenderer<>(Receipt::getAmount, "£%(,.2f",
            Locale.UK, "£0.00")).setHeader("Amount").setWidth("200px");

    grid.addColumn(new NumberRenderer<>(Receipt::getPersonalExpenses, "£%(,.2f",
            Locale.UK, "£0.00")).setHeader("Personal Expenses").setWidth("200px");

    if(SecurityUtils.isAdmin()) {
      // Connect selected Customer to editor or hide if none is selected
      grid.asSingleSelect().addValueChangeListener(e -> {
        receiptEditor.editReceipt(e.getValue());
      });
    }

    // Instantiate and edit new Customer the new button is clicked
    addNewBtn.addClickListener(e -> receiptEditor.editReceipt(new Receipt(LocalDate.now(), new BigDecimal(0))));

    editor.setChangeHandler(() -> {
      editor.setVisible(false);
      listReceipts(calendar.getValue());
    });

    calendar.setValue(LocalDate.now());
    calendar.addValueChangeListener(e -> {
      if (e.getValue() != null) {
        txtMonth.setValue(e.getValue().format(dtf));
        listReceipts(e.getValue());
      }
      else {
        txtMonth.clear();
      }
    });

    listReceipts(LocalDate.now());
  }

  void listReceipts(LocalDate filterDate) {
    List<Receipt> receipts = new ArrayList<>();
    if (StringUtils.isEmpty(filterDate)) {
      // grid.setItems(receiptRepo.findAll());
      receipts = receiptRepo.findAll();
    }
    else {
      // grid.setItems(receiptRepo.findAllByReceiptDateBetween(filterDate.withDayOfMonth(1),
      // filterDate.withDayOfMonth(filterDate.lengthOfMonth())));

      receipts = receiptRepo.findAllByReceiptDateBetween(filterDate.withDayOfMonth(1),
                                                         filterDate.withDayOfMonth(filterDate.lengthOfMonth()));
    }

    grid.setItems(receipts);
    BigDecimal sum = new BigDecimal(0);
    BigDecimal sumPersonalExpenses = new BigDecimal(0);
    
    for(Receipt r : receipts) {
      sum = sum.add(r.getAmount());
      sumPersonalExpenses = sumPersonalExpenses.add(r.getPersonalExpenses());
    }
    
    this.txtTotalAmount.setValue(sum.toString());
    this.txtAmountEach.setValue(sum.subtract(sumPersonalExpenses).divide(new BigDecimal(2)).toString());
    this.txtPersonalExpenses.setValue(sumPersonalExpenses.toString());
  }

}
