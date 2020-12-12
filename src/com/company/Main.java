package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

        public static void main(String[] args) {
        Statistics statistics = new Statistics(1,4,15000);
        statistics.run();
        statistics.showStatistic();
        statistics.plotPZero();
        statistics.saveLogs();

        }



}
