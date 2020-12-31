Feature: Test search

  Background: Open browser
    Given The user open the browser
    And The user navigate to www.fravega.com

    Scenario: The user perform a search
      Given The user writes "Heladera" in the search field
      And The user