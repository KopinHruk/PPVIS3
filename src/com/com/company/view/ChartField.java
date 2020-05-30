package com.company.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtchart.*;


public class ChartField {
    private final Chart chart;

    ChartField(Composite composite) {
        this.chart = new Chart(composite, SWT.FILL);
        chart.getTitle().setVisible(false);
        chart.getAxisSet().getXAxis(0).getTitle().setVisible(true);
        chart.getAxisSet().getYAxis(0).getTitle().setVisible(true);
    }

    private IAxisSet getAxisSet() {
        return this.chart.getAxisSet();
    }

    private ISeriesSet getISeriesSet() {
        return this.chart.getSeriesSet();
    }

    public void adjustRange() {
        this.getAxisSet().adjustRange();
    }

    public void addChart(String seriesDescription, double[] seriesX, double[] seriesY) {
        ISeries series = this
                .getISeriesSet()
                .createSeries(ISeries.SeriesType.LINE, seriesDescription);
        series.setYSeries(seriesY);
        series.setXSeries(seriesX);
    }

    public void setColor(String seriesDescription, com.company.view.consts.Color color) {
        ILineSeries series = (ILineSeries) chart.getSeriesSet().getSeries(seriesDescription);
        Display display = this.chart.getDisplay();
        if (color == com.company.view.consts.Color.RED)
            series.setLineColor(new Color(display, 255, 255, 0));
        else if (color == com.company.view.consts.Color.BLUE)
            series.setLineColor(new Color(display, 0, 40, 255));
    }

    public Chart getChart() {
        return chart;
    }

    public void redraw() {
        this.getChart().redraw();
    }

    public void clear () {
        ISeriesSet seriesSet = this.getChart().getSeriesSet();
        ISeries[] a = seriesSet.getSeries();
        for (ISeries series: a){
            if (!series.getId().equals("5X+3"))
                this.getChart().getSeriesSet().deleteSeries(series.getId());
        }
        this.redraw();
    }
}
