/*
 * Copyright 2020 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.kafka.da.infra.jms.messageprocessors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opensmartgridplatform.adapter.kafka.da.infra.kafka.out.StringMessageProducer;
import org.opensmartgridplatform.shared.infra.jms.Constants;
import org.opensmartgridplatform.shared.infra.jms.ResponseMessage;
import org.opensmartgridplatform.shared.infra.jms.ResponseMessageResultType;

@ExtendWith(MockitoExtension.class)
class DomainResponseMessageProcessorTest {

  @InjectMocks DomainResponseMessageProcessor domainResponseMessageProcessor;

  @Mock private StringMessageProducer producer;

  @Mock private ObjectMessage receivedMessage;

  @Test
  void processMessageTest() throws JMSException {
    // Arrange
    final String correlationUid = "CorrelationID-1";
    final String messageType = "GET_DATA";
    final String organisationIdentification = "test-org";
    final String deviceIdentification = "device1";
    final ResponseMessageResultType resultType = ResponseMessageResultType.OK;
    final String topic = "the topic";
    final String payload = "the payload";

    final ResponseMessage responseMessage =
        new ResponseMessage.Builder()
            .withCorrelationUid(correlationUid)
            .withMessageType(messageType)
            .withOrganisationIdentification(organisationIdentification)
            .withDeviceIdentification(deviceIdentification)
            .withTopic(topic)
            .withResult(resultType)
            .withDataObject(payload)
            .build();

    when(this.receivedMessage.getJMSCorrelationID()).thenReturn(correlationUid);
    when(this.receivedMessage.getJMSType()).thenReturn(messageType);
    when(this.receivedMessage.getStringProperty(Constants.ORGANISATION_IDENTIFICATION))
        .thenReturn(organisationIdentification);
    when(this.receivedMessage.getStringProperty(Constants.DEVICE_IDENTIFICATION))
        .thenReturn(deviceIdentification);
    when(this.receivedMessage.getStringProperty(Constants.TOPIC)).thenReturn(topic);
    when(this.receivedMessage.getStringProperty(Constants.RESULT))
        .thenReturn(resultType.toString());
    when(this.receivedMessage.getObject()).thenReturn(responseMessage);

    // Act
    this.domainResponseMessageProcessor.processMessage(this.receivedMessage);

    // Assert
    verify(this.producer, times(1)).send(payload);
  }
}
