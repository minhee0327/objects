package org.example.section01;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // Prob1. audience, ticketSeller 수동적 (아무나 접근해서 티켓, 돈에 접근 가능??)
    // Prob2. 하나의 메서드, 클래스에 너무 많은 정보가 들어있어서 이해하기 어려움
    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            // Prob3.  audience, ticketSeller 수정시 같이 수정 해야 함 (변경 어려움)
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
