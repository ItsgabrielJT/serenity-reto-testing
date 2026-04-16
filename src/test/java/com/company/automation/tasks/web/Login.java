package com.company.automation.tasks.web;

import com.company.automation.ui.pages.LoginPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Login implements Task {

    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Login withCredentials(String username, String password) {
        return Tasks.instrumented(Login.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Enter.theValue(username).into(LoginPageTargets.USERNAME_FIELD),
            Enter.theValue(password).into(LoginPageTargets.PASSWORD_FIELD),
            Click.on(LoginPageTargets.LOGIN_BUTTON)
        );
    }
}
