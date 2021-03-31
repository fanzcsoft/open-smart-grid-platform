/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.ws.da.application.exceptionhandling;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.opensmartgridplatform.adapter.ws.schema.distributionautomation.common.FunctionalFault;
import org.opensmartgridplatform.shared.exceptionhandling.FunctionalException;

public class FunctionalExceptionConverter
    extends CustomConverter<FunctionalException, FunctionalFault> {

  @Override
  public FunctionalFault convert(
      final FunctionalException source,
      final Type<? extends FunctionalFault> destinationType,
      final MappingContext context) {
    if (source == null) {
      return null;
    }
    final FunctionalFault destination = new FunctionalFault();
    destination.setCode(source.getCode());
    destination.setComponent(source.getComponentType().name());
    destination.setMessage(source.getMessage());
    if (source.getCause() != null) {
      destination.setInnerException(source.getCause().getClass().getName());
      destination.setInnerMessage(source.getCause().getMessage());
    }
    return destination;
  }
}
