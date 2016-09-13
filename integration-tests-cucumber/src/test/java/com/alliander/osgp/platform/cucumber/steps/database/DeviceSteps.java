/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.platform.cucumber.steps.database;

import static com.alliander.osgp.platform.cucumber.core.Helpers.getBoolean;
import static com.alliander.osgp.platform.cucumber.core.Helpers.getDate;
import static com.alliander.osgp.platform.cucumber.core.Helpers.getEnum;
import static com.alliander.osgp.platform.cucumber.core.Helpers.getFloat;
import static com.alliander.osgp.platform.cucumber.core.Helpers.getLong;
import static com.alliander.osgp.platform.cucumber.core.Helpers.getString;

import java.net.InetAddress;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alliander.osgp.domain.core.entities.Device;
import com.alliander.osgp.domain.core.entities.DeviceAuthorization;
import com.alliander.osgp.domain.core.entities.DeviceModel;
import com.alliander.osgp.domain.core.entities.Organisation;
import com.alliander.osgp.domain.core.repositories.DeviceAuthorizationRepository;
import com.alliander.osgp.domain.core.repositories.DeviceModelRepository;
import com.alliander.osgp.domain.core.repositories.DeviceRepository;
import com.alliander.osgp.domain.core.repositories.OrganisationRepository;
import com.alliander.osgp.domain.core.repositories.ProtocolInfoRepository;
import com.alliander.osgp.domain.core.valueobjects.DeviceFunctionGroup;
import com.alliander.osgp.platform.cucumber.core.ScenarioContext;
import com.alliander.osgp.platform.cucumber.steps.Defaults;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@Transactional("txMgrCore")
public class DeviceSteps {

    public static String DEFAULT_DEVICE_IDENTIFICATION = "test-device";
    public static String DEFAULT_DEVICE_TYPE = "OSLP";
    public static String DEFAULT_PROTOCOL = "OSLP";
    public static String DEFAULT_PROTOCOL_VERSION = "1.0";
    private Long DEFAULT_DEVICE_ID = new java.util.Random().nextLong();
    private Boolean DEFAULT_IS_ACTIVATED = true;
    private Boolean DEFAULT_ACTIVE = true;
    private String DEFAULT_ALIAS = "";
    private String DEFAULT_CONTAINER_CITY = "";
    private String DEFAULT_CONTAINER_POSTALCODE = "";
    private String DEFAULT_CONTAINER_STREET = "";
    private String DEFAULT_CONTAINER_NUMBER = "";
    private String DEFAULT_CONTAINER_MUNICIPALITY = "";
    private Float DEFAULT_LATITUDE = new Float(0);
    private Float DEFAULT_LONGITUDE = new Float(0);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private OrganisationRepository organizationRepository;

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @Autowired
    private DeviceAuthorizationRepository deviceAuthorizationRepository;

    @Autowired
    private ProtocolInfoRepository protocolInfoRepository;

    /**
     * Generic method which adds a device using the settings.
     *
     * @param settings
     *            The settings for the device to be used.
     * @throws Throwable
     */
    @Given("^a device$")
    public void aDevice(final Map<String, String> settings) throws Throwable {

        // Set the required stuff
        final String deviceIdentification = settings.get("DeviceIdentification");
        Device device = new Device(deviceIdentification);

        // Now set the optional stuff
        device.setId(getLong(settings, "DeviceId", this.DEFAULT_DEVICE_ID));
        device.setActivated(getBoolean(settings, "IsActivated", this.DEFAULT_IS_ACTIVATED));
        device.setTechnicalInstallationDate(getDate(settings, "TechnicalInstallationDate").toDate());

        final DeviceModel deviceModel = this.deviceModelRepository
                .findByModelCode(getString(settings, "DeviceModel", Defaults.DEFAULT_DEVICE_MODEL_MODEL_CODE));
        device.setDeviceModel(deviceModel);

        // TODO: add protocol information in controlled place
        device.updateProtocol(this.protocolInfoRepository.findByProtocolAndProtocolVersion(
                getString(settings, "Protocol", this.DEFAULT_PROTOCOL),
                getString(settings, "ProtocolVersion", this.DEFAULT_PROTOCOL_VERSION)));

        device.updateRegistrationData(InetAddress.getLoopbackAddress(),
                getString(settings, "DeviceType", this.DEFAULT_DEVICE_TYPE));

        device.setVersion(getLong(settings, "Version"));
        device.setActive(getBoolean(settings, "Active", this.DEFAULT_ACTIVE));
        device.addOrganisation(getString(settings, "Organization", Defaults.DEFAULT_ORGANIZATION_IDENTIFICATION));
        device.updateMetaData(getString(settings, "alias", this.DEFAULT_ALIAS),
                getString(settings, "containerCity", this.DEFAULT_CONTAINER_CITY),
                getString(settings, "containerPostalCode", this.DEFAULT_CONTAINER_POSTALCODE),
                getString(settings, "containerStreet", this.DEFAULT_CONTAINER_STREET),
                getString(settings, "containerNumber", this.DEFAULT_CONTAINER_NUMBER),
                getString(settings, "containerMunicipality", this.DEFAULT_CONTAINER_MUNICIPALITY),
                getFloat(settings, "gpsLatitude", this.DEFAULT_LATITUDE),
                getFloat(settings, "gpsLongitude", this.DEFAULT_LONGITUDE));

        device = this.deviceRepository.save(device);

        final Organisation organization = this.organizationRepository.findByOrganisationIdentification(
                getString(settings, "OrganizationIdentification", Defaults.DEFAULT_ORGANIZATION_IDENTIFICATION));

        final DeviceFunctionGroup functionGroup = getEnum(settings, "DeviceFunctionGroup", DeviceFunctionGroup.class,
                DeviceFunctionGroup.OWNER);

        final DeviceAuthorization authorization = device.addAuthorization(organization, functionGroup);
        Device savedDevice = this.deviceRepository.save(device);
        this.deviceAuthorizationRepository.save(authorization);
        
        ScenarioContext.Current().put("DeviceIdentification", savedDevice.getDeviceIdentification());
    }

    /**
     *
     * @throws Throwable
     */
    @Then("^the device with device identification \"([^\"]*)\" should be active$")
    public void theDeviceWithDeviceIdentificationShouldBeActive(final String deviceIdentification) throws Throwable {
        final Device device = this.deviceRepository.findByDeviceIdentification(deviceIdentification);

        Assert.assertTrue(device.isActive());
    }

    /**
     *
     * @param deviceIdentification
     * @throws Throwable
     */
    @Then("^the device with device identification \"([^\"]*)\" should be inactive$")
    public void theDeviceWithDeviceIdentificationShouldBeInActive(final String deviceIdentification) throws Throwable {
        final Device device = this.deviceRepository.findByDeviceIdentification(deviceIdentification);
        Assert.assertFalse(device.isActive());
    }
}
