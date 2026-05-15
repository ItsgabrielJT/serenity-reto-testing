package com.demoblaze.automation.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePageTargets {

    public static Target productLink(String productName) {
        return Target.the("Product link for " + productName)
            .located(By.xpath(
                "//h4[@class='card-title']/a[normalize-space(text())='" + productName + "']"
            ));
    }
}
