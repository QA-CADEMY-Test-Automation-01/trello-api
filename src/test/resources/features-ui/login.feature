@login
Feature: Login

  @bvt
  Scenario: Login with valid credentials
    Given I am on login page
#    When I login with username "joseccb1948@yahoo.com" and password "Control*1234"
    When I login with username "jose"
    Then I should see "joseccb1948@yahoo.com" in profile menu

  @negative
  Scenario: Login with incorrect credentials
    Given I am on login page
    And I login in single page with username "Juan perez" and password "123"
    Then I should see the error message "There isn't an account for this username"

  @outline
  Scenario Outline: Login with not valid credentials
    Given I am on login page
    When I login in single page with username "<user>" and password "<password>"
    Then I should see the error message "<message>"

    Examples:
      | user | password | message                                                                 |
      | juan | 123      | Incorrect email address and / or password. Do you need help logging in? |
      |      | 123      | Missing email                                                           |
      | juan |          | Incorrect email address and / or password. Do you need help logging in? |

