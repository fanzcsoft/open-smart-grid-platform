/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.jasper.sessionproviders.exceptions;

import org.opensmartgridplatform.shared.exceptionhandling.ComponentType;
import org.opensmartgridplatform.shared.exceptionhandling.OsgpException;

public class SessionProviderException extends OsgpException {

  /** Serial Version UID. */
  private static final long serialVersionUID = -5449632007365870329L;

  public SessionProviderException(final String message) {
    super(ComponentType.PROTOCOL_DLMS, message);
  }

  public SessionProviderException(final String message, final Throwable throwable) {
    super(ComponentType.PROTOCOL_DLMS, message, throwable);
  }
}
