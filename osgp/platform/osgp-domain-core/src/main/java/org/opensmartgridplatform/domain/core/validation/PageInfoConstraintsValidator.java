/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.domain.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.opensmartgridplatform.domain.core.valueobjects.PageInfo;

public class PageInfoConstraintsValidator
    implements ConstraintValidator<PageInfoConstraints, PageInfo> {

  private static final String CHECK_CURRENT_LESS_TOTAL =
      "currentPage may not be equal or more then totalPages";

  @Override
  public void initialize(final PageInfoConstraints constraintAnnotation) {
    // Empty Method
  }

  @Override
  public boolean isValid(final PageInfo value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    final ValidatorHelper helper = new ValidatorHelper();

    if (value.getCurrentPage() >= value.getTotalPages()) {
      helper.addMessage(CHECK_CURRENT_LESS_TOTAL);
    }

    return helper.isValid(context);
  }
}
