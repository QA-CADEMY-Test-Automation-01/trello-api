@Board
Feature: Verify Trello Board POST method

  @DeleteBoard
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/boards"
    And raw body:
    """
    {
      "name": "Example"
    }
    """
    When method POST
    Then status code 200
    And response headers contains:
      | Content-Type | application/json; charset=utf-8 |
    And response body contains:
      | name | Example |
    And JSON schema matches "boardSchema.json"

  @DeleteBoard
  Scenario: Verify status code 200 with valid data
    Given endpoint "/1/boards"
    And body data:
      | name | Example data |
      | desc | Description  |
    When method POST
    Then status code 200
    And response headers contains:
      | Content-Type | application/json; charset=utf-8 |
    And response body contains:
      | name | Example data |
      | desc | Description  |
    And JSON schema matches "boardSchema.json"


  Scenario: Verify status code 400 with valid data
    Given endpoint "/1/boards"
    And raw body:
    """
    {
      "desc": "Description"
    }
    """
    When method POST
    Then status code 400
    And response headers contains:
      | Content-Type | text/plain; charset=utf-8 |


  Scenario: Verify status code 400 with valid data
    Given endpoint "/1/boards"
    And body data:
      | desc | Description |
    When method POST
    Then status code 400
    And response headers contains:
      | Content-Type | text/plain; charset=utf-8 |

