package com.microideal.springsecurityoauth2sso.utils;

import org.springframework.data.domain.ExampleMatcher;

public class ExampleMatcherUtils {

    public static ExampleMatcher generateStringContainingAndNullIgnoreMatcher(){
       return ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues();
    }
}
