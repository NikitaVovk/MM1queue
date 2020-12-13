package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    int lambda; //lambda
    int mi; //mi
    double ro ; //ro
    int maxSimulationTime; // maksymalny czas symulacji
    double clock; //zegar
    int runUp; //rozbieg
    int servicedEvents; //liczba obsłużonych klientów
    double pZeroTime; //czas p0
    double serviceTime; //czas obsługi klientów
    int  eventsInBuffor; //ilość klientów w kolejce
    String []eventType =  {"REAL_CLIENT","IMAGINE_CLIENT"}; //typ klienta
    ArrayList<Double>arrivalTimes; //lista czasów przybycia klientów do systemu
    ArrayList<Double>startServiceTimes ; //lista czasów rozpocznięcia obsługi klientów
    ArrayList<Double>listOfPZeroTimes; //lista czasów p0 w różnych stanach systemu
    ArrayList<Double>listTimes; //lista czasów
    ArrayList<Double>listCountEventsInBuf; //lista ilości klientó∑ w buforze w każdej chwili czasu
    EventList eventList;


    public Simulation(int lambda, int mi, int maxSimulationTime, int runUp) { //inicjalizacja paramterów systemu
        this.lambda = lambda;
        this.mi = mi;
        this.maxSimulationTime = maxSimulationTime;
        this.ro = (double)lambda/mi;
        this.clock = 0;
        this.runUp = runUp;
        this.servicedEvents = 0;
        this.pZeroTime = 0;
        this.serviceTime = 0;
        this.eventsInBuffor = 0;
        this.arrivalTimes= new ArrayList<>();
        this.startServiceTimes = new ArrayList<>();
        this.listOfPZeroTimes = new ArrayList<>();
        this.listTimes = new ArrayList<>();
        this.listCountEventsInBuf = new ArrayList<>();
        this.eventList = new EventList(new Event(eventType[0],0,exponential(mi)));;
    }

    public void run(){ //symulacja

        double timeSpace; //pomocnicza wartość do ustawienia czasów przybycia kolejnych pakietów

        while (clock<=maxSimulationTime){   //pętla symulacyjna
           // eventList.showList();
            timeSpace = exponential( lambda )+ eventList.getLast().arrivalTime; //ustawienia odstępów między klientami

            if (eventList.getLast().arrivalTime<maxSimulationTime)
                eventList.put(new Event(eventType[0],timeSpace,exponential(mi),eventList.getLast())); //dodaj nowe zdarzenie do listy



            eventsInBuffor = 0;
            Event iteratorEvent = eventList.head;

            while (iteratorEvent!=null){ //pętla licząca ilość klientów oczekujących na obsługę
                if (iteratorEvent.arrivalTime<clock)
                    eventsInBuffor++;
                iteratorEvent=iteratorEvent.nextEvent;
            }

                listTimes.add(clock);
                listCountEventsInBuf.add((double) eventsInBuffor);

            //jeśli czas jest większy niż czas przybycia pierwszego klienta, to obsłuż go
            if (clock>=eventList.head.arrivalTime){

                Event event = eventList.get(); //pobierz pierwszy element z listy

                //zbieraj statystykę po określonym czasie
                if (runUp<=clock) {
                    servicedEvents += 1;
                    serviceTime += event.serviceTime;
                    arrivalTimes.add(event.arrivalTime);
                    startServiceTimes.add(clock);
                }

                clock+=event.serviceTime; //ustaw aktualny czas, na czas w którym klient został obsłużony
            }
            else {
                pZeroTime+=eventList.head.arrivalTime-clock; // oblicz czas w którym system nic nie robi
                clock = eventList.head.arrivalTime; //ustawinie czasu na czas przybycia nowego klienta
                listOfPZeroTimes.add(pZeroTime/clock); //zapisywania p0 do listy
            }


        }
    }

    double exponential(double lambda) { //generowanie zmiennej o rozkładzie wykladniczym
        Random r = new Random();
        // System.out.println("RANDOM"+r.nextDouble());
        double x = Math.log(1-r.nextDouble())/(-lambda);
        return x;
    }


}
