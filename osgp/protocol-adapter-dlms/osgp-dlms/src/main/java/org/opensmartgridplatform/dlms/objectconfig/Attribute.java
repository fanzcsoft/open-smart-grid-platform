/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.opensmartgridplatform.dlms.objectconfig;

import lombok.Data;

@Data
public class Attribute {
  private int id;
  private String description;
  private DlmsDataType datatype;
  private ValueType valuetype;
  private String value;
  private AccessType access;
}
