Feature: Lists
  Background: Create board
    Given I am on login page
    And I login with username "joseccb1948@yahoo.com" and password "Control*1234"
    And I open board creation form
    When I create a board with title "My board"
    Then I should see board page loaded with "My board"

  Scenario: Create a list
    Given I open list creation form
    When I create the list with name "My List"
    Then I should see the title "My List" in list
