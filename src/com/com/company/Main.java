package com.company;

import com.company.model.functions.TripledXFunction;
import com.company.view.MainWindow;


public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        TripledXFunction first = new TripledXFunction(10,-10, 2);
        window.chartField.addChart(
                "5X+3", first.getXes(),
                first.getYes());
        window.run();
    }
}
