package com.saucedemo.automation.questions.web;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TheErrorMessage {
    public static Question<String> text() {
        // El mensaje de error puede estar dentro del contenedor error-message-container
        // Su texto completo está presente en el DOM
        Target errorText = Target.the("Error message text")
                .located(By.cssSelector(".error-message-container"));
        return Text.of(errorText).asString();
    }
}
