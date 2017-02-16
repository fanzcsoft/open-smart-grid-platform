/**
 * Copyright 2014-2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */

package com.alliander.osgp.adapter.ws.smartmetering.application.mapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alliander.osgp.adapter.ws.schema.smartmetering.common.CaptureObject;
import com.alliander.osgp.adapter.ws.schema.smartmetering.common.OsgpUnitType;
import com.alliander.osgp.adapter.ws.schema.smartmetering.common.ProfileEntryValue;
import com.alliander.osgp.adapter.ws.schema.smartmetering.monitoring.ProfileGenericDataResponse;
import com.alliander.osgp.domain.core.valueobjects.smartmetering.CaptureObjectVo;
import com.alliander.osgp.domain.core.valueobjects.smartmetering.ObisCodeValues;
import com.alliander.osgp.domain.core.valueobjects.smartmetering.ProfileEntriesVo;
import com.alliander.osgp.domain.core.valueobjects.smartmetering.ProfileEntryValueVo;
import com.alliander.osgp.domain.core.valueobjects.smartmetering.ProfileGenericDataResponseVo;

public class ProfileGenericDataResponseMappingTest {

    private MonitoringMapper monitoringMapper = new MonitoringMapper();

    @Test
    public void testProfileGenericDataResponse() {
        ProfileGenericDataResponseVo voResponse = this.makeresponseVo();
        ProfileGenericDataResponse wsResponse = this.monitoringMapper.map(voResponse, ProfileGenericDataResponse.class);
        Assert.assertTrue(wsResponse != null && wsResponse instanceof ProfileGenericDataResponse);
    }

    @Test
    public void testCaptureObject() {
        CaptureObjectVo captureObjectVo = this.makeCaptureObjectVo();
        CaptureObject captureObject = this.monitoringMapper.map(captureObjectVo, CaptureObject.class);
        Assert.assertTrue(captureObject != null);
    }

    @Test
    public void testProfileEntry() {
        ProfileEntryValueVo profileEntryValueVo = new ProfileEntryValueVo("test", null, null, null);
        ProfileEntryValue profileEntryValue = this.monitoringMapper.map(profileEntryValueVo, ProfileEntryValue.class);
        Assert.assertTrue(profileEntryValue.getStringValueOrDateValueOrFloatValue().get(0).equals("test"));

        profileEntryValueVo = new ProfileEntryValueVo(null, new Date(), null, null);
        profileEntryValue = this.monitoringMapper.map(profileEntryValueVo, ProfileEntryValue.class);
        Assert.assertTrue(profileEntryValue.getStringValueOrDateValueOrFloatValue().get(0) instanceof Date);

        profileEntryValueVo = new ProfileEntryValueVo(null, null, new BigDecimal(100.0d), null);
        profileEntryValue = this.monitoringMapper.map(profileEntryValueVo, ProfileEntryValue.class);
        Assert.assertTrue(((BigDecimal) profileEntryValue.getStringValueOrDateValueOrFloatValue().get(0)).doubleValue() == 100.d);

        profileEntryValueVo = new ProfileEntryValueVo(null, null, null, 12345L);
        profileEntryValue = this.monitoringMapper.map(profileEntryValueVo, ProfileEntryValue.class);
        Assert.assertTrue(((Long) profileEntryValue.getStringValueOrDateValueOrFloatValue().get(0)).doubleValue() == 12345L);
    }

    private ProfileGenericDataResponseVo makeresponseVo() {
        List<CaptureObjectVo> captureObjects = new ArrayList<CaptureObjectVo>();
        captureObjects.add(this.makeCaptureObjectVo());
        List<ProfileEntriesVo> profileEntries = new ArrayList<ProfileEntriesVo>();
        profileEntries.add(this.makeProfileEntryVo());
        profileEntries.add(this.makeProfileEntryVo());
        ProfileGenericDataResponseVo result = new ProfileGenericDataResponseVo(this.makeObisCode(), captureObjects,
                profileEntries);
        return result;
    }

    private ObisCodeValues makeObisCode() {
        return new ObisCodeValues((byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1);
    }

    private CaptureObjectVo makeCaptureObjectVo() {
        return new CaptureObjectVo(10L, "0.0.1.0.0.255", 10, 1, OsgpUnitType.UNDEFINED.name());
    }

    private ProfileEntriesVo makeProfileEntryVo() {
        List<ProfileEntryValueVo> entriesVo = new ArrayList<ProfileEntryValueVo>();
        entriesVo.add(new ProfileEntryValueVo("test", null, null, null));
        return new ProfileEntriesVo(entriesVo);
    }

}
