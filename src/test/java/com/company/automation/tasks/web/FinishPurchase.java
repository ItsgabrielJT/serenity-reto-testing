package com.company.automation.tasks.web;

import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

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
