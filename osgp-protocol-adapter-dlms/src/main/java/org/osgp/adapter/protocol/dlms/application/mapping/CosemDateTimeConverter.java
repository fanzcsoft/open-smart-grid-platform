/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.osgp.adapter.protocol.dlms.application.mapping;

import java.util.Set;

import org.openmuc.jdlms.datatypes.CosemDateFormat;
import org.openmuc.jdlms.datatypes.CosemDateTime.ClockStatus;

import com.alliander.osgp.dto.valueobjects.smartmetering.CosemDateDto;
import com.alliander.osgp.dto.valueobjects.smartmetering.CosemDateTimeDto;
import com.alliander.osgp.dto.valueobjects.smartmetering.CosemTimeDto;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class CosemDateTimeConverter
        extends BidirectionalConverter<CosemDateTimeDto, org.openmuc.jdlms.datatypes.CosemDateTime> {

    @Override
    public org.openmuc.jdlms.datatypes.CosemDateTime convertTo(final CosemDateTimeDto source,
            final Type<org.openmuc.jdlms.datatypes.CosemDateTime> destinationType) {

        final CosemTimeDto time = source.getTime();
        final CosemDateDto date = source.getDate();
        final Set<ClockStatus> clockStatus = ClockStatus.clockStatusFrom((byte) source.getClockStatus().getStatus());

        return new org.openmuc.jdlms.datatypes.CosemDateTime(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                date.getDayOfWeek(), time.getHour(), time.getMinute(), time.getSecond(), time.getHundredths(),
                source.getDeviation(), clockStatus.toArray(new ClockStatus[clockStatus.size()]));
    }

    @Override
    public CosemDateTimeDto convertFrom(final org.openmuc.jdlms.datatypes.CosemDateTime source,
            final Type<CosemDateTimeDto> destinationType) {
        if (source == null) {
            return null;
        }

        final int year = source.get(CosemDateFormat.Field.YEAR);
        final int month = source.get(CosemDateFormat.Field.MONTH);
        final int dayOfMonth = source.get(CosemDateFormat.Field.DAY_OF_MONTH);
        final int dayOfWeek = source.get(CosemDateFormat.Field.DAY_OF_WEEK);
        final com.alliander.osgp.dto.valueobjects.smartmetering.CosemDateDto date = new com.alliander.osgp.dto.valueobjects.smartmetering.CosemDateDto(
                year, month, dayOfMonth, dayOfWeek);

        final int hour = source.get(CosemDateFormat.Field.HOUR);
        final int minute = source.get(CosemDateFormat.Field.MINUTE);
        final int second = source.get(CosemDateFormat.Field.SECOND);
        final int hundredths = source.get(CosemDateFormat.Field.HUNDREDTHS);
        final com.alliander.osgp.dto.valueobjects.smartmetering.CosemTimeDto time = new com.alliander.osgp.dto.valueobjects.smartmetering.CosemTimeDto(
                hour, minute, second, hundredths);

        final int deviation = source.get(CosemDateFormat.Field.DEVIATION);

        final int clockStatusValue = source.get(CosemDateFormat.Field.CLOCK_STATUS);
        final com.alliander.osgp.dto.valueobjects.smartmetering.ClockStatusDto clockStatus = new com.alliander.osgp.dto.valueobjects.smartmetering.ClockStatusDto(
                clockStatusValue);

        return new com.alliander.osgp.dto.valueobjects.smartmetering.CosemDateTimeDto(date, time, deviation,
                clockStatus);
    }

}
