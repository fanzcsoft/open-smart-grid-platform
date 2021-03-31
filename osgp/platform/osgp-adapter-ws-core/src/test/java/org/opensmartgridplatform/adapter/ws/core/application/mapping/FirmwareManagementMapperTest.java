/**
 * Copyright 2018 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.ws.core.application.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opensmartgridplatform.adapter.ws.schema.core.firmwaremanagement.FirmwareModuleDataBuilder;
import org.opensmartgridplatform.domain.core.valueobjects.FirmwareModuleData;

public class FirmwareManagementMapperTest {
  private FirmwareManagementMapper mapper;

  private FirmwareModuleData map(
      final org.opensmartgridplatform.adapter.ws.schema.core.firmwaremanagement.FirmwareModuleData
          source) {
    return new FirmwareModuleData(
        source.getModuleVersionComm(),
        source.getModuleVersionFunc(),
        source.getModuleVersionMa(),
        source.getModuleVersionMbus(),
        source.getModuleVersionSec(),
        source.getModuleVersionMBusDriverActive(),
        source.getModuleVersionSimple());
  }

  @Test
  public void mapsFirmwareModuleData() {
    final org.opensmartgridplatform.adapter.ws.schema.core.firmwaremanagement.FirmwareModuleData
        source = new FirmwareModuleDataBuilder().build();

    final FirmwareModuleData mappedValue = this.map(source);
    assertThat(this.mapper.map(source, FirmwareModuleData.class))
        .usingRecursiveComparison()
        .isEqualTo(mappedValue);
  }

  @BeforeEach
  public void setUp() throws Exception {
    this.mapper = new FirmwareManagementMapper();
    this.mapper.initialize();
  }
}
