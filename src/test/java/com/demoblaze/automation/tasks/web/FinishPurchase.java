package com.demoblaze.automation.tasks.web;

import com.demoblaze.automation.ui.pages.CheckoutPageTargets;
import com.demoblaze.automation.ui.pages.OrderConfirmationPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FinishPurchase implements Task {

    public static FinishPurchase successfully() {
        return Tasks.instrumented(FinishPurchase.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(CheckoutPageTargets.PURCHASE_BUTTON, isVisible())
                .forNoMoreThan(10).seconds(),
            JavaScriptClick.on(CheckoutPageTargets.PURCHASE_BUTTON),
            WaitUntil.the(OrderConfirmationPageTargets.CONFIRMATION_TITLE, isVisible())
                .forNoMoreThan(15).seconds()
        );
    }
}
