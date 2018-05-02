package com.cafe24.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface Auth {
	public enum Role {
		ADMIN,
		USER
	}
	public Role role() default Role.USER;
}
