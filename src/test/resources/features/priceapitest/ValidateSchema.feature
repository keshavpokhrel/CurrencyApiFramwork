Feature: Verify Json schema 
 
  @verifyjsonschema
  Scenario: Validate api response with api schema 
    Given Configure api schema.
    Then validate that api response matches with json schema
