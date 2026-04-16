package com.company.automation.tasks.web;

import com.company.automation.models.Customer;
import com.company.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

public class FillCheckoutInformation implements Task {

    private final Customer customer;

    public FillCheckoutInformation(Customer customer) {
        this.customer = customer;
    }

    public static FillCheckoutInformation withDetails(Customer customer) {
        return Tasks.instrumented(FillCheckoutInformation.class, customer);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(CheckoutPageTargets.FIRST_NAME_FIELD, WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),
            Enter.theValue(customer.getFirstName()).into(CheckoutPageTargets.FIRST_NAME_FIELD),
            Enter.theValue(customer.getLastName()).into(CheckoutPageTargets.LAST_NAME_FIELD),
            Enter.theValue(customer.getZipCode()).into(CheckoutPageTargets.ZIP_CODE_FIELD),
            net.serenitybdd.screenplay.actions.JavaScriptClick.on(CheckoutPageTargets.CONTINUE_BUTTON)
        );
    }
}
