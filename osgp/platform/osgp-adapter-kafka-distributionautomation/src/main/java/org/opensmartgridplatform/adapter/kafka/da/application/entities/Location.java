/**
 * Copyright 2021 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.kafka.da.application.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.opensmartgridplatform.shared.domain.entities.AbstractEntity;

import lombok.Data;

@Entity
@Data
public class Location extends AbstractEntity {

    @OneToMany
    private List<Feeder> feederList;

    @Column(length = 32, nullable = false)
    private String substationIdentification;

    @Column(length = 32, nullable = false)
    private String name;
}
