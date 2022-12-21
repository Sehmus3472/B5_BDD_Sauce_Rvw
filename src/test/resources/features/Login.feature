Feature: Login and E2E Test

  @wip
  Scenario: Login and End to End Test
    Given The user is on the login page
    When The user is enters "standard_user" and "secret_sauce"
    Then  The user should be able to Login and See "PRODUCTS" header
    And The user should be able to sort products "Price (low to high)"
    And The user should clicks cheapest as "15.99" and second costline as "49.99" product
    And The user open basket
    And The user clicks checkout
    And The user enters detailes "Jhon" "Smith" "GF123H" and finish the purchase
    Then The user verify that items are "71.26" and able to total is "Total: $71.26"
