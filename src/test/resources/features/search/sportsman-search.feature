Feature: Sportman Search

  Background:
    Given the user wants to use train schedule application

  Scenario Outline: Verify if the page has loaded successfully
    Then the user is navigate successfully to the application
    And search for "<playername>"
    Examples:
      | playername       |
      | Sachin Tendulkar |
      | Virat Kohli      |
      | Roger Federer    |
      | Kane Williamson  |
      | AB Devilliers    |
      | Tiger Woods      |
      | Lin Dan          |


  