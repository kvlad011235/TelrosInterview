package com.telros.interview.ui.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Telros Company")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

  LoginForm loginForm = new LoginForm();

  public LoginView() {
      addClassName("login-view");
      setSizeFull();

      setJustifyContentMode(JustifyContentMode.CENTER);
      setAlignItems(Alignment.CENTER);

      loginForm.setAction("login");

    add(
          new H1("Telros Company greets you"),
          loginForm
      );
  }


  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    if(beforeEnterEvent.getLocation()
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
      loginForm.setError(true);
    }
  }


}
