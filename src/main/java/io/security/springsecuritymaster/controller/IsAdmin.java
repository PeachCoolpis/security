package io.security.springsecuritymaster.controller;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
public @interface IsAdmin {
}
