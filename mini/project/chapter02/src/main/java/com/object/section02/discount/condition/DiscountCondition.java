package com.object.section02.discount.condition;

import com.object.section02.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
