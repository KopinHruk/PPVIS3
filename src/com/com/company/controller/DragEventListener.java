package com.company.controller;

import com.company.model.Pair;
import com.company.view.MainWindow;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swtchart.IAxis;

public class DragEventListener implements MouseListener, MouseMoveListener {

    private final MainWindow window;
    private final Pair<Integer> cursorCoordinates;
    private boolean isDragging;

    public DragEventListener(MainWindow window) {
        this.window = window;
        this.isDragging = false;
        this.cursorCoordinates = new Pair<>();
        this.window.chartField.getChart().getPlotArea().addMouseListener(this);
        this.window.chartField.getChart().getPlotArea().addMouseMoveListener(this);
    }


    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {
        this.cursorCoordinates.put(mouseEvent.x, mouseEvent.y);
        this.isDragging = true;
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
        this.cursorCoordinates.clear();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent) {
        if (this.isDragging) {
            Pair<Pair<Boolean>> direction = this.getDirection(new Pair<>(mouseEvent.x, mouseEvent.y));
            // {{Left, Right}, {Up, Down}}

            IAxis xAsix = this.window.chartField.getChart().getAxisSet().getXAxis(0);
            IAxis yAsix = this.window.chartField.getChart().getAxisSet().getYAxis(0);

            boolean left = direction.getFirst().getFirst();
            boolean right = direction.getFirst().getSecond();
            boolean up = direction.getSecond().getFirst();
            boolean down = direction.getSecond().getSecond();

            if (left) xAsix.scrollUp();
            if (right) xAsix.scrollDown();
            if (up) yAsix.scrollDown();
            if (down) yAsix.scrollUp();

            this.cursorCoordinates.put(mouseEvent.x, mouseEvent.y);
            this.window.chartField.redraw();
        }
    }

    private Pair<Pair<Boolean>> getDirection(Pair<Integer> currentCursorCoordinates) {
        // {{Left, Right}, {Up, Down}}

        Pair<Pair<Boolean>> direction = new Pair<>();
        Pair<Boolean> leftRight = new Pair<>();
        Pair<Boolean> upDown = new Pair<>();

        int prevX = this.cursorCoordinates.getFirst();
        int prevY = this.cursorCoordinates.getSecond();
        int currentX = currentCursorCoordinates.getFirst();
        int currentY = currentCursorCoordinates.getSecond();

        leftRight.setFirst(currentX < prevX + 20);    // If X coordinate has decreased - cursor is moving left
        leftRight.setSecond(currentX > prevX - 20);   // If X coordinate has increased - cursor is moving right
        upDown.setFirst(currentY < prevY + 20);       // If Y coordinate has decreased - cursor is moving up
        upDown.setSecond(currentY > prevY - 20);      // If Y coordinate has increased - cursor is moving down

        direction.put(leftRight, upDown);

        return direction;
    }
}
