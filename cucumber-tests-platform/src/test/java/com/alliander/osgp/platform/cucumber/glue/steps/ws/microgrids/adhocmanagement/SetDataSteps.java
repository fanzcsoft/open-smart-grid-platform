/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.platform.cucumber.glue.steps.ws.microgrids.adhocmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alliander.osgp.adapter.ws.schema.microgrids.adhocmanagement.SetDataAsyncRequest;
import com.alliander.osgp.adapter.ws.schema.microgrids.adhocmanagement.SetDataAsyncResponse;
import com.alliander.osgp.adapter.ws.schema.microgrids.adhocmanagement.SetDataRequest;
import com.alliander.osgp.adapter.ws.schema.microgrids.adhocmanagement.SetDataResponse;
import com.alliander.osgp.platform.cucumber.Defaults;
import com.alliander.osgp.platform.cucumber.Keys;
import com.alliander.osgp.platform.cucumber.GlueBase;
import com.alliander.osgp.platform.cucumber.core.ScenarioContext;
import com.alliander.osgp.platform.cucumber.helpers.SettingsHelper;
import com.alliander.osgp.platform.cucumber.support.ws.microgrids.adhocmanagement.AdHocManagementClient;
import com.alliander.osgp.platform.cucumber.support.ws.microgrids.adhocmanagement.SetDataRequestBuilder;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SetDataSteps extends GlueBase {

    @Autowired
    private AdHocManagementClient client;

    @When("^a set data request is received$")
    public void aSetDataRequestIsReceived(final Map<String, String> requestParameters) throws Throwable {
        final String organizationIdentification = (String) ScenarioContext.Current()
                .get(Keys.KEY_ORGANIZATION_IDENTIFICATION, Defaults.DEFAULT_ORGANIZATION_IDENTIFICATION);
        ScenarioContext.Current().put(Keys.KEY_ORGANIZATION_IDENTIFICATION, organizationIdentification);
        final String userName = (String) ScenarioContext.Current().get(Keys.KEY_USER_NAME, Defaults.DEFAULT_USER_NAME);
        ScenarioContext.Current().put(Keys.KEY_USER_NAME, userName);

        final SetDataRequest setDataRequest = SetDataRequestBuilder.fromParameterMap(requestParameters);
        final SetDataAsyncResponse response = this.client.setDataAsync(setDataRequest);

        ScenarioContext.Current().put(Keys.KEY_CORRELATION_UID, response.getAsyncResponse().getCorrelationUid());
    }

    @Then("^the set data response should be returned$")
    public void theSetDataResponseShouldBeReturned(final Map<String, String> responseParameters) throws Throwable {

        final String correlationUid = (String) ScenarioContext.Current().get(Keys.KEY_CORRELATION_UID);
        final Map<String, String> extendedParameters = SettingsHelper.addDefault(responseParameters,
                Keys.KEY_CORRELATION_UID, correlationUid);

        final SetDataAsyncRequest setDataAsyncRequest = SetDataRequestBuilder.fromParameterMapAsync(extendedParameters);
        final SetDataResponse response = this.client.setData(setDataAsyncRequest);

        final String expectedResult = responseParameters.get(Keys.KEY_RESULT);
        assertNotNull("Result", response.getResult());
        assertEquals("Result", expectedResult, response.getResult().name());
    }
}
