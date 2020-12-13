package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

        public static void main(String[] args) {
        Statistics statistics = new Statistics(1,4,15000, 1000);//ustawienie początkowych parametrów
        statistics.run();       //uruchomienie symulacji
        statistics.showStatistic();     //wyświetlenia statystyki w terminali
        statistics.plotPZero();         //tworzenia wykresu
        statistics.saveLogs();          //zapis statystyki do pliku

        }



}
