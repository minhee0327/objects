package org.eternity.step03;

import org.eternity.money.Money;

import java.time.LocalDateTime;

// 영화 예매의 책임(p146)
//예매에 대한 정보 전문가  / Reservation 창조자
public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    // Reservation 인스턴스 생성할 책임
    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        //Screening(송신자) 는 송신자(Movie)의 내부 구현에 대한 어떤 지식도 없이 전송할 메세지를 결정할 수 있음. (p147)
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public int getSequence() {
        return sequence;
    }
}
