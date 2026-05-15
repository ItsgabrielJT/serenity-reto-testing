package com.demoblaze.automation.tasks.web;

import com.demoblaze.automation.ui.pages.ShoppingCartPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ProceedToCheckout implements Task {

    public static ProceedToCheckout now() {
        return Tasks.instrumented(ProceedToCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(ShoppingCartPageTargets.PLACE_ORDER_BUTTON, isVisible())
                .forNoMoreThan(10).seconds(),
            JavaScriptClick.on(ShoppingCartPageTargets.PLACE_ORDER_BUTTON)
        );
    }
}
