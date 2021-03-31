/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.dto.da;

import java.io.Serializable;

public class GetHealthStatusResponseDto implements Serializable {
  private static final long serialVersionUID = 4776483459295816646L;

  private final String healthStatus;

  public GetHealthStatusResponseDto(final String healthStatus) {
    this.healthStatus = healthStatus;
  }

  public String getHealthStatus() {
    return this.healthStatus;
  }
}
