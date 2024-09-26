Feature: Validate booking

  Scenario: verify user can create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json"
    When user performs post call
    Then verify status code is 200

    And verify booking id is not empty
    And store created booking id into "booking.id"

    When user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_token.json"
    When user performs post call
    Then verify status code is 200
    And store token value to "api.token"

    When user wants to call "/booking/@id" end point
    And set header "Content-Type" to "application/json"
    And set header "Cookie" to "token=@token"
    And set request body from file "update_booking.json"
    When user performs put call
    Then verify status code is 200





