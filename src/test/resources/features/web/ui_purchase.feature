@ui
Feature: Demoblaze E2E Purchase Flow
  As a customer
  I want to add products to the cart and complete a purchase on Demoblaze
  So that I can receive the items I ordered

  Scenario Outline: Customer completes a full purchase with two products
    Given the customer is on the Demoblaze store
    When the customer adds "<product1>" to the cart
    And the customer adds "<product2>" to the cart
    And the customer views the shopping cart
    Then the shopping cart should contain the selected products
    When the customer clicks the place order button
    And the customer fills in the purchase form with:
      | name       | <name>       |
      | country    | <country>    |
      | city       | <city>       |
      | creditCard | <creditCard> |
      | month      | <month>      |
      | year       | <year>       |
    When the customer confirms the purchase
    Then the purchase confirmation should contain "Thank you for your purchase!"

    Examples:
      | product1           | product2           | name      | country  | city   | creditCard    | month | year |
      | Samsung galaxy s6  | Nokia lumia 1520   | John Doe  | Colombia | Bogota | 4111111111111 | 05    | 2026 |

  @ui
  Scenario: Customer cannot complete a purchase with missing required fields
    Given the customer is on the Demoblaze store
    When the customer adds "Samsung galaxy s6" to the cart
    And the customer views the shopping cart
    And the customer clicks the place order button
    And the customer fills in the purchase form with:
      | name       |            |
      | country    |            |
      | city       |            |
      | creditCard |            |
      | month      |            |
      | year       |            |
    And the customer attempts to confirm the purchase
    Then the purchase should be rejected with a message containing "Please fill out"
