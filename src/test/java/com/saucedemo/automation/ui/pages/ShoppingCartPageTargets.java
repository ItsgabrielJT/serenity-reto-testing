package com.saucedemo.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ShoppingCartPageTargets {
    public static final Target CHECKOUT_BUTTON = Target.the("Checkout button").located(By.cssSelector("[data-test='checkout']"));
}
