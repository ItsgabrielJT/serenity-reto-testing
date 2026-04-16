@ui
Feature: SauceDemo E2E Purchase Flow
  As a customer
  I want to login and purchase products from the store
  So that I can receive the items I ordered

  Scenario: Customer completes a full purchase with two products
    Given the customer is on the SauceDemo login page
    When the customer logs in with "standard_user" and "secret_sauce"
    And the customer adds "Sauce Labs Backpack" to the cart
    And the customer adds "Sauce Labs Bike Light" to the cart
    And the customer goes to the shopping cart
    Then the shopping cart should contain the selected products
    When the customer clicks the checkout button
    And the customer fills in the checkout form with:
      | firstName | John   |
      | lastName  | Doe    |
      | zipCode   | 12345  |
    And the customer continues to the checkout overview
    Then the checkout overview should display the selected products
    When the customer finishes the purchase
    Then the order confirmation message should contain "Thank you for your order!"
