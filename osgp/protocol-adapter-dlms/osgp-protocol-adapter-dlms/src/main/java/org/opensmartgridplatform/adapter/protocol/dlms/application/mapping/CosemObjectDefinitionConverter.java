/*
 * Copyright 2021 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.application.mapping;

import java.util.Arrays;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.openmuc.jdlms.datatypes.DataObject;
import org.opensmartgridplatform.dto.valueobjects.smartmetering.CosemObjectDefinitionDto;

public class CosemObjectDefinitionConverter
    extends CustomConverter<CosemObjectDefinitionDto, DataObject> {

  @Override
  public DataObject convert(
      final CosemObjectDefinitionDto source,
      final Type<? extends DataObject> destinationType,
      final MappingContext context) {
    if (source == null) {
      return null;
    }

    final DataObject classIdElement = DataObject.newUInteger16Data(source.getClassId());
    final DataObject obisCodeElement =
        DataObject.newOctetStringData(source.getLogicalName().toByteArray());
    final DataObject attributeIdElement =
        DataObject.newInteger8Data((byte) source.getAttributeIndex());
    final DataObject dataIndexElement = DataObject.newUInteger16Data(source.getDataIndex());

    return DataObject.newStructureData(
        Arrays.asList(classIdElement, obisCodeElement, attributeIdElement, dataIndexElement));
  }
}
