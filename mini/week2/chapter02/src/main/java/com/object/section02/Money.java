package com.object.section02;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public  Money plus(long amount){
        return new Money(this.amount.add(BigDecimal.valueOf(amount)));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money times(double percentage) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percentage)));
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }
}
