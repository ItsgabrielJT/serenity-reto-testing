package com.demoblaze.automation.tasks.web;

import com.demoblaze.automation.ui.pages.HomePageTargets;
import com.demoblaze.automation.ui.pages.ProductPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

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
            Open.url("https://www.demoblaze.com/"),
            WaitUntil.the(HomePageTargets.productLink(productName), isVisible())
                .forNoMoreThan(15).seconds(),
            Click.on(HomePageTargets.productLink(productName)),
            WaitUntil.the(ProductPageTargets.ADD_TO_CART_BUTTON, isVisible())
                .forNoMoreThan(10).seconds(),
            Click.on(ProductPageTargets.ADD_TO_CART_BUTTON),
            AcceptTheAlert.ifPresent()
        );
    }
}
