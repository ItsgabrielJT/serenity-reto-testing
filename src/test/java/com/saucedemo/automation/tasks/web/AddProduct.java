package com.saucedemo.automation.tasks.web;

import com.saucedemo.automation.ui.pages.InventoryPageTargets;
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
            net.serenitybdd.screenplay.waits.WaitUntil.the(
                InventoryPageTargets.addToCartButton(productName), 
                net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible()
            ).forNoMoreThan(10).seconds(),
            net.serenitybdd.screenplay.actions.Click.on(InventoryPageTargets.addToCartButton(productName))
        );
    }
}
