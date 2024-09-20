
@DbLoginFeature @Regression
Feature: Codefios login functionality validation


 Background: 
    Given User is on the codefios login page
    
@DbLoginScenario1
Scenario: User should be able to login with valid credential
When User enters "username" from mysql database
When User enters "password" from mysql database
And User clicks on "login"
