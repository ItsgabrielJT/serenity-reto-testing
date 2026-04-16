package com.company.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPageTargets {
    public static final Target ADD_TO_CART_BUTTON = Target.the("Add to cart button").locatedBy("//div[text()='{0}']/ancestor::div[@class='inventory_item_description']//button");
    public static final Target SHOPPING_CART_LINK = Target.the("Shopping cart link").locatedBy("[data-test='shopping-cart-link']");
}
