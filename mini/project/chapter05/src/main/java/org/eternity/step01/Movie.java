package org.eternity.step01;

import org.eternity.money.Money;

import java.time.Duration;
import java.util.List;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money calculateMovieFee(Screening screening) {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }

        return fee;
    }

    //Movie 이 각 DiscountCondition 에 할인 여부를 판단하라는 메세지를 전송한다.
    //DiscountCondition은 이 메시지를 처리하기 위해 isSatisfiedBy 메서드를 구현해야 한다. (p149)
    private boolean isDiscountable(Screening screening) {
        return discountConditions.stream()
                                 .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    private Money calculateDiscountAmount() {
        return switch (movieType) {
            case AMOUNT_DISCOUNT -> calculateAmountDiscountAmount();
            case PERCENT_DISCOUNT -> calculatePercentDiscountAmount();
            case NONE_DISCOUNT -> calculateNoneDiscountAmount();
        };
    }

    private Money calculateAmountDiscountAmount() {
        return discountAmount;
    }

    private Money calculatePercentDiscountAmount() {
        return fee.times(discountPercent);
    }

    private Money calculateNoneDiscountAmount() {
        return Money.ZERO;
    }
}
