/*
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.domain.repositories;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class FirmwareFileCachingRepository extends ByteArrayCachingRepository {

  public FirmwareFileCachingRepository() {
    // Public constructor
  }

  protected FirmwareFileCachingRepository(final Map<String, byte[]> cache) {
    // Protected constructor for testing
    super(cache);
  }
}
