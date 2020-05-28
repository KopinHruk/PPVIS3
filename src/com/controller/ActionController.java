package com.company.controller;

import com.company.view.MainWindow;

public class ActionController {
    private final MainWindow window;

    public ActionController(MainWindow window) {
        this.window = window;
    }

    public void addEventListeners() {
        ZoomEventListener zoomEventListener = new ZoomEventListener(window);
        DragEventListener dragEventListener = new DragEventListener(window);
        ButtonEventController buttonEventController = new ButtonEventController(window);
    }
}
