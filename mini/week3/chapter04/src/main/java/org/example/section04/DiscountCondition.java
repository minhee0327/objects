package org.example.section04;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private DiscountConditionType type;

    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    // DiscountConditionType 을 포함한다는 정보를 노출하고 있음.
    public DiscountConditionType getType() {
        return type;
    }

    //DayOfWeek, LocalTime 정보가 인스턴스 변수로 포함돼 있다는 사실을 인터페이스로 외부에 노출
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time){
        if(type != DiscountConditionType.PERIOD){
            throw new IllegalArgumentException();
        }
        return this.dayOfWeek.equals(dayOfWeek) &&
                this.startTime.compareTo(time) <= 0 &&
                this.endTime.compareTo(time) >= 0;
    }

    // 순번 정보 노출중
    public boolean isDiscountable(int sequence){
        if(type != DiscountConditionType.SEQUENCE){
            throw new IllegalArgumentException();
        }
        return sequence == this.sequence;
    }
}
