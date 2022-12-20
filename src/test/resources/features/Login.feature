Feature: Login and E2E Test

  @wip
  Scenario: Login and End to End Test
    Given The user is on the login page
    When The user is enters "standard_user" and "secret_sauce"
    Then The user should be able to Login and See "PRODUCTS" header