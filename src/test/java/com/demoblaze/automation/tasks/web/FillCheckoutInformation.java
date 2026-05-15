package com.demoblaze.automation.tasks.web;

import com.demoblaze.automation.models.PurchaseOrder;
import com.demoblaze.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillCheckoutInformation implements Task {

    private final PurchaseOrder order;

    public FillCheckoutInformation(PurchaseOrder order) {
        this.order = order;
    }

    public static FillCheckoutInformation withDetails(PurchaseOrder order) {
        return Tasks.instrumented(FillCheckoutInformation.class, order);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(CheckoutPageTargets.NAME_FIELD, isVisible())
                .forNoMoreThan(10).seconds(),
            Enter.theValue(order.getName()).into(CheckoutPageTargets.NAME_FIELD),
            Enter.theValue(order.getCountry()).into(CheckoutPageTargets.COUNTRY_FIELD),
            Enter.theValue(order.getCity()).into(CheckoutPageTargets.CITY_FIELD),
            Enter.theValue(order.getCreditCard()).into(CheckoutPageTargets.CREDIT_CARD_FIELD),
            Enter.theValue(order.getMonth()).into(CheckoutPageTargets.MONTH_FIELD),
            Enter.theValue(order.getYear()).into(CheckoutPageTargets.YEAR_FIELD)
        );
    }
}
