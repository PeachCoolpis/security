package io.security.springsecuritymaster.controller;

import org.springframework.security.access.prepost.PostAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@PostAuthorize("returnObject.owner == authentication.name")
public @interface OwnerShip {
}
