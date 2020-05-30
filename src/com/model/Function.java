package com.company.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Function {
    private final double step = 1.0;
    protected int aParam;
    double[] Xes;
    double[] Yes;
    int topValue;
    int bottomValue;

    public Function(int topValue, int bottomValue, int aParam) {
        this.topValue = topValue;
        this.bottomValue = bottomValue;
        this.aParam = aParam;
        List<Double> xes = new LinkedList<>();
        List<Double> yes = new LinkedList<>();
        for (double i = bottomValue; i < topValue; i += step) {
            xes.add(i);
            yes.add(calculateY(i));
        }
        Xes = new double[xes.size()];
        Yes = new double[yes.size()];
        for (int i = 0; i < xes.size(); i++) {
            Xes[i] = xes.get(i);
            Yes[i] = yes.get(i);
        }
    }

    public abstract double calculateY(double x);

    public double[] getXes() {
        return Xes;
    }

    public double[] getYes() {
        return Yes;
    }
}
