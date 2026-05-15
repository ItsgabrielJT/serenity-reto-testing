package com.demoblaze.automation.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AcceptTheAlert implements Task {

    public static AcceptTheAlert ifPresent() {
        return Tasks.instrumented(AcceptTheAlert.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
            BrowseTheWeb.as(actor).getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            // Sin alerta presente o tiempo de espera agotado — continuar
        }
    }
}
