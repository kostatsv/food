package com.example.food.ui.views.dashboard;

import com.example.food.backend.repositories.ReceiptRepository;
import com.example.food.ui.utils.BakeryConst;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Tag("dashboard-view")
@HtmlImport("src/views/dashboard/dashboard-view.html")
@PageTitle(BakeryConst.TITLE_STOREFRONT)
@Route(value = BakeryConst.PAGE_ROOT)
public class DashboardView extends VerticalLayout {

    @Id("month")
    private final H3 month;

    private final String totalAmount = "£0.00";
    private final String personalExpenses = "£0.00";
    private final String amountEach = "£0.00";

    public DashboardView(ReceiptRepository repo) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
        this.month = new H3();
        this.month.setText(LocalDate.now().format(dtf));

    }

}
