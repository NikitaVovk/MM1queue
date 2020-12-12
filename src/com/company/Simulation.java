package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    int lambda;
    int mi;
    double ro ;
    int maxSimulationTime;
    double clock;
    int servicedEvents;
    double pZeroTime;
    double serviceTime;
    int  eventsInBuffor;
    String []eventType =  {"PRZYJSCIE_REAL","PRZYJSCIE_IMAG"};
    ArrayList<Double>arrivalTimes;
    ArrayList<Double>startServiceTimes ;
    ArrayList<Double>listOfPZeroTimes;
    ArrayList<Double>listTimes;
    ArrayList<Double>listCountEventsInBuf;
    EventList eventList;


    public Simulation(int lambda, int mi, int maxSimulationTime) {
        this.lambda = lambda;
        this.mi = mi;
        this.maxSimulationTime = maxSimulationTime;
        this.ro = (double)lambda/mi;
        this.clock = 0;
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
    public void run(){

        double timeSpace = exponential( lambda );

        while (clock<=maxSimulationTime){
           // eventList.showList();

            if (eventList.getLast().arrivalTime<maxSimulationTime)
                eventList.put(new Event(eventType[0],timeSpace,exponential(mi),eventList.getLast()));


            timeSpace = exponential( lambda )+ eventList.getLast().arrivalTime;
            eventsInBuffor = 0;
            Event iteratorEvent = eventList.head;

            while (iteratorEvent!=null){
                if (iteratorEvent.arrivalTime<clock)
                    eventsInBuffor++;
                iteratorEvent=iteratorEvent.nextEvent;
            }


            listTimes.add(clock);
            listCountEventsInBuf.add((double)eventsInBuffor);


            if (clock>=eventList.head.arrivalTime){

                Event event = eventList.get();
                eventsInBuffor-=1;
                servicedEvents+=1;
                serviceTime+=event.serviceTime;
                arrivalTimes.add(event.arrivalTime);
                startServiceTimes.add(clock);
                clock+=event.serviceTime;
            }
            else {
                pZeroTime+=eventList.head.arrivalTime-clock;
                clock = eventList.head.arrivalTime;
                listOfPZeroTimes.add(pZeroTime/clock);
            }


        }
    }

    double exponential(double lambda) {
        Random r = new Random();
        // System.out.println("RANDOM"+r.nextDouble());
        double x = Math.log(1-r.nextDouble())/(-lambda);
        return x;
    }


}
