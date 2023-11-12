package com.example.platformerp.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class AbstractValidator<E, REP> {
    protected REP repository;
    public void validate(E entity) {
        log.info("validating " + entity.getClass().getName());

        log.info("validated " + entity.getClass().getName());
    }
}
