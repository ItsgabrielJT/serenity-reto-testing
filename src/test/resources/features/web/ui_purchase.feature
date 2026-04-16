@ui
Feature: SauceDemo E2E Purchase Flow
  As a customer
  I want to login and purchase products from the store
  So that I can receive the items I ordered

  Scenario Outline: Customer completes a full purchase with multiple combinations
    Given the customer is on the SauceDemo login page
    When the customer logs in with "standard_user" and "secret_sauce"
    And the customer adds "<product1>" to the cart
    And the customer adds "<product2>" to the cart
    And the customer goes to the shopping cart
    Then the shopping cart should contain the selected products
    When the customer clicks the checkout button
    And the customer fills in the checkout form with:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | zipCode   | <zipCode>   |
    And the customer continues to the checkout overview
    Then the checkout overview should display the selected products
    When the customer finishes the purchase
    Then the order confirmation message should contain "Thank you for your order!"

    Examples:
      | product1                 | product2                   | firstName | lastName | zipCode |
      | Sauce Labs Backpack      | Sauce Labs Bike Light      | John      | Doe      | 12345   |
      | Sauce Labs Bolt T-Shirt | Sauce Labs Fleece Jacket   | Jane      | Smith    | 98765   |

  Scenario: Customer tries to checkout missing the first name
    Given the customer is on the SauceDemo login page
    When the customer logs in with "standard_user" and "secret_sauce"
    And the customer adds "Sauce Labs Backpack" to the cart
    And the customer goes to the shopping cart
    When the customer clicks the checkout button
    And the customer fills in the checkout form with:
      | firstName |       |
      | lastName  | Doe   |
      | zipCode   | 12345 |
    Then the checkout should display an error message containing "Error: First Name is required"

  Scenario: Customer tries to checkout without adding products (empty cart)
    Given the customer is on the SauceDemo login page
    When the customer logs in with "standard_user" and "secret_sauce"
    And the customer goes to the shopping cart
    Then the cart should be empty
