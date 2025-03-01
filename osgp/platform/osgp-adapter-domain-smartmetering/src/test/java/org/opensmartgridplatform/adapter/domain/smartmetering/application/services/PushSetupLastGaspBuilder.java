/*
 * Copyright 2022 Alliander.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.domain.smartmetering.application.services;

import java.util.ArrayList;
import java.util.List;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.CosemObisCode;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.CosemObjectDefinition;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.MessageType;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.PushSetupLastGasp;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.SendDestinationAndMethod;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.TransportServiceType;
import org.opensmartgridplatform.domain.core.valueobjects.smartmetering.WindowElement;

public class PushSetupLastGaspBuilder {

  private CosemObisCode logicalName = new CosemObisCode(new int[] {1, 2, 3, 4, 5, 6});
  private TransportServiceType transportServiceType = TransportServiceType.UDP;
  private MessageType messageType = MessageType.A_XDR_ENCODED_X_DLMS_APDU;
  private SendDestinationAndMethod sendDestinationAndMethod =
      new SendDestinationAndMethod(this.transportServiceType, "destination", this.messageType);
  private Integer randomisationStartInterval = new Integer(1);
  private Integer numberOfRetries = new Integer(10);
  private Integer repetitionDelay = new Integer(2);

  private List<CosemObjectDefinition> pushObjectList;
  private List<WindowElement> communicationWindow;

  public PushSetupLastGasp build() {

    final PushSetupLastGasp.Builder pushSetupLastGaspBuilder = new PushSetupLastGasp.Builder();
    pushSetupLastGaspBuilder
        .withLogicalName(this.logicalName)
        .withPushObjectList(this.pushObjectList)
        .withSendDestinationAndMethod(this.sendDestinationAndMethod)
        .withCommunicationWindow(this.communicationWindow)
        .withRandomisationStartInterval(this.randomisationStartInterval)
        .withNumberOfRetries(this.numberOfRetries)
        .withRepetitionDelay(this.repetitionDelay);
    return pushSetupLastGaspBuilder.build();
  }

  public PushSetupLastGaspBuilder withNullValues() {
    this.logicalName = null;
    this.pushObjectList = null;
    this.sendDestinationAndMethod = null;
    this.communicationWindow = null;
    this.randomisationStartInterval = null;
    this.numberOfRetries = null;
    this.repetitionDelay = null;
    return this;
  }

  public PushSetupLastGaspBuilder withEmptyLists(
      final ArrayList<CosemObjectDefinition> pushObjectList,
      final ArrayList<WindowElement> communicationWindow) {
    this.pushObjectList = pushObjectList;
    this.communicationWindow = communicationWindow;
    return this;
  }

  public PushSetupLastGaspBuilder withFilledLists(
      final CosemObjectDefinition cosemObjectDefinition, final WindowElement windowElement) {
    this.pushObjectList = new ArrayList<>();
    this.pushObjectList.add(cosemObjectDefinition);
    this.communicationWindow = new ArrayList<>();
    this.communicationWindow.add(windowElement);
    return this;
  }
}
