package com.saucedemo.automation.tasks.web;

import com.saucedemo.automation.ui.pages.ShoppingCartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ProceedToCheckout implements Task {

    public static ProceedToCheckout now() {
        return Tasks.instrumented(ProceedToCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(ShoppingCartPageTargets.CHECKOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
            net.serenitybdd.screenplay.actions.JavaScriptClick.on(ShoppingCartPageTargets.CHECKOUT_BUTTON)
        );
    }
}
