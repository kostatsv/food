//package com.example.food.ui;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.util.StringUtils;
//
//import com.example.food.backend.domain.Customer;
//import com.example.food.backend.repositories.CustomerRepository;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.icon.VaadinIcon;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//
//@Route
//public class MainViewOld extends VerticalLayout {
//
//  private static final long serialVersionUID = 1L;
//
//  private final CustomerRepository repo;
//
//  // private final CustomerEditor editor;
//
//  final Grid<Customer> grid;
//
//  final TextField filter; 
//
//  private final Button addNewBtn;
//  
//  final DatePicker calendar;
//  final TextField txtMonth; 
//
//  public MainViewOld(CustomerRepository repo, CustomerEditor editor) {
//    this.repo = repo;
//    // this.editor = editor;
//    this.grid = new Grid<>(Customer.class);
//    this.filter = new TextField();    
//    this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
//    
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");    
//    this.calendar = new DatePicker();
//    this.txtMonth = new TextField();    
//    this.txtMonth.setReadOnly(true);
//    this.txtMonth.setValue(LocalDate.now().format(dtf));
//
//    // build layout
//    HorizontalLayout actions = new HorizontalLayout(calendar, txtMonth, addNewBtn);
//    add(actions, grid, editor);
//
//    grid.setHeight("300px");
//    grid.setColumns("id", "firstName", "lastName");
//    grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
//
//    filter.setPlaceholder("Filter by last name");
//
//    // Hook logic to components
//
//    // Replace listing with filtered content when user changes filter
//    // filter.setValueChangeMode(ValueChangeMode.EAGER);
//    // filter.addValueChangeListener(e -> listCustomers(e.getValue()));
//
//    // Connect selected Customer to editor or hide if none is selected
//    grid.asSingleSelect().addValueChangeListener(e -> {
//      editor.editCustomer(e.getValue());
//    });
//
//    // Instantiate and edit new Customer the new button is clicked
//    addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));
//
//    // Listen changes made by the editor, refresh data from backend
//    editor.setChangeHandler(() -> {
//      editor.setVisible(false);
//      //listCustomers(filter.getValue());
//    });
//    
//    
//    calendar.setValue(LocalDate.now());
//    calendar.addValueChangeListener(e -> {   
//      
//      if(e.getValue() != null) {
//        txtMonth.setValue(e.getValue().format(dtf));
//        listCustomers(e.getValue());
//      } else {
//        txtMonth.clear();
//      }
//      
//    });
//
//    // Initialize listing
//    listCustomers(null);
//  }
//
//  // tag::listCustomers[]
////  void listCustomers(String filterText) {
////    if (StringUtils.isEmpty(filterText)) {
////      grid.setItems(repo.findAll());
////    }
////    else {
////      grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
////    }
////  }
//  
//  void listCustomers(LocalDate filterText) {
//    if (StringUtils.isEmpty(filterText)) {
//      grid.setItems(repo.findAll());
//    }
//    else {
//      // grid.setItems(repo.findAllByCreated(filterText));
//      // grid.setItems(repo.findAllByCreatedBetween(filterText.withDayOfMonth(1), filterText.withDayOfMonth(filterText.lengthOfMonth())));
//    }
//  }  
//  // end::listCustomers[]
//}
