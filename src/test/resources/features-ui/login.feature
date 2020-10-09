Feature: Login
  Background:
    Given I am on login page
    When I login with username "juan.perez@mail.com" and password "123"

  Scenario: Login with valid credentials
    Given I am on login page
    When I login with username "joseccb1948@yahoo.com" and password "Control*1234"
    Then I should see "joseccb1948@yahoo.com" in profile menu

  Scenario: Login with incorrect credentials
    Given I am on login page
    And I login in single page with username "Juan perez" and password "123"
    Then I should see the error message "There isn't an account for this username"


