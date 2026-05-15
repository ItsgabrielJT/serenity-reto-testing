package com.demoblaze.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPageTargets {

    public static final Target NAME_FIELD        = Target.the("Name field")        .located(By.id("name"));
    public static final Target COUNTRY_FIELD     = Target.the("Country field")     .located(By.id("country"));
    public static final Target CITY_FIELD        = Target.the("City field")        .located(By.id("city"));
    public static final Target CREDIT_CARD_FIELD = Target.the("Credit card field") .located(By.id("card"));
    public static final Target MONTH_FIELD       = Target.the("Month field")       .located(By.id("month"));
    public static final Target YEAR_FIELD        = Target.the("Year field")        .located(By.id("year"));

    public static final Target PURCHASE_BUTTON = Target.the("Purchase button")
        .located(By.cssSelector("button[onclick='purchaseOrder()']"));
}
