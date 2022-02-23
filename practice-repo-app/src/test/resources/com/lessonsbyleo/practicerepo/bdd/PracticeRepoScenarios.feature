Feature: The shopping cart calculates the Customers order.

  Scenario: Shopping cart gets the Zip code from the internal service
    Given the customer has an order in their shopping cart
    And the customers sales tax has already been saved internally
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart gets the customers sales tax internally

  Scenario: Shopping cart gets the Zip code from the external service
    Given the customer has an order in their shopping cart
    And the customers sales tax has not been saved internally
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart gets the customers sales tax externally

  Scenario: Shopping cart cannot get the Zip code from the services
    Given the customer has an order in their shopping cart
    And the shopping cart is unable to get the customers sales tax
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart cannot calculate the customers total bill

  Scenario: Shopping cart gets the orders inventory details
    Given the customer has an order in their shopping cart
    And the customers sales tax has already been saved internally
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart gets the orders inventory details

  Scenario: Shopping cart requests an unknown sku inventory details
    Given the customer has an order in their shopping cart
    And one of the orders items is not part of the inventory
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart cannot calculate the customers total bill

  Scenario: Customer order has an item with more quantity than inventory can provide
    Given the customer has an order in their shopping cart
    And one of the orders items has more quantity than inventory can provide
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart cannot calculate the customers total bill

  Scenario: The Customer is ready for their orders total
    Given the customer has an order in their shopping cart
    And the customers sales tax has already been saved internally
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart calculates customers the total bill

  Scenario: The Customer is ready for their orders total
    Given the customer has an order in their shopping cart
    And the customers sales tax has already been saved internally
    When the customer requests the total bill of the shopping cart order
    Then the shopping cart calculates customers the total bill