/**
 * Copyright 2018 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.domain.smartmetering.infra.jms.ws.messageprocessors;

import org.opensmartgridplatform.adapter.domain.smartmetering.application.services.ConfigurationService;
import org.opensmartgridplatform.adapter.domain.smartmetering.infra.jms.BaseRequestMessageProcessor;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.GetMbusEncryptionKeyStatusByChannelRequestData;
import org.opensmartgridplatform.shared.exceptionhandling.FunctionalException;
import org.opensmartgridplatform.shared.infra.jms.DeviceMessageMetadata;
import org.opensmartgridplatform.shared.infra.jms.MessageProcessorMap;
import org.opensmartgridplatform.shared.infra.jms.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GetMbusEncryptionKeyStatusByChannelRequestMessageProcessor
    extends BaseRequestMessageProcessor {

  @Autowired
  @Qualifier("domainSmartMeteringConfigurationService")
  private ConfigurationService configurationService;

  @Autowired
  protected GetMbusEncryptionKeyStatusByChannelRequestMessageProcessor(
      @Qualifier("domainSmartMeteringInboundWebServiceRequestsMessageProcessorMap")
          MessageProcessorMap messageProcessorMap) {
    super(messageProcessorMap, MessageType.GET_MBUS_ENCRYPTION_KEY_STATUS_BY_CHANNEL);
  }

  @Override
  protected void handleMessage(
      final DeviceMessageMetadata deviceMessageMetadata, final Object dataObject)
      throws FunctionalException {

    final GetMbusEncryptionKeyStatusByChannelRequestData
        getMbusEncryptionKeyStatusByChannelRequest =
            (GetMbusEncryptionKeyStatusByChannelRequestData) dataObject;
    this.configurationService.getMbusEncryptionKeyStatusByChannel(
        deviceMessageMetadata, getMbusEncryptionKeyStatusByChannelRequest);
  }
}
