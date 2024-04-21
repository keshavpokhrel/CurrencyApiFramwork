Feature: Get API results

  @getvalidprice
  Scenario: Get price from api and validate 
    Given Api configs are created
    When Hit configure api and get price result
    Then Validate api result
    And check api call is successful and returns valid price
    And check that USD price against "AED" is within range on "3.6234" to "3.6900"

  @getcurrencylist
  Scenario Outline: Get list of currency in api response
    Given Api configs are created
    When Hit configure api and get price result
    Then Validate api result
    Then Validate that api result have 162 currency pairs
