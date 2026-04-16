package com.saucedemo.automation.tasks.web;

import com.saucedemo.automation.ui.pages.InventoryPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateTo implements Task {

    public static NavigateTo shoppingCart() {
        return Tasks.instrumented(NavigateTo.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(InventoryPageTargets.SHOPPING_CART_LINK, isVisible()).forNoMoreThan(10).seconds(),
            net.serenitybdd.screenplay.actions.JavaScriptClick.on(InventoryPageTargets.SHOPPING_CART_LINK)
        );
    }
}
