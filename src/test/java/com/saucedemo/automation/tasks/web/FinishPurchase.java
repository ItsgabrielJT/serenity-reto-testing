package com.saucedemo.automation.tasks.web;

import com.saucedemo.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class FinishPurchase implements Task {

    public static FinishPurchase successfully() {
        return Tasks.instrumented(FinishPurchase.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            net.serenitybdd.screenplay.waits.WaitUntil.the(CheckoutPageTargets.FINISH_BUTTON, net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),
            net.serenitybdd.screenplay.actions.JavaScriptClick.on(CheckoutPageTargets.FINISH_BUTTON)
        );
    }
}
