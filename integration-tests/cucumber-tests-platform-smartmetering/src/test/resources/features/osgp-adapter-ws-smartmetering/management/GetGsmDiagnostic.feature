@SmartMetering @Platform @GetGsmDiagnostic
Feature: SmartMetering Management - Get GSM Diagnostic
  As a grid operator
  I want to be able to get the GSM Diagnostic of a smart meter

  Scenario: Get the gsm diagnostic of a E-Meter
    And a dlms device
      | DeviceIdentification | TEST1027000000001 |
      | DeviceType           | SMART_METER_E     |
      | CommunicationMethod  | GPRS              |
      | Protocol             | SMR               |
      | ProtocolVersion      |               5.1 |
      | Port                 |              1027 |
    When a get gsm diagnostic request is received
      | DeviceIdentification | TEST1027000000001 |
    Then the get gsm diagnostic response is returned with values
      | operator                  | Utility Connect          |
      | modemRegistrationStatus   | REGISTERED_ROAMING       |
      | circuitSwitchedStatus     | INACTIVE                 |
      | packetSwitchedStatus      | GPRS                     |
      | cellId                    | 77                       |
      | locationId                | 2230                     |
      | signalQuality             | MINUS_87_DBM             |
      | bitErrorRate              | RXQUAL_6                 |
      | mobileCountryCode         |                       66 |
      | mobileNetworkCode         |                      204 |
      | channelNumber             |                      107 |
      | numberOfAdjacentCells     |                        2 |
      | adjacentCellId            | 93                       |
      | adjacentCellSignalQuality | MINUS_91_DBM             |
#      | captureTime               | 2021-04-13T08:45:00.000Z |
