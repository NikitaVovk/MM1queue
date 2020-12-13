package com.company;

public class Event {
    String typ;
    double arrivalTime; //czas przybycia
    double serviceTime; //czas obsługi
    Event nextEvent=null; //wskażnik na następne zdarzenie z listy
    Event prevEvent=null; //wskażnik na poprzednie zdarzenie z listy

    public Event(String typ, double czasPrzyjscia, double czasObslugi) {
        this.typ = typ;
        this.arrivalTime = czasPrzyjscia;
        this.serviceTime = czasObslugi;
    }

}
