package com.company;

public class EventList {
    Event head;

    public EventList(Event head) {
        this.head = head;
    }

    public void put(Event event){
        Event last = this.getLast();
        event.prevEvent = last;
        last.nextEvent=event;
    }

    public Event get(){
        Event bufHead=head;
        head = head.nextEvent;
        head.prevEvent = null;
        return bufHead;

    }

    public Event getLast(){
        Event iteratorEvent = head;
        while(iteratorEvent.nextEvent!=null)
            iteratorEvent=iteratorEvent.nextEvent;
        return iteratorEvent;
    }

    void showList(){
        Event iteratorEvent = head;
        while(iteratorEvent!=null) {
            System.out.println("T przybycia "+iteratorEvent.arrivalTime+" T obslugi "+iteratorEvent.serviceTime);
            iteratorEvent = iteratorEvent.nextEvent;
        }
        System.out.println("\n\n");
    }

}
