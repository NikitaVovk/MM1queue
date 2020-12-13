package com.company;

public class EventList {
    Event head;

    public EventList(Event head) {
        this.head = head;
    }

    public void put(Event event){
        //wstaw kolejne zdarzenie na koniec listy jeśli przyszedł prawdziwy klient
        if (event.typ.equals("REAL_CLIENT")){
        Event last = this.getLast();
        event.prevEvent = last;
        last.nextEvent=event;
        }
        //wstaw kolejne zdarzenie na początek listy jeśli przyszedł wyimaginowany klient
        else if (event.typ.equals("IMAGINE_CLIENT")){
            event.nextEvent=head;
            head.prevEvent=event;
            head=event;
        }
    }

    public Event get(){ //pobierz i usuń pierwsze zdarzenie z listy
        Event bufHead=head;
        head = head.nextEvent;
        head.prevEvent = null;
        return bufHead;

    }

    public Event getLast(){ //pobierz ostatni elemnt z listy
        Event iteratorEvent = head;
        while(iteratorEvent.nextEvent!=null)
            iteratorEvent=iteratorEvent.nextEvent;
        return iteratorEvent;
    }

    void showList(){ //wyswietl listę
        Event iteratorEvent = head;
        while(iteratorEvent!=null) {
            System.out.println("T przybycia "+iteratorEvent.arrivalTime+" T obslugi "+iteratorEvent.serviceTime);
            iteratorEvent = iteratorEvent.nextEvent;
        }
        System.out.println("\n\n");
    }

}
