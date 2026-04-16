package com.saucedemo.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPageTargets {
    public static final Target SHOPPING_CART_LINK = Target.the("Shopping cart link").locatedBy("[data-test='shopping-cart-link']");
    
    public static Target addToCartButton(String productName) {
        // Convertir nombre del producto a formato kebab-case para el data-test
        String kebabCaseProduct = productName.toLowerCase()
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\\.", "")
                .replaceAll("\\s+", "-");
        return Target.the("Add to cart button for " + productName)
                .locatedBy("[data-test='add-to-cart-" + kebabCaseProduct + "']");
    }
}
