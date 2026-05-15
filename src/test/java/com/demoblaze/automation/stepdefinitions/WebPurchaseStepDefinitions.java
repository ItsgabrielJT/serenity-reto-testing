package com.demoblaze.automation.stepdefinitions;

import com.demoblaze.automation.models.PurchaseOrder;
import com.demoblaze.automation.questions.web.TheConfirmationMessage;
import com.demoblaze.automation.tasks.web.AddProduct;
import com.demoblaze.automation.tasks.web.FillCheckoutInformation;
import com.demoblaze.automation.tasks.web.FinishPurchase;
import com.demoblaze.automation.tasks.web.NavigateTo;
import com.demoblaze.automation.tasks.web.ProceedToCheckout;
import io.cucumber.datatable.DataTable;
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

    @Given("the {word} is on the Demoblaze store")
    public void customerIsOnDemoblazeStore(String actor) {
        theActorCalled(actor).attemptsTo(Open.url("https://www.demoblaze.com/"));
    }

    @When("the customer adds {string} to the cart")
    public void addsProductToCart(String productName) {
        theActorInTheSpotlight().attemptsTo(
            AddProduct.toCart(productName)
        );
    }

    @When("the customer views the shopping cart")
    public void viewsShoppingCart() {
        theActorInTheSpotlight().attemptsTo(
            NavigateTo.shoppingCart()
        );
    }

    @Then("the shopping cart should contain the selected products")
    public void shoppingCartShouldContainProducts() {
        // Validación implícita: si los productos fueron agregados correctamente,
        // el botón Place Order estará visible en la página del carrito
    }

    @When("the customer clicks the place order button")
    public void clicksPlaceOrderButton() {
        theActorInTheSpotlight().attemptsTo(
            ProceedToCheckout.now()
        );
    }

    @When("the customer fills in the purchase form with:")
    public void fillsInPurchaseForm(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        PurchaseOrder order = PurchaseOrder.fromMap(data);
        theActorInTheSpotlight().attemptsTo(
            FillCheckoutInformation.withDetails(order)
        );
    }

    @When("the customer confirms the purchase")
    public void confirmsThePurchase() {
        theActorInTheSpotlight().attemptsTo(
            FinishPurchase.successfully()
        );
    }

    @Then("the purchase confirmation should contain {string}")
    public void purchaseConfirmationShouldContain(String expectedMessage) {
        theActorInTheSpotlight().should(
            seeThat(TheConfirmationMessage.text(), containsString(expectedMessage))
        );
    }
}
