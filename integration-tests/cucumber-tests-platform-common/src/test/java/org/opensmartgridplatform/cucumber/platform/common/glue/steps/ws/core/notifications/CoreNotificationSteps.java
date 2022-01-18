/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.cucumber.platform.common.glue.steps.ws.core.notifications;

import io.cucumber.java.en.Then;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.opensmartgridplatform.adapter.ws.schema.core.notification.Notification;
import org.opensmartgridplatform.cucumber.core.ScenarioContext;
import org.opensmartgridplatform.cucumber.platform.PlatformKeys;
import org.opensmartgridplatform.cucumber.platform.common.support.ws.core.notification.CoreNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CoreNotificationSteps {

  private static final Logger LOGGER = LoggerFactory.getLogger(CoreNotificationSteps.class);

  /*
   * Allow a little more time than the period for the job trigger to re-send
   * notifications.
   */
  private static final int MAX_WAIT_FOR_NOTIFICATION = 65_000;

  @Autowired private CoreNotificationService coreNotificationService;

  @Then("^a notification is sent in ws-core$")
  public void aNotificationIsSent() {
    this.waitForNotification(MAX_WAIT_FOR_NOTIFICATION, true);
  }

  @Then("^no notification is sent in ws-core$")
  public void noNotificationIsSent() {
    this.waitForNotification(MAX_WAIT_FOR_NOTIFICATION, false);
  }

  private void waitForNotification(final int maxTimeOut, final boolean expectNotification) {
    final String correlationUid =
        (String) ScenarioContext.current().get(PlatformKeys.KEY_CORRELATION_UID);
    if (correlationUid == null) {
      Assertions.fail(
          "No "
              + PlatformKeys.KEY_CORRELATION_UID
              + " stored in the scenario context. Unable to make assumptions as to whether a notification has been sent in ws-core.");
    }
    LOGGER.info(
        "Waiting to make sure {} notification is received for correlation UID {} for at most {} milliseconds.",
        expectNotification ? "a" : "no",
        correlationUid,
        maxTimeOut);

    final Notification notification =
        this.coreNotificationService.getNotification(
            correlationUid, maxTimeOut, TimeUnit.MILLISECONDS);

    if (expectNotification && notification == null) {
      Assertions.fail(
          "Did not receive a notification for correlation UID: "
              + correlationUid
              + " within "
              + maxTimeOut
              + " milliseconds");
    }

    if (!expectNotification && notification != null) {
      Assertions.fail("Received notification for correlation UID: " + correlationUid);
    }
  }
}
