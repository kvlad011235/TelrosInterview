package com.telros.interview.ui.views.list;

import com.telros.interview.backend.entities.Product;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import java.util.List;

public class ProductForm extends FormLayout {

  TextField name = new TextField("name");
  IntegerField price = new IntegerField("price, $");
  TextField description = new TextField("description");
  Button choose = new Button("Choose");
  Button close = new Button("Cancel");

  Binder<Product> binder = new BeanValidationBinder<>(Product.class);


  public ProductForm(List<Product> products) {
    addClassName("contact-form");
    binder.bindInstanceFields(this);

    name.setReadOnly(true);
    price.setReadOnly(true);
    description.setReadOnly(true);
    add(name, price, description, createButtonsLayout());
  }


  public void setProduct(Product product) {
    binder.setBean(product);
  }

  private HorizontalLayout createButtonsLayout() {
    choose.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    choose.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    choose.addClickListener(event -> UI.getCurrent().getPage().setLocation("/checkout-form"));
    close.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));

    return new HorizontalLayout(choose, close);
  }



  public static abstract class ProductFormEvent extends ComponentEvent<ProductForm> {

    private Product product;

    protected ProductFormEvent(ProductForm source, Product product) {
      super(source, false);
      this.product = product;
    }

    public Product getContact() {
      return product;
    }
  }


  public static class CloseEvent extends ProductFormEvent {
    CloseEvent(ProductForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }

}
