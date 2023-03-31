Feature: Validate Products Total Price In Cart


  @Client
  Scenario: Validate That Special Offer Products Total Price In Cart Is Correct
    Given Open Gourmet And Select Suhoor From Side Categories Menu
    When  Select All Special Offer Products
    Then  Assert That Total Price In Cart Matches The Total Price of Special Products
