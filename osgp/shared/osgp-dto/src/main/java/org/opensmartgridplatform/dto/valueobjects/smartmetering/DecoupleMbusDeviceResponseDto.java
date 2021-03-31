/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.dto.valueobjects.smartmetering;

import lombok.Getter;

@Getter
public class DecoupleMbusDeviceResponseDto extends ActionResponseDto {

  private static final long serialVersionUID = -4454979905929290745L;
  private final ChannelElementValuesDto channelElementValues;
  private String mbusDeviceIdentification;

  public DecoupleMbusDeviceResponseDto(final ChannelElementValuesDto channelElementValues) {
    super("Decouple Mbus Device was successful");
    this.channelElementValues = channelElementValues;
  }

  @Override
  public String toString() {
    return "DecoupleMbusDeviceResponseDto [channel="
        + this.channelElementValues.getChannel()
        + ", mbusDeviceIdentification="
        + this.mbusDeviceIdentification
        + "]";
  }

  public void setMbusDeviceIdentification(final String mbusDeviceIdentification) {
    this.mbusDeviceIdentification = mbusDeviceIdentification;
  }
}
