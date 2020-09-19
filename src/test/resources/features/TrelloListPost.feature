@List @Test
Feature: Verify Trello List POST method

  @CreateDeleteBoard
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/lists"
    And raw body:
    """
    {
      "name": "Example List",
      "idBoard": "[board.id]"
    }
    """
    When method POST
    Then status code 200
    And response headers contains:
      | Content-Type | application/json; charset=utf-8 |
    And response body contains:
      | name | Example List |
    And JSON schema matches "listSchema.json"