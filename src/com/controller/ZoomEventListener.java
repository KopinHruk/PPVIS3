package com.company.controller;

import com.company.view.MainWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Button;

public class ZoomEventListener implements MouseListener, MouseWheelListener {

    private final MainWindow window;
    private long zoomCounter = 0;

    public ZoomEventListener(MainWindow window) {
        this.window = window;
        this.window.getZoomInButton().addMouseListener(this);
        this.window.getZoomOutButton().addMouseListener(this);
        this.window.chartField.getChart().addMouseWheelListener(this);
    }

    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {
        Button target = (Button) mouseEvent.widget;
        if (target == this.window.getZoomInButton())
            this.window.chartField.getChart().getAxisSet().zoomIn();
        else if (target == this.window.getZoomOutButton())
            this.window.chartField.getChart().getAxisSet().zoomOut();
        this.window.chartField.redraw();
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseScrolled(MouseEvent mouseEvent) {
        long prev = this.zoomCounter;
        this.zoomCounter += mouseEvent.count;
        if (mouseEvent.stateMask != SWT.CTRL) return;
        if (zoomCounter > prev) {
            this.window.chartField.getChart().getAxisSet().zoomOut();
        } else if (zoomCounter < prev) {
            this.window.chartField.getChart().getAxisSet().zoomIn();
        }
        this.window.chartField.redraw();
        this.zoomCounter = 0;
    }
}
