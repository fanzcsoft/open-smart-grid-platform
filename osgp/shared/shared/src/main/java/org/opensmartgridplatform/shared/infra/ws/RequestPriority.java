/** Copyright 2017 Smart Society Services B.V. */
package org.opensmartgridplatform.shared.infra.ws;

public enum RequestPriority {
  DEFAULT_REQUEST_PRIORITY(4);

  private final int priority;

  RequestPriority(final int priority) {
    this.priority = priority;
  }

  public int getValue() {
    return this.priority;
  }
}
