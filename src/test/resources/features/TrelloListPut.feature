@List
Feature: Verify Trello List POST method

  @CreateDeleteBoard @CreateList @Test
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/lists/[List.id]"
    And raw body:
    """
    {
      "name": "List Example Updated"
    }
    """
    When method PUT
    Then status code 200
    And response headers contains:
      | Content-Type | application/json; charset=utf-8 |
    And response body contains:
      | name | List Example Updated |
    And JSON schema matches "listSchema.json"
    And response body contains "name"