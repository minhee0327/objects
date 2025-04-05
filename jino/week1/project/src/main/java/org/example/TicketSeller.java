package org.example;

public class TicketSeller {

    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        Long amount = audience.buy(ticketOffice.getTicket());
        ticketOffice.plusAmount(amount);
    }

}
