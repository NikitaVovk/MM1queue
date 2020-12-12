package com.company;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Statistics extends Simulation {


    public Statistics(int lam, int mi, int max_czas_symulacji) {
        super(lam, mi, max_czas_symulacji);
    }

    void saveLogs(){
        File dir = new File("logs");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File("logs/ro = "+ro+" simulation time= "+maxSimulationTime+".txt");
        FileWriter out;
        try {
            out = new FileWriter(file,true);
            out.append("SIMULATION: lambda = ").append(String.valueOf(lambda)).append(", Mi = ").append(String.valueOf(mi)).append(", Simulation time = ").append(String.valueOf(maxSimulationTime)).append("\n");
            //out.write();
            out.append("Sredni czas oczekiwania na obsluge =  ").append(String.valueOf(getAvgTimeWaitingForService(arrivalTimes, startServiceTimes, servicedEvents))).append(" Wartość teoretyczna:   ").append(String.valueOf(Math.pow(ro, 2) / (lambda * (1 - ro)))).append("\n");
            out.append("Sredni czas przejscia przez system  =  ").append(String.valueOf(getAvgTransitionTime(arrivalTimes, startServiceTimes, servicedEvents, mi))).append(" Wartość teoretyczna:   ").append(String.valueOf(ro / (lambda * (1 - ro)))).append("\n");

            out.append("Srednia liczba klientow w kolejce =   ").append(String.valueOf(getAvgClientsInBuf(listTimes, listCountEventsInBuf, clock))).append(" Wartość teoretyczna:   ").append(String.valueOf(Math.pow(ro, 2) / (1 - ro))).append("\n");
            out.append("Srednia liczba klientow w systemie =   ").append(String.valueOf(getAvgClientInSys(listTimes, listCountEventsInBuf, serviceTime, clock))).append(" Wartość teoretyczna:   ").append(String.valueOf(ro / (1 - ro))).append("\n").append("\n").append("\n").append("\n");

            out.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    void plotPZero(){
        ArrayList<Double> listTimeSeconds = new ArrayList<>();
        ArrayList<Double> listPZeroStacjonarny = new ArrayList<>();

            for (int i =0;i<listOfPZeroTimes.size();i++){
                listTimeSeconds.add((double)i);
                listPZeroStacjonarny.add(1-ro);
            }
            Plot plot = Plot.plot(Plot.plotOpts().
                    title("Wykres zbieżności p0(t) do p0 rozkładu stacjonarnego").
                    legend(Plot.LegendFormat.BOTTOM)).

                    series("p0(t)", Plot.data().
                            xy(listTimeSeconds,listOfPZeroTimes),
                    Plot.seriesOpts().

                            color(Color.BLUE));

            try {
                plot.save("p0", "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            plot.series("p0", Plot.data().
                            xy(listTimeSeconds,listPZeroStacjonarny),
                    Plot.seriesOpts().

                            color(Color.RED));
        try {
            plot.save("p0", "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showStatistic(){
        System.out.println("Sredni czas oczekiwania na obsluge =  "+ getAvgTimeWaitingForService(arrivalTimes,startServiceTimes,servicedEvents)+
                " Wartość teoretyczna:   "+(Math.pow(ro,2)/(lambda*(1-ro))));
        System.out.println("Sredni czas przejscia przez system  =  "+ getAvgTransitionTime(arrivalTimes,startServiceTimes,servicedEvents,mi)+
                " Wartość teoretyczna:   "+(ro/(lambda*(1-ro))));

        System.out.println("Srednia liczba klientow w kolejce =   "+getAvgClientsInBuf(listTimes,listCountEventsInBuf,clock)+
                " Wartość teoretyczna:   "+(Math.pow(ro,2)/(1-ro)));
        System.out.println("Srednia liczba klientow w systemie =   "+getAvgClientInSys(listTimes,listCountEventsInBuf, serviceTime,clock)+
                " Wartość teoretyczna:   "+(ro/(1-ro)));

        System.out.println("CZAS P0:     "+ pZeroTime/clock);
    }


    double getAvgClientsInBuf(ArrayList<Double> listTimes, ArrayList<Double>listCountEventsInBuf, double clock){
        double sum = 0;
        for (int i = 1000; i<listCountEventsInBuf.size()-1;i++){
            sum+=((listTimes.get(i+1)-listTimes.get(i))*listCountEventsInBuf.get(i));
        }
        return sum/clock;
    }
    double getAvgClientInSys(ArrayList<Double>listTimes,ArrayList<Double>listCountEventsInBuf,
                                double serviceTime,double clock){
        // System.out.println("KL W SYSTEMIE: "+obsluga_real+"  "+czas_symulacji+"   "+ (obsluga_real/czas_symulacji));
        double wynik = getAvgClientsInBuf(listTimes,listCountEventsInBuf,clock)+(serviceTime/clock);
        return wynik;
    }
    double getAvgTimeWaitingForService (ArrayList<Double>arrivalTimes,ArrayList<Double>startServiceTimes,double servicedEvents){
        double sum = 0;
        for (int i = 0; i < servicedEvents; i++){
            sum+= (startServiceTimes.get(i)-arrivalTimes.get(i));
        }
        return sum/servicedEvents;
    }
    double getAvgTransitionTime (ArrayList<Double>arrivalTimes,ArrayList<Double>startServiceTimes,
                                        double events,int mi) {
        double suma = 0;
        for (int i = 0; i < events; i++){
            suma+= (startServiceTimes.get(i)-arrivalTimes.get(i)+(1/(double)mi));
        }
        return suma/events;
    }
}
