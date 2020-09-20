@Board
Feature: Verify Trello Board PUT method

  @CreateDeleteBoard
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/boards/[Board.id]"
    And raw body:
    """
    {
      "name": "Example Updated"
    }
    """
    When method PUT
    Then status code 200
    And response headers contains:
      |Content-Type|application/json; charset=utf-8|
    And response body contains:
      | name | Example Updated |
    And JSON schema matches "boardSchema.json"