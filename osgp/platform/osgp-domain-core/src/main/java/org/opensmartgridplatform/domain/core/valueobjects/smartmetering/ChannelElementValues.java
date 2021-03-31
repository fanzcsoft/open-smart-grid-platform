/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.domain.core.valueobjects.smartmetering;

import java.io.Serializable;

public class ChannelElementValues implements Serializable {

  private static final long serialVersionUID = 5377631203726277889L;

  private final short channel;
  private final Short primaryAddress;
  private final String identificationNumber;
  private final String manufacturerIdentification;
  private final short version;
  private final short deviceTypeIdentification;

  public ChannelElementValues(
      final short channel,
      final Short primaryAddress,
      final String identificationNumber,
      final String manufacturerIdentification,
      final short version,
      final short deviceTypeIdentification) {
    this.channel = channel;
    this.primaryAddress = primaryAddress;
    this.identificationNumber = identificationNumber;
    this.manufacturerIdentification = manufacturerIdentification;
    this.version = version;
    this.deviceTypeIdentification = deviceTypeIdentification;
  }

  /**
   * Indicates if this has values for a configured M-Bus slave device.
   *
   * <p>If a slave is de-installed the primary address is set to {@code 0}. A non-zero value for the
   * primary address means the other attributes can be used to match an M-Bus device to the
   * indicated channel.
   *
   * @return {@code true} if this can be used to couple an M-Bus device; otherwise {@code false}
   */
  public boolean isMbusSlaveDeviceConfigured() {
    return this.primaryAddress > 0;
  }

  public short getChannel() {
    return this.channel;
  }

  public boolean hasChannel() {
    return this.channel > 0;
  }

  public Short getPrimaryAddress() {
    return this.primaryAddress;
  }

  public String getIdentificationNumber() {
    return this.identificationNumber;
  }

  public String getManufacturerIdentification() {
    return this.manufacturerIdentification;
  }

  public short getVersion() {
    return this.version;
  }

  public boolean hasVersion() {
    return this.version > 0;
  }

  public short getDeviceTypeIdentification() {
    return this.deviceTypeIdentification;
  }

  public boolean hasIdentificationNumber() {
    return this.identificationNumber != null;
  }

  public boolean hasManufacturerIdentification() {
    return this.manufacturerIdentification != null;
  }

  public boolean hasDeviceTypeIdentification() {
    return this.deviceTypeIdentification > 0;
  }

  @Override
  public String toString() {
    return "ChannelElementValues[channel="
        + this.channel
        + ", primaryAddress="
        + this.primaryAddress
        + ", identificationNumber="
        + this.identificationNumber
        + ", manufacturerIdentification="
        + this.manufacturerIdentification
        + ", version="
        + this.version
        + ", deviceTypeIdentification="
        + this.deviceTypeIdentification
        + "]";
  }
}
