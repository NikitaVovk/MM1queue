package com.company;


public class Main {

        public static void main(String[] args) {

        Statistics statistics =
                new Statistics(1, 4, 15000, 5000);//ustawienie początkowych parametrów
        statistics.run();       //uruchomienie symulacji
        statistics.showStatistic();     //wyświetlenia statystyki w terminali
         statistics.plotPZero();         //tworzenia wykresu
        statistics.saveLogs();          //zapis statystyki do pliku


                 Statistics statistics2 =
                        new Statistics(5,10,15000, 5000);//ustawienie początkowych parametrów
                statistics2.runContinuesService();       //uruchomienie symulacji
                statistics2.showStatisticForCS();     //wyświetlenia statystyki w terminali

        }



}
