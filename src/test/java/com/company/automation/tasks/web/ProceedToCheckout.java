package com.company.automation.tasks.web;

import com.company.automation.ui.pages.ShoppingCartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ProceedToCheckout implements Task {

    public static ProceedToCheckout now() {
        return Tasks.instrumented(ProceedToCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(ShoppingCartPageTargets.CHECKOUT_BUTTON, isClickable()).forNoMoreThan(10).seconds(),
            net.serenitybdd.screenplay.actions.JavaScriptClick.on(ShoppingCartPageTargets.CHECKOUT_BUTTON)
        );
    }
}
