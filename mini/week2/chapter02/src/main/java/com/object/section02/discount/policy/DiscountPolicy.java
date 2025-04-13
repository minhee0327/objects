package com.object.section02.discount.policy;

import com.object.section02.Money;
import com.object.section02.Screening;
import com.object.section02.discount.condition.DiscountCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.object.section02.Money.ZERO;

// TemplateMethod Pattern: 부모 클래스에 기본 알고리즘 흐름 구현 + 중간에 필요한 처리는 자식 클래스에 위임
public abstract class DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition... condition) {
        this.conditions = Arrays.asList(condition);
    }

    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition condition : conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
