package com.demoblaze.automation.questions.web;

import com.demoblaze.automation.ui.pages.OrderConfirmationPageTargets;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheConfirmationMessage implements Question<String> {

    public static TheConfirmationMessage text() {
        return new TheConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(OrderConfirmationPageTargets.CONFIRMATION_TITLE).answeredBy(actor);
    }
}
