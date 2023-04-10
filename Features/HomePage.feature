Feature: Reqres data management application homepage

  Scenario: Able to click on each request type and verify response details
    Given I open the application
    When I click on single user request method
    Then verify response after clicking on sigle user method

  Scenario: Application should display different request types
    Given I open the application
    When verify different request types on homepage
    Then request types counts is "15"

  Scenario: Support Reqres button validation
    Given I open the application
    When I verify Support Reqres button is displayed
    Then Click on Support Reqres button
    And Verify Support page displayed

  Scenario: Upgrade button validation
    Given I open the application
    When I verify Upgrade button is displayed
    Then Click on Upgrade button
    And Verify Upgrade details page displayed




