/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.platform.cucumber.steps;

/**
 * Defaults within the database.
 */
public class Defaults {

    public Defaults() {
    }

	// Labels
	public static final String KEY_ORGANIZATION_IDENTIFICATION =  "OrganizationIdentification";

	// Values
	public static final String DEFAULT_ORGANIZATION_DESCRIPTION = "Test Organization";
	public static final String DEFAULT_ORGANIZATION_IDENTIFICATION = "test-org";
	public static final String DEFAULT_PREFIX = "MAA";
	public static final String DEFAULT_MANUFACTURER_ID = "Test";
	public static final String DEFAULT_MANUFACTURER_NAME = "Test Manufacturer";
	public static final Boolean DEFAULT_MANUFACTURER_USE_PREFIX = false;
	public static final String DEFAULT_DEVICE_MODEL_MODEL_CODE = "TestModel";
	public static final String DEFAULT_DEVICE_MODEL_DESCRIPTION = "Test Model";
    public static final String DEFAULT_DEVICE_IDENTIFICATION = "TD01"; // Test Device  01
	public static final Boolean DEFAULT_DEVICE_MODEL_METERED = true;

	public static final String DLMS_DEFAULT_COMMUNICATION_METHOD = "GPRS";
    public static final Boolean DLMS_DEFAULT_IP_ADDRESS_IS_STATIC = true;
    public static final long DLMS_DEFAULT_PORT = 4059L;
    public static final Boolean DLMS_DEFAULT_HSL5_ACTIVE = true;
    public static final String DLMS_DEFAULT_DEVICE_TYPE = "SMART_METER_E";

    public static final String DEFAULT_DEVICE_TYPE = "OSLP";
    public static final String DEFAULT_PROTOCOL = "OSLP";
    public static final String DEFAULT_PROTOCOL_VERSION = "1.0";
    public static final Long DEFAULT_DEVICE_ID = new java.util.Random().nextLong();
    public static final Boolean DEFAULT_IS_ACTIVATED = true;
    public static final Boolean DEFAULT_ACTIVE = true;
    public static final String DEFAULT_ALIAS = "";
    public static final String DEFAULT_CONTAINER_CITY = "";
    public static final String DEFAULT_CONTAINER_POSTALCODE = "";
    public static final String DEFAULT_CONTAINER_STREET = "";
    public static final String DEFAULT_CONTAINER_NUMBER = "";
    public static final String DEFAULT_CONTAINER_MUNICIPALITY = "";
    public static final Float DEFAULT_LATITUDE = new Float(0);
    public static final Float DEFAULT_LONGITUDE = new Float(0);
    public static final Short DEFAULT_CHANNEL = new Short((short) 1);

    // Expected values
    public static final String EXPECTED_RESULT_OK = "OK";
    public static final String DEFAULT_PERIOD_TYPE = "INTERVAL";
    public static final String DEFAULT_BEGIN_DATE = "";
    public static final String DEFAULT_END_DATE = "";
    public static final Boolean DEFAULT_INDEBUGMODE = false;

    //Types
    public static final String SMART_METER_E = "SMART_METER_E";
    public static final String SMART_METER_G = "SMART_METER_G";

}
