/*
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.application.threads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import org.opensmartgridplatform.shared.infra.jms.MessageMetadata;

public class RecoverKeyProcessInitiator {
  private ScheduledExecutorService executorService;

  private Provider<RecoverKeyProcess> recoverKeyProcessProvider;

  private int recoverKeyDelay;

  public RecoverKeyProcessInitiator(
      final ScheduledExecutorService executorService,
      final Provider<RecoverKeyProcess> recoverKeyProcessProvider,
      final int recoverKeyDelay) {
    this.executorService = executorService;
    this.recoverKeyProcessProvider = recoverKeyProcessProvider;
    this.recoverKeyDelay = recoverKeyDelay;
  }

  public void initiate(final MessageMetadata messageMetadata, final String deviceIdentification) {
    final RecoverKeyProcess process = this.recoverKeyProcessProvider.get();
    process.setMessageMetadata(messageMetadata);
    process.setDeviceIdentification(deviceIdentification);
    this.executorService.schedule(process, this.recoverKeyDelay, TimeUnit.MILLISECONDS);
  }
}
