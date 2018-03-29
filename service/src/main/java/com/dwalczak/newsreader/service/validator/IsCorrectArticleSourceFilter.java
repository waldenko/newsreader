package com.dwalczak.newsreader.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ArticleSourceFilterValidator.class })
public @interface IsCorrectArticleSourceFilter {
    String message() default "Filter is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
