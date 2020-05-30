package com.company.view;

import com.company.controller.ActionController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class MainWindow {

    public com.company.view.ChartField chartField;
    protected Shell shlChartDrawer;
    private Composite chartComposite;
    private Display display;
    private ActionController actionController;

    private Table table;
    private Button startButton;
    private Button stopButton;
    private Spinner functionParamSpinner;
    private Spinner functionStep;
    private Button zoomInButton;
    private Button zoomOutButton;

    public Composite getChartComposite() {
        return chartComposite;
    }

    public MainWindow() {
        this.init();
    }

    public Table getTable() {
        return table;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Spinner getFunctionParamSpinner() {
        return functionParamSpinner;
    }

    public Spinner getFunctionStep() {
        return functionStep;
    }

    public Button getZoomInButton() {
        return zoomInButton;
    }

    public Button getZoomOutButton() {
        return zoomOutButton;
    }

    public void run() {
        try {
            this.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        this.display = Display.getDefault();
        this.createWindow();
        this.actionController = new ActionController(this);
        this.actionController.addEventListeners();
    }

    private void open() {
        this.shlChartDrawer.open();
        this.shlChartDrawer.layout();
        while (!shlChartDrawer.isDisposed()) {
            if (!this.display.readAndDispatch()) {
                this.display.sleep();
            }
        }
    }

    protected void createWindow() {
        this.createShell();
        this.createChartField();
        this.createControls();
    }

    protected void createShell() {
        this.shlChartDrawer = new Shell();
        this.shlChartDrawer.setMinimumSize(new Point(640, 480));
        this.shlChartDrawer.setText("ChartDrawer");
        this.shlChartDrawer.setLayout(new FormLayout());
    }

    protected void createChartField() {
        this.chartComposite = new Composite(shlChartDrawer, SWT.NONE);
        this.chartComposite.setLayout(new FillLayout());
        FormData fd_composite = new FormData();
        fd_composite.bottom = new FormAttachment(100, -46);
        fd_composite.right = new FormAttachment(75);
        fd_composite.top = new FormAttachment(0);
        fd_composite.left = new FormAttachment(0);
        this.chartComposite.setLayoutData(fd_composite);

        this.chartField = new com.company.view.ChartField(this.chartComposite);
    }

    public void clearTable() {
        this.getTable().clearAll();
    }

    protected void createControls() {
        table = new Table(this.shlChartDrawer, SWT.BORDER | SWT.FULL_SELECTION);
        FormData fd_table = new FormData();
        fd_table.right = new FormAttachment(100, -10);
        fd_table.left = new FormAttachment(76);
        table.setLayoutData(fd_table);
        table.setHeaderVisible(true);
        String[] titles = { "x", "y(x)"};
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(title);
        }
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        startButton = new Button(this.shlChartDrawer, SWT.NONE);
        fd_table.bottom = new FormAttachment(startButton, 0, SWT.BOTTOM);
        fd_table.top = new FormAttachment(0);
        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.top = new FormAttachment(100, -40);
        fd_btnNewButton.bottom = new FormAttachment(100, -6);
        fd_btnNewButton.left = new FormAttachment(0, 10);
        startButton.setLayoutData(fd_btnNewButton);
        startButton.setText("Start");

        stopButton = new Button(this.shlChartDrawer, SWT.NONE);
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.left = new FormAttachment(startButton, 6);
        fd_btnNewButton_1.top = new FormAttachment(this.chartComposite, 6);
        fd_btnNewButton_1.bottom = new FormAttachment(100, -6);
        stopButton.setLayoutData(fd_btnNewButton_1);
        stopButton.setText("Stop");

        functionParamSpinner = new Spinner(this.shlChartDrawer, SWT.BORDER);
        FormData fd_spinner = new FormData();
        fd_spinner.left = new FormAttachment(stopButton, 6);
        fd_spinner.top = new FormAttachment(this.chartComposite, 6);
        fd_spinner.bottom = new FormAttachment(100, -6);
        functionParamSpinner.setLayoutData(fd_spinner);

        functionStep = new Spinner(this.shlChartDrawer, SWT.BORDER);
        fd_spinner.right = new FormAttachment(functionStep, -6);
        FormData fd_spinner_1 = new FormData();
        fd_spinner_1.top = new FormAttachment(this.chartComposite, 6);
        fd_spinner_1.right = new FormAttachment(0, 363);
        fd_spinner_1.bottom = new FormAttachment(100, -6);
        fd_spinner_1.left = new FormAttachment(0, 242);
        functionStep.setLayoutData(fd_spinner_1);

        zoomInButton = new Button(shlChartDrawer, SWT.NONE);
        FormData fd_btnNewButton_2 = new FormData();
        fd_btnNewButton_2.bottom = new FormAttachment(table, 0, SWT.BOTTOM);
        fd_btnNewButton_2.left = new FormAttachment(functionStep, 6);
        zoomInButton.setLayoutData(fd_btnNewButton_2);
        zoomInButton.setText("+");

        zoomOutButton = new Button(shlChartDrawer, SWT.NONE);
        zoomOutButton.setText("-");
        FormData fd_button = new FormData();
        fd_button.top = new FormAttachment(this.chartComposite, 6);
        fd_button.left = new FormAttachment(zoomInButton, 6);
        zoomOutButton.setLayoutData(fd_button);
    }
}

