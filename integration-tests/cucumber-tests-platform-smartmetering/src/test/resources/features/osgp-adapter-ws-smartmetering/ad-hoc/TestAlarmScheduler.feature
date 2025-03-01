@SmartMetering @Platform @SmartMeteringAdHoc @NightlyBuildOnly
Feature: SmartMetering schedule test alarms
  As a grid operator
  I want to be able to set a test alarm on a device
  So scheduled alarms will be sent

  Scenario: Set test alarm PARTIAL_POWER_OUTAGE on a device
    Given a dlms device
      | DeviceIdentification | TEST1027000000001    |
      | DeviceType           | SMART_METER_E        |
      | Protocol             | SMR                  |
      | ProtocolVersion      | 5.0.0                |
      | Port                 | 1027                 |
    When receiving a test alarm scheduler request
      | DeviceIdentification | TEST1027000000001    |
      | TestAlarmType        | PARTIAL_POWER_OUTAGE |
      | Time                 | 2088-01-01T00:00:00Z |
    Then a response is received
      | DeviceIdentification | TEST1027000000001    |


  Scenario: Set test alarm LAST_GASP on a device
    Given a dlms device
      | DeviceIdentification | TEST1030000000001    |
      | DeviceType           | SMART_METER_E        |
      | Protocol             | SMR                  |
      | ProtocolVersion      | 5.5                  |
      | Port                 | 1030                 |
    When receiving a test alarm scheduler request
      | DeviceIdentification | TEST1030000000001    |
      | TestAlarmType        | LAST_GASP            |
      | Time                 | 2088-01-01T00:00:00Z |
    Then a response is received
      | DeviceIdentification | TEST1030000000001    |