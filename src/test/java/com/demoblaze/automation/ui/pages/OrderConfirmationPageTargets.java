package com.demoblaze.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class OrderConfirmationPageTargets {

    public static final Target CONFIRMATION_TITLE = Target.the("Confirmation title")
        .located(By.cssSelector(".sweet-alert h2"));

    public static final Target CONFIRM_OK_BUTTON = Target.the("Confirm OK button")
        .located(By.cssSelector(".sweet-alert .confirm"));
}
