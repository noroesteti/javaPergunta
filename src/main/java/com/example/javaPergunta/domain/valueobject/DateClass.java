package com.example.javaPergunta.domain.valueobject;

import java.time.Instant;
import java.time.ZoneId;

public class DateClass {
    private final Instant dateTime;

    public DateClass(){
        this.dateTime = Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
    }
    public Instant getDateTime(){
        return dateTime;
    }
}
