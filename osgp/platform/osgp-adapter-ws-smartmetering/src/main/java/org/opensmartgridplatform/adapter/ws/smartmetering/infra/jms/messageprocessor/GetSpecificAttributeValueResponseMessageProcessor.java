/**
 * Copyright 2014-2016 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.ws.smartmetering.infra.jms.messageprocessor;

import org.opensmartgridplatform.shared.infra.jms.MessageType;
import org.springframework.stereotype.Component;

@Component
public class GetSpecificAttributeValueResponseMessageProcessor
    extends DomainResponseMessageProcessor {

  public GetSpecificAttributeValueResponseMessageProcessor() {
    super(MessageType.GET_SPECIFIC_ATTRIBUTE_VALUE);
  }
}
