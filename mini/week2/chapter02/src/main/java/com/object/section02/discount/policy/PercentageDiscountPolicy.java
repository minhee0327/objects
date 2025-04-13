package com.object.section02.discount.policy;

import com.object.section02.Money;
import com.object.section02.Screening;
import com.object.section02.discount.condition.DiscountCondition;

public class PercentageDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentageDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
