package com.object.section02.discount.policy;

import com.object.section02.Money;
import com.object.section02.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
