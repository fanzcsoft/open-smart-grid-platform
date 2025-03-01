/*
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.shared.exceptionhandling;

public enum FunctionalExceptionType {
  // Organisation exceptions
  UNKNOWN_ORGANISATION(101, "UNKNOWN_ORGANISATION"),
  EXISTING_ORGANISATION(102, "EXISTING_ORGANISATION"),
  EXISTING_ORGANISATION_WITH_SAME_IDENTIFICATION(
      103, "EXISTING_ORGANISATION_WITH_SAME_IDENTIFICATION"),
  DISABLED_ORGANISATION(104, "DISABLED_ORGANISATION"),

  // Device exceptions
  UNKNOWN_DEVICE(201, "UNKNOWN_DEVICE"),
  UNREGISTERED_DEVICE(202, "UNREGISTERED_DEVICE"),
  UNSCHEDULED_DEVICE(203, "UNSCHEDULED_DEVICE"),
  EXISTING_DEVICE(204, "EXISTING_DEVICE"),
  PROTOCOL_UNKNOWN_FOR_DEVICE(205, "PROTOCOL_UNKNOWN_FOR_DEVICE"),
  UNKNOWN_PROTOCOL_NAME_OR_VERSION_OR_VARIANT(206, "UNKNOWN_PROTOCOL_NAME_OR_VERSION_OR_VARIANT"),
  INACTIVE_DEVICE(207, "INACTIVE_DEVICE"),
  DEVICES_NOT_COUPLED(208, "DEVICES_NOT_COUPLED"),
  CHANNEL_ON_DEVICE_ALREADY_COUPLED(209, "CHANNEL_ON_DEVICE_ALREADY_COUPLED"),
  UNKNOWN_DEVICE_OUTPUT_SETTINGS(210, "UNKNOWN_DEVICE_OUTPUT_SETTINGS"),
  CONNECTION_ERROR(211, "CONNECTION_ERROR"),
  INVALID_IP_ADDRESS(212, "INVALID_IP_ADDRESS"),
  CHALLENGE_LENGTH_OUT_OF_RANGE(213, "CHALLENGE_LENGTH_OUT_OF_RANGE"),
  COMMUNICATION_TIMEOUT(214, "COMMUNICATION_TIMEOUT"),
  NO_MBUS_DEVICE_CHANNEL_FOUND(215, "NO_MBUS_DEVICE_CHANNEL_FOUND"),
  GIVEN_MBUS_DEVICE_ALREADY_COUPLED(216, "GIVEN_MBUS_DEVICE_ALREADY_COUPLED"),
  ALL_MBUS_CHANNELS_OCCUPIED(217, "ALL_MBUS_CHANNELS_OCCUPIED"),
  NO_MATCHING_MBUS_DEVICE_FOUND(218, "NO_MATCHING_MBUS_DEVICE_FOUND"),
  NO_DEVICE_FOUND_ON_CHANNEL(219, "NO_DEVICE_FOUND_ON_CHANNEL"),
  GATEWAY_DEVICE_NOT_SET_FOR_MBUS_DEVICE(220, "GATEWAY_DEVICE_NOT_SET_FOR_MBUS_DEVICE"),
  GATEWAY_DEVICE_INVALID_FOR_MBUS_DEVICE(221, "GATEWAY_DEVICE_INVALID_FOR_MBUS_DEVICE"),
  MBUS_DEVICE_NOT_MOVED_TO_ANOTHER_EMETER(222, "MBUS_DEVICE_NOT_MOVED_TO_ANOTHER_EMETER"),

  // Authorization exceptions
  UNAUTHORIZED(301, "UNAUTHORIZED"),
  EXISTING_DEVICE_AUTHORIZATIONS(302, "EXISTING_DEVICE_AUTHORIZATIONS"),
  METHOD_NOT_ALLOWED_FOR_OWNER(303, "METHOD_NOT_ALLOWED_FOR_OWNER"),
  DEVICE_IN_MAINTENANCE(304, "DEVICE_IN_MAINTENANCE"),

  // Other exceptions
  VALIDATION_ERROR(401, "VALIDATION_ERROR"),
  TARIFF_SCHEDULE_NOT_ALLOWED_FOR_PSLD(402, "TARIFF_SCHEDULE_NOT_ALLOWED_FOR_PSLD"),
  ARGUMENT_NULL(403, "ARGUMENT_NULL"),
  JMS_TEMPLATE_NULL(404, "JMS_TEMPLATE_NULL"),
  UNKNOWN_CORRELATION_UID(405, "UNKNOWN_CORRELATION_UID"),
  LIGHT_SWITCHING_NOT_ALLOWED_FOR_RELAY(406, "LIGHT_SWITCHING_NOT_ALLOWED_FOR_RELAY"),
  UNSUPPORTED_DEVICE_ACTION(407, "UNSUPPORTED_DEVICE_ACTION"),
  ACTION_NOT_ALLOWED_FOR_LIGHT_RELAY(408, "ACTION_NOT_ALLOWED_FOR_LIGHT_RELAY"),
  ACTION_NOT_ALLOWED_FOR_TARIFF_RELAY(409, "ACTION_NOT_ALLOWED_FOR_TARIFF_RELAY"),
  INVALID_ICCID(410, "INVALID_ICCID"),
  SESSION_INFO_NULL(411, "SESSION_INFO_NULL"),
  ERROR_RETRIEVING_ATTRIBUTE_VALUE(412, "ERROR_RETRIEVING_ATTRIBUTE_VALUE"),
  UNSUPPORTED_COMMUNICATION_SETTING(413, "UNSUPPORTED_COMMUNICATION_SETTING"),
  UNABLE_TO_PROCESS_REQUEST(414, "UNABLE_TO_PROCESS_REQUEST"),
  SET_SCHEDULE_WITH_ASTRONOMICAL_OFFSETS_IN_PROGRESS(
      415, "SET_SCHEDULE_WITH_ASTRONOMICAL_OFFSETS_IN_PROGRESS"),
  OPERATION_NOT_SUPPORTED_BY_PLATFORM_FOR_PROTOCOL(
      416, "OPERATION_NOT_SUPPORTED_BY_PLATFORM_FOR_PROTOCOL"),
  MAX_SCHEDULE_TIME_EXCEEDED(417, "MAX_SCHEDULE_TIME_EXCEEDED"),
  SESSION_PROVIDER_ERROR(419, "SESSION_PROVIDER_ERROR"),

  // Manufacturer exceptions
  UNKNOWN_MANUFACTURER(501, "UNKNOWN_MANUFACTURER"),
  EXISTING_MANUFACTURER(502, "EXISTING_MANUFACTURER"),
  EXISTING_DEVICEMODEL_MANUFACTURER(503, "EXISTING_DEVICEMODEL_MANUFACTURER"),

  // DeviceModel exceptions
  UNKNOWN_DEVICEMODEL(601, "UNKNOWN_DEVICEMODEL"),
  EXISTING_DEVICEMODEL(602, "EXISTING_DEVICEMODEL"),
  EXISTING_DEVICE_DEVICEMODEL(603, "EXISTING_DEVICE_DEVICEMODEL"),
  EXISTING_DEVICEMODEL_FIRMWARE(604, "EXISTING_DEVICEMODEL_FIRMWARE"),

  // Firmware exceptions
  UNKNOWN_FIRMWARE(701, "UNKNOWN_FIRMWARE"),
  EXISTING_FIRMWARE(702, "EXISTING_FIRMWARE"),
  EXISTING_FIRMWARE_DEVICEFIRMWARE(703, "EXISTING_FIRMWARE_DEVICEFIRMWARE"),
  FIRMWARE_DOES_NOT_SUPPORT_DEVICE_MODEL(704, "FIRMWARE_DOES_NOT_SUPPORT_DEVICE_MODEL"),

  // Key exceptions
  INVALID_DLMS_KEY_ENCRYPTION(801, "INVALID_DLMS_KEY_ENCRYPTION"),
  INVALID_DLMS_KEY_FORMAT(802, "INVALID_DLMS_KEY_FORMAT"),
  ENCRYPTION_EXCEPTION(803, "ENCRYPTION_EXCEPTION"),
  DECRYPTION_EXCEPTION(804, "DECRYPTION_EXCEPTION"),
  READING_KEY_EXCEPTION(805, "READING_KEY_EXCEPTION"),
  KEY_NOT_PRESENT(806, "KEY_NOT_PRESENT"),
  INVALID_KEY_FORMAT(807, "INVALID_KEY_FORMAT"),
  ATTEMPT_TO_LOWER_INVOCATION_COUNTER(808, "ATTEMPT_TO_LOWER_INVOCATION_COUNTER");

  private final int code;
  private final String message;

  FunctionalExceptionType(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}
