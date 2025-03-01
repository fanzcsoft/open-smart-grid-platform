/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.opensmartgridplatform.dto.valueobjects.smartmetering;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProtocolResponseDto extends ActionResponseDto {

  private static final long serialVersionUID = 173773882762590354L;

  private final String protocol;
  private final String protocolVersion;
  private final String protocolVariant;
}
