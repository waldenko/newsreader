package com.dwalczak.newsreader.service.validator;

import com.dwalczak.newsreader.service.NewsService;
import com.dwalczak.newsreader.service.dto.ArticleFilter;
import com.dwalczak.newsreader.service.dto.ArticleSourceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ArticleSourceFilterValidator implements ConstraintValidator<IsCorrectArticleSourceFilter, ArticleSourceFilter> {

    private final NewsService newsService;

    @Autowired public ArticleSourceFilterValidator(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override public void initialize(IsCorrectArticleSourceFilter constraintAnnotation) {

    }

    @Override public boolean isValid(ArticleSourceFilter value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean isValid = true;
        if (!"pl".equals(value.getCountry())) {
            context.buildConstraintViolationWithTemplate("Country must be equal 'pl'")
                    .addPropertyNode("country").addConstraintViolation();
            isValid = false;
        }
        if (!newsService.getCategories().contains(value.getCategory())) {
            context.buildConstraintViolationWithTemplate("Category must be one of value: " + newsService.getCategories())
                    .addPropertyNode("category").addConstraintViolation();
            isValid = false;
        }
        if (!isValid) {
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }

}
