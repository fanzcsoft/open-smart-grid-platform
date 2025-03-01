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

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component(value = "pushSetupMapper")
public class PushSetupMapper extends ConfigurableMapper {

  @Override
  public void configure(final MapperFactory mapperFactory) {
    mapperFactory.getConverterFactory().registerConverter(new CosemObjectDefinitionConverter());
    mapperFactory.getConverterFactory().registerConverter(new SendDestinationAndMethodConverter());
  }
}
