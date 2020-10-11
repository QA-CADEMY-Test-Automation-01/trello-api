Feature: Cards
  Background: Create list
    Given I am on login page
    And I login with username "joseccb1948@yahoo.com" and password "Control*1234"
    And I open board creation form
    And I create a board with title "My board"
    When I open list creation form
    And I create the list with name "My List"
    Then I should see the title "My List" in list

  Scenario: Create more than one card in the same list
    When I create the following cards
      | Title     |
      | my card1  |
      | my card2  |
    Then I see the following cards
      | Title     |
      | my card1  |
      | my card2  |

#    Given I have a the following cards
#    | Description | Description card |
#    | Activity    | Starting task    |
#    | DueDate     | 10/14/2020       |