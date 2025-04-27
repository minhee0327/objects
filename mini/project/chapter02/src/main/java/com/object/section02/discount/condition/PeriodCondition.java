package com.object.section02.discount.condition;

import com.object.section02.Screening;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
                (startTime.isBefore(screening.getStartTime().toLocalTime()) || startTime.equals(screening.getStartTime().toLocalTime())) &&
                (endTime.isAfter(screening.getStartTime().toLocalTime()) || endTime.equals(screening.getStartTime().toLocalTime()));
    }
}
