package com.saucedemo.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPageTargets {
    public static final Target FIRST_NAME_FIELD = Target.the("First name").located(By.cssSelector("[data-test='firstName']"));
    public static final Target LAST_NAME_FIELD = Target.the("Last name").located(By.cssSelector("[data-test='lastName']"));
    public static final Target ZIP_CODE_FIELD = Target.the("Zip code").located(By.cssSelector("[data-test='postalCode']"));
    public static final Target CONTINUE_BUTTON = Target.the("Continue button").located(By.cssSelector("[data-test='continue']"));
    public static final Target FINISH_BUTTON = Target.the("Finish button").located(By.id("finish"));
    public static final Target ERROR_MESSAGE = Target.the("Error message").located(By.className("error-message-container"));
}
