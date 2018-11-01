package com.example.food.ui.views.receipts;

import com.example.food.backend.domain.Receipt;
import com.example.food.ui.components.FormButtonsBar;
import com.example.food.ui.crud.CrudView;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.validator.BeanValidator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Tag("receipt-form")
@HtmlImport("src/views/receipts/receipt-form.html")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReceiptForm extends PolymerTemplate<TemplateModel> implements CrudView.CrudForm<Receipt> {

    @Id("title")
    private H3 title;

    @Id("buttons")
    private FormButtonsBar buttons;

    @Id("receiptDate")
    private final DatePicker receiptDate = new DatePicker("Date");

    @Id("amount")
    private final TextField amount = new TextField("Amount");

    @Id("store")
    private final TextField store = new TextField("Store");

    @Id("personalExpenses")
    private final TextField personalExpenses = new TextField("Personal Expenses");

    @Autowired
    public ReceiptForm() {
    }

    @Override
    public HasText getTitle() {
        return this.title;
    }

    @Override
    public void setBinder(BeanValidationBinder<Receipt> binder) {
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
    }

    @Override
    public FormButtonsBar getButtons() {
        return buttons;
    }
}
