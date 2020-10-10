Feature: Boards
  Background: Login
    Given I am on login page
    When I login with username "joseccb1948@yahoo.com" and password "Control*1234"

  Scenario: Create a board
    Given I open board creation form
    When I create a board with title "My board"
    Then I should see board page loaded with "My board"

  Scenario: Test
    Given I have a the following card
      | Description | Description card |
      | Activity    | Starting task    |
      | DueDate     | 10/14/2020       |
#      Given I have the following boards
#      | Title | Private |
#      | my board1    | yes    |
#      | my board2     | no       |