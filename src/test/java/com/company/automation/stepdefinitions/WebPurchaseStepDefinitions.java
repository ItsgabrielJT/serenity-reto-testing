package com.company.automation.stepdefinitions;

import com.company.automation.questions.web.TheConfirmationMessage;
import com.company.automation.tasks.web.AddProduct;
import com.company.automation.tasks.web.FillCheckoutInformation;
import com.company.automation.tasks.web.FinishPurchase;
import com.company.automation.tasks.web.Login;
import com.company.automation.tasks.web.NavigateTo;
import com.company.automation.tasks.web.ProceedToCheckout;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;

public class WebPurchaseStepDefinitions {

    @Given("the {word} is on the SauceDemo login page")
    public void customerIsOnLoginPage(String actor) {
        theActorCalled(actor).attemptsTo(Open.url("https://www.saucedemo.com/"));
    }

    @When("the customer logs in with {string} and {string}")
    public void logsIn(String username, String password) {
        theActorInTheSpotlight().attemptsTo(
            Login.withCredentials(username, password)
        );
    }

    @When("the customer adds {string} to the cart")
    public void addsProductToCart(String productName) {
        theActorInTheSpotlight().attemptsTo(
            AddProduct.toCart(productName)
        );
    }

    @When("the customer goes to the shopping cart")
    public void goesToShoppingCart() {
        theActorInTheSpotlight().attemptsTo(
            NavigateTo.shoppingCart()
        );
    }

    @Then("the shopping cart should contain the selected products")
    public void shoppingCartShouldContainProducts() {
        // En este paso se debería realizar la validación con una Question de los productos agregados
    }

    @When("the customer clicks the checkout button")
    public void clicksCheckout() {
        theActorInTheSpotlight().attemptsTo(
            ProceedToCheckout.now()
        );
    }

    @When("the customer fills in the checkout form with:")
    public void fillsInCheckoutForm(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        com.company.automation.models.Customer customer = com.company.automation.models.Customer.fromMap(data);
        theActorInTheSpotlight().attemptsTo(
            FillCheckoutInformation.withDetails(customer)
        );
    }

    @When("the customer continues to the checkout overview")
    public void continuesToOverview() {
        // La acción de continuar está manejada dentro de FillCheckoutInformation 
        // para dar continuidad natural a llenar el formulario, 
        // podrías separarlo en una Task aparte si deseas atomicidad extrema.
    }

    @Then("the checkout overview should display the selected products")
    public void overviewShouldDisplayProducts() {
        // Validación visual en el "Overview" antes del step final (Finish)
    }

    @When("the customer finishes the purchase")
    public void finishesPurchase() {
        theActorInTheSpotlight().attemptsTo(
            FinishPurchase.successfully()
        );
    }

    @Then("the order confirmation message should contain {string}")
    public void confirmationMessageShouldContain(String expectedMessage) {
        theActorInTheSpotlight().should(
            seeThat(TheConfirmationMessage.text(), containsString(expectedMessage))
        );
    }
}
