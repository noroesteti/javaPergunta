package com.example.javaPergunta.domain.valueobject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DateClass {
    private final Instant dateTime;
    private static final String UTC = "UTC";

    public DateClass(){
        this.dateTime = Instant.now().atZone(ZoneId.of(UTC)).toInstant();
    }
    public Instant getDateTime(){
        return dateTime;
    }

    public Instant subtract(ChronoUnit unit, int amount){
        return this.dateTime.minus(amount, unit);
    }
    public Instant add(ChronoUnit unit, int amount){
        return this.dateTime.plus(amount, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateClass dateClass = (DateClass) o;
        return Objects.equals(dateTime, dateClass.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}
