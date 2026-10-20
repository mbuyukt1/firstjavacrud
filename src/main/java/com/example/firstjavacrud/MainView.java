package com.example.firstjavacrud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final CustomerRepository repository;
    private final Grid<Customer> grid = new Grid<>(Customer.class);
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final Button save = new Button("Save Customer");

    public MainView(CustomerRepository repository) {
        this.repository = repository;

        grid.setColumns("id", "firstName", "lastName");
        updateList();

        save.addClickListener(e -> {
            repository.save(new Customer(firstName.getValue(), lastName.getValue()));
            firstName.clear();
            lastName.clear();
            updateList();
        });

        add(grid, firstName, lastName, save);
    }

    private void updateList() {
        grid.setItems(repository.findAll());
    }
}