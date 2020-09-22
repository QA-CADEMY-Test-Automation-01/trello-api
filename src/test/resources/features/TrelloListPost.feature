@List
Feature: Verify Trello List POST method

  @CreateDeleteBoard
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/lists"
    And raw body:
    """
    {
      "name": "Example List",
      "idBoard": "[Board.id]"
    }
    """
    When method POST
    Then status code 200
    And response headers contains:
      | Content-Type | application/json; charset=utf-8 |
    And response body contains:
      | name | Example List |
    And JSON schema matches "listSchema.json"

  @CreateDeleteBoard
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/lists"
    And body data:
      | name    | [Board.name] Updated |
      | idBoard | [Board.id]           |
    When method POST
    Then status code 200
    And response headers contains:
      | Content-Type | application/json; charset=utf-8 |
    And response body contains:
      | name | Example Updated |
    And JSON schema matches "listSchema.json"