package com.company;

public class Event {
    String typ;
    double arrivalTime; //czas przybycia
    double serviceTime; //czas obsługi
    Event nextEvent=null; //wskażnik na następne zdarzenie z listy
    Event prevEvent=null; //wskażnik na poprzednie zdarzenie z listy


    public Event(String typ, double czasPrzyjscia, double czasObslugi, Event prevEvent) {
        this.typ = typ;
        this.arrivalTime = czasPrzyjscia;
        this.serviceTime = czasObslugi;
        this.prevEvent = prevEvent;
    }

    public Event(String typ, double czasPrzyjscia, double czasObslugi) {
        this.typ = typ;
        this.arrivalTime = czasPrzyjscia;
        this.serviceTime = czasObslugi;
    }

    public Event(double czasPrzyjscia) {
        this.arrivalTime = czasPrzyjscia;
    }

    public Event setServiceTime(double czasObslugi){
        this.serviceTime = czasObslugi;
        return this;
    }
    public Event setNextEvent(Event nextEvent){
        this.nextEvent = nextEvent;
        return this;
    }

}
