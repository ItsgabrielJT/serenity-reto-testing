package com.demoblaze.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ShoppingCartPageTargets {

    public static final Target PLACE_ORDER_BUTTON = Target.the("Place Order button")
        .located(By.cssSelector("button[data-target='#orderModal']"));

    public static final Target CART_ITEMS_TABLE = Target.the("Cart items table")
        .located(By.id("tbodyid"));
}
