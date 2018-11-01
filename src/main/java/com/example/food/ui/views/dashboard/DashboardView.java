package com.example.food.ui.views.dashboard;

import com.example.food.backend.domain.Receipt;
import com.example.food.backend.repositories.ReceiptRepository;
import com.example.food.ui.MainView;
import com.example.food.ui.service.ReceiptService;
import com.example.food.ui.utils.BakeryConst;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Tag("dashboard-view")
@HtmlImport("src/views/dashboard/dashboard-view.html")
@PageTitle(BakeryConst.TITLE_STOREFRONT)
@Route(value = BakeryConst.PAGE_ROOT)
public class DashboardView extends PolymerTemplate<TemplateModel> {

    @Id("month")
    private Element month;

    @Id("totalAmount")
    private Element totalAmount;

    @Id("amountEach")
    private Element amountEach;

    public DashboardView(ReceiptService receiptService) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
        LocalDate currentDate = LocalDate.now();
        this.month.setText(currentDate.format(dtf));

//        List<Receipt> receipts = repo.findAllByReceiptDateBetween(currentDate.withDayOfMonth(1),
//                currentDate.withDayOfMonth(currentDate.lengthOfMonth()));

        List<Receipt> receipts = receiptService.getReceipts(currentDate);

        BigDecimal sum = new BigDecimal(0);
        BigDecimal sumPersonalExpenses = new BigDecimal(0);

        for(Receipt r : receipts) {
            sum = sum.add(r.getAmount());
            sumPersonalExpenses = sumPersonalExpenses.add(r.getPersonalExpenses());
        }

        this.totalAmount.setText("£ " + sum.toString());
        this.amountEach.setText("£ " + sum.subtract(sumPersonalExpenses).divide(new BigDecimal(2)).toString());

    }

}
