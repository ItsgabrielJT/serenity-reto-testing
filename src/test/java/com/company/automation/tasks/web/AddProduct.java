package com.company.automation.tasks.web;

import com.company.automation.ui.pages.InventoryPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;

public class AddProduct implements Task {

    private final String productName;

    public AddProduct(String productName) {
        this.productName = productName;
    }

    public static AddProduct toCart(String productName) {
        return Tasks.instrumented(AddProduct.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            net.serenitybdd.screenplay.waits.WaitUntil.the(InventoryPageTargets.ADD_TO_CART_BUTTON.of(productName), net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
            Click.on(InventoryPageTargets.ADD_TO_CART_BUTTON.of(productName))
        );
    }
}
