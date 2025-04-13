package com.object.section02;

import com.object.section02.discount.policy.DiscountPolicy;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration duration;
    private Money fee;
    private DiscountPolicy discountPolicy; // Movie <=> DiscountPolicy (합성)

    // Movie는 오직 하나의 DiscountPolicy 만 받도록 강제
    public Movie(String title, Duration duration, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.duration = duration;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    // 다형성, 상속, 추상화 ( 어떤 할인정책 (discountPolicy) 인지 알 수 없음. 고정 정책 ? 비율 정책?)
    public Money calculatedMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

    public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
