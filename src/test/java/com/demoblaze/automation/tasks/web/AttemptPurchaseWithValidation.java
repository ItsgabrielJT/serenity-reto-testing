package com.demoblaze.automation.tasks.web;

import com.demoblaze.automation.ui.pages.CheckoutPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AttemptPurchaseWithValidation implements Task {

    public static final String ALERT_TEXT_KEY = "validationAlertText";

    public static AttemptPurchaseWithValidation andCaptureAlert() {
        return Tasks.instrumented(AttemptPurchaseWithValidation.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(CheckoutPageTargets.PURCHASE_BUTTON, isVisible())
                .forNoMoreThan(10).seconds(),
            JavaScriptClick.on(CheckoutPageTargets.PURCHASE_BUTTON)
        );

        try {
            String alertText = new WebDriverWait(
                    BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent())
                .getText();
            actor.remember(ALERT_TEXT_KEY, alertText);
            BrowseTheWeb.as(actor).getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            actor.remember(ALERT_TEXT_KEY, "");
        }
    }
}
