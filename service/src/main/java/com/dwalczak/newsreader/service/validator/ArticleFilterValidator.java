package com.dwalczak.newsreader.service.validator;

import com.dwalczak.newsreader.service.dto.ArticleFilter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArticleFilterValidator implements ConstraintValidator<IsCorrectArticleFilter, ArticleFilter> {

    @Override public void initialize(IsCorrectArticleFilter constraintAnnotation) {

    }

    @Override public boolean isValid(ArticleFilter value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean isValid = true;
        if (!"pl".equals(value.getCountry())) {
            context.buildConstraintViolationWithTemplate("Country must equal 'pl'")
                    .addPropertyNode("country").addConstraintViolation();
            isValid = false;
        }
        if (!"technology".equals(value.getCategory())) {
            context.buildConstraintViolationWithTemplate("Category must equal 'technology'")
                    .addPropertyNode("category").addConstraintViolation();
            isValid = false;
        }
        if (value.getPaginationFilter().getPageNumber() <= 0) {
            context.buildConstraintViolationWithTemplate("PageNumber must be greater than 0")
                    .addPropertyNode("pageNumber").addConstraintViolation();
            isValid = false;
        }
        if (value.getPaginationFilter().getPageSize() <= 0) {
            context.buildConstraintViolationWithTemplate("PageSize must be greater than 0")
                    .addPropertyNode("pageSize").addConstraintViolation();
            isValid = false;
        }
        if (!isValid) {
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }

}
