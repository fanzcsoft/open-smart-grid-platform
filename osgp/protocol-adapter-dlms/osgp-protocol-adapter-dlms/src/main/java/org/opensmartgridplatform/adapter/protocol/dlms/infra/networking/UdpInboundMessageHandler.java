/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.opensmartgridplatform.adapter.protocol.dlms.infra.networking;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.opensmartgridplatform.dlms.DlmsPushNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;

@Slf4j
@MessageEndpoint
public class UdpInboundMessageHandler {

  private static final String IP_ADDRESS = "ip_address";

  @Autowired private PushedMessageProcessor pushedMessageProcessor;

  @ServiceActivator(inputChannel = "inboundChannel")
  public void handeMessage(final Message message, @Headers final Map<String, Object> headerMap)
      throws UnrecognizedMessageDataException {
    final byte[] payload = (byte[]) message.getPayload();
    log.info("Received UDP message: {}", new String(payload));

    final ByteArrayInputStream inputStream = new ByteArrayInputStream(payload);
    final Smr5AlarmDecoder alarmDecoder = new Smr5AlarmDecoder();
    final DlmsPushNotification dlmsPushNotification = alarmDecoder.decodeSmr5alarm(inputStream);

    final String correlationId = UUID.randomUUID().toString().replace("-", "");
    final String deviceIdentification = dlmsPushNotification.getEquipmentIdentifier();
    final String ipAddress = this.retrieveIpAddress(message, deviceIdentification);

    this.pushedMessageProcessor.process(
        dlmsPushNotification, correlationId, deviceIdentification, ipAddress);
  }

  private String retrieveIpAddress(final Message message, final String deviceIdentification) {
    String ipAddress = null;
    try {
      ipAddress = (String) message.getHeaders().get(IP_ADDRESS);
      log.info(
          "UDP push notification for device {} received from IP address {}",
          deviceIdentification,
          ipAddress);
    } catch (final Exception ex) {
      log.info("Unable to determine IP address of the meter sending a udp push notification: ", ex);
    }
    return ipAddress;
  }
}
