@SmartMetering @Platform
Feature: SmartMetering Installation - Add smart meter
  As a grid operator
  I want to be able to add a smart meter

  Scenario Outline: Add a new device <Testtitle>
    When receiving a smartmetering add device request
      | DeviceIdentification  | TEST1024000000001 |
      | DeviceType            | SMART_METER_E     |
      | CommunicationMethod   | GPRS              |
      | CommunicationProvider | KPN               |
      | ICC_id                | 1234              |
      | protocolName          | DSMR              |
      | protocolVersion       | 4.2.2             |
      | Supplier              | Kaifa             |
      | HLS3_active           | false             |
      | HLS4_active           | false             |
      | HLS5_active           | true              |
      | Master_key            | SECURITY_KEY_M    |
      | Authentication_key    | SECURITY_KEY_A    |
      | Encryption_key        | SECURITY_KEY_E    |
      | ManufacturerCode      | Test              |
      | ModelCode             | Test              |
      | Timezone              | <Timezone>        |
    Then the add device response should be returned
      | DeviceIdentification | TEST1024000000001 |
      | Result               | OK                |
    And the dlms device with identification "TEST1024000000001" exists with device model
      | ManufacturerCode | Test |
      | ModelCode        | Test |
    And the dlms device with identification "TEST1024000000001" exists with properties
      | DlmsDeviceTimezone | <Timezone> |
    And a request to the device can be performed after activation
    And the new keys are stored in the database in another encryption then the encryption of the keys received in the SOAP request

    Examples:
      | Testtitle        | Timezone         |
      | with timezone    | Europe/Amsterdam |
      | without timezone |                  |

  @NightlyBuildOnly @Skip
  Scenario: Add a new device with incorrectly encrypted keys
    When receiving a smartmetering add device request
      | DeviceIdentification  | TEST1024000000001        |
      | DeviceType            | SMART_METER_E            |
      | CommunicationMethod   | GPRS                     |
      | CommunicationProvider | KPN                      |
      | ICC_id                | 1234                     |
      | protocolName          | DSMR                     |
      | protocolVersion       | 4.2.2                    |
      | Supplier              | Kaifa                    |
      | HLS3_active           | false                    |
      | HLS4_active           | false                    |
      | HLS5_active           | true                     |
      | Master_key            | INCORRECT_SECURITY_KEY_3 |
      | Authentication_key    | INCORRECT_SECURITY_KEY_1 |
      | Encryption_key        | INCORRECT_SECURITY_KEY_2 |
    Then retrieving the AddDevice response results in an exception
    And a SOAP fault should have been returned
      | Code         | 804                                                           |
      | Message      | DECRYPTION_EXCEPTION                                          |
      | InnerMessage | Unexpected exception during decryption of E_METER_MASTER key. |
    And the dlms device with identification "TEST1024000000001" does not exist
