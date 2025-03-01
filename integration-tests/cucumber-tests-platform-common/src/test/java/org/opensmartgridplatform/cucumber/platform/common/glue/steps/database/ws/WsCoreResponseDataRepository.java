/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.cucumber.platform.common.glue.steps.database.ws;

import org.opensmartgridplatform.adapter.ws.domain.repositories.ResponseDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WsCoreResponseDataRepository extends ResponseDataRepository {}
