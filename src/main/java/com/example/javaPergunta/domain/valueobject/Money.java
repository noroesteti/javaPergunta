package com.example.javaPergunta.domain.valueobject;

import com.sun.jdi.ObjectCollectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new ObjectCollectedException("Amount cannnot be negative");
        }
        this.amount = amount;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public Money add(Money money){
        return new Money(setScale(this.amount.add(money.amount)));
    }

    public Money subtract(Money money){
        if (this.isLowerThan(money)){
            throw new IllegalArgumentException("Insufficient balance");
        }
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

    public boolean isGreaterThan(Money money){
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public boolean isLowerThan(Money money){
        return this.amount != null && this.amount.compareTo(money.getAmount()) < 0;
    }

    private BigDecimal setScale(BigDecimal input){
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}
