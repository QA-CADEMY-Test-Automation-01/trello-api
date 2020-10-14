Feature: teams
  Background: Open creation menu
    Given I am on login page
    When I login with username "joseccb1948@yahoo.com" and password "Control*1234"
    Then I should see "joseccb1948@yahoo.com" in profile menu
    Given I open board creation form

  Scenario: Delete a team
    Given I create a team with following data
      | Name        | Team A      |
      | Type        | Operations  |
      | Description | My new team |
    And I go to team settings
    And I scroll down to bottom
    When I delete the team
    Then I should not see the team "Team A" into the homepage
