package com.example.food.ui.views.mainpage;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.food.backend.domain.Receipt;
import com.example.food.backend.repositories.ReceiptRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.validator.BeanValidator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

/**
 * A simple example to introduce building forms. As your real application is probably much more complicated than this
 * example, you could re-use this form in multiple places. This example component is only used in MainView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class ReceiptEditor extends VerticalLayout implements KeyNotifier {

  @Autowired
  ReceiptRepository receiptRepo;

  private static final long serialVersionUID = 1L;

  /**
   * The currently edited receipt
   */
  private Receipt receipt;

  /* Fields to edit properties in Customer entity */
  DatePicker receiptDate = new DatePicker("Date");
  TextField amount = new TextField("Amount");
  TextField store = new TextField("Store");
  TextField personalExpenses = new TextField("Personal Expenses");

  /* Action buttons */
  // TODO why more code?
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

  Binder<Receipt> binder = new Binder<>(Receipt.class);
  private ChangeHandler changeHandler;

  public ReceiptEditor() {

    add(receiptDate, store, amount, personalExpenses, actions);

    // bind using naming convention
    // binder.bindInstanceFields(this);
    binder.forField(receiptDate)
      .withValidator(new BeanValidator(Receipt.class, "receiptDate"))
      .bind("receiptDate");

    binder.forField(store)
            .withValidator(new BeanValidator(Receipt.class, "store"))
            .bind("store");

    binder.forField(amount)
            .withValidator(new BeanValidator(Receipt.class, "amount"))
            .withConverter(new StringToBigDecimalConverter("Incorrect value for amount field"))
            .bind("amount");

    binder.forField(personalExpenses)
            .withValidator(new BeanValidator(Receipt.class, "personalExpenses"))
            .withConverter(new StringToBigDecimalConverter("Incorrect value for amount field"))
            .bind("personalExpenses");

    // Configure and style components
    setSpacing(true);
    
    amount.setPattern("#0.00");
    personalExpenses.setPattern("#0.00");

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    // wire action buttons to save, delete and reset
    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editReceipt(receipt));
    setVisible(false);
  }

  void delete() {
    receiptRepo.delete(receipt);
    changeHandler.onChange();
  }

  void save() {
    receiptRepo.save(receipt);
    changeHandler.onChange();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editReceipt(Receipt r) {
    if (r == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = r.getPk() != null;
    if (persisted) {
      // Find fresh entity for editing
      receipt = receiptRepo.findById(r.getPk()).get();
    }
    else {
      receipt = r;
    }
    cancel.setVisible(persisted);

    // Bind receipt properties to similarly named fields
    // Could also use annotation or "manual binding" or programmatically
    // moving values from fields to entities before saving
    binder.setBean(receipt);

    setVisible(true);

    // Focus first name initially
    amount.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    // ChangeHandler is notified when either save or delete
    // is clicked
    changeHandler = h;
  }

}
