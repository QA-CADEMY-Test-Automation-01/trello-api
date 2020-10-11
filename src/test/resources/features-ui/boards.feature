Feature: Boards
  Background: Login
    Given I am on login page
    When I login with username "joseccb1948@yahoo.com" and password "Control*1234"

  Scenario: Create a board
    Given I open board creation form
    When I create a board with title "My board"
    Then I should see board page loaded with "My board"
@board
  Scenario: Create a board with data parameters
    Given I open board creation form
    When I create a board with following data
      | Title | My Board       |
      | Theme | pink jellyfish |
#    When I create a board with following data
#      | Title    | Theme          |
#      | My Board | pink jellyfish |
    Then I should see board page loaded with "My Board"

