package com.telros.interview.ui.views.list;

import com.telros.interview.backend.entities.Product;
import com.telros.interview.backend.service.ProductService;
import com.telros.interview.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class)
@PageTitle("Products | Telros Company")
public class ListView extends VerticalLayout {

    private final ProductForm form;
    Grid<Product> grid = new Grid<>(Product.class);
    private ProductService productService;

    public ListView(ProductService productService) {
        this.productService = productService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        form = new ProductForm(productService.findAll());

        form.addListener(ProductForm.CloseEvent.class, e-> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        updateList();

        add(content);
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name","price","description");
        grid.getColumns().forEach(item -> item.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(item -> editProduct(item.getValue()));
    }

    private void editProduct(Product product) {
        if(product == null) {
            closeEditor();
        } else {
            form.setProduct(product);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(productService.findAll());
    }
}
