/**
 * Copyright 2021 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.ws.smartmetering.application.mapping;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.opensmartgridplatform.domain.core.valueobjects.FirmwareModuleType;
import org.opensmartgridplatform.domain.core.valueobjects.FirmwareVersion;

@Slf4j
public class FirmwareVersionGasConverter extends
        BidirectionalConverter<FirmwareVersion,
                org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareVersionGas> {

    @Override
    public org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareVersionGas convertTo(
            final FirmwareVersion source,
            final Type<org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareVersionGas> destinationType,
            final MappingContext mappingContext) {

        if (source == null) {
            return null;
        }

        final org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareVersionGas firmwareVersion = new org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareVersionGas();
        firmwareVersion.setFirmwareModuleType(
                org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareModuleGasType.valueOf(
                        source.getFirmwareModuleType().getDescription()));
        try {
            firmwareVersion.setVersion(Hex.decodeHex(source.getVersion()));
        } catch (DecoderException e) {
            log.error("Simple version info is not a valid HexString", e);
        }

        return firmwareVersion;
    }

    @Override
    public FirmwareVersion convertFrom(
            final org.opensmartgridplatform.adapter.ws.schema.smartmetering.configuration.FirmwareVersionGas source,
            final Type<FirmwareVersion> destinationType, final MappingContext mappingContext) {

        if (source == null) {
            return null;
        }

        final FirmwareModuleType type = FirmwareModuleType.forDescription(source.getFirmwareModuleType().name());
        final String version = new String(source.getVersion());

        return new FirmwareVersion(type, version);
    }
}
