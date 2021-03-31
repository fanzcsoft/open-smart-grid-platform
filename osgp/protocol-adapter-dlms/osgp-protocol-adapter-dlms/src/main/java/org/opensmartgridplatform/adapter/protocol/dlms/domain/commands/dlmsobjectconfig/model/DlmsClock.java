/**
 * Copyright 2019 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.domain.commands.dlmsobjectconfig.model;

import org.opensmartgridplatform.adapter.protocol.dlms.domain.commands.dlmsobjectconfig.DlmsObjectType;

public class DlmsClock extends DlmsObject {
  private static final int CLASS_ID_CLOCK = 8;

  public DlmsClock(final String obisCode) {
    super(DlmsObjectType.CLOCK, CLASS_ID_CLOCK, obisCode);
  }
}
