package com.demoblaze.automation.questions.web;

import com.demoblaze.automation.tasks.web.AttemptPurchaseWithValidation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheValidationAlert implements Question<String> {

    public static TheValidationAlert text() {
        return new TheValidationAlert();
    }

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall(AttemptPurchaseWithValidation.ALERT_TEXT_KEY);
    }
}
