package id.ac.sgu.fx;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import id.ac.sgu.SystemController;

public class AppController {
    public SystemController systemController = SystemController.getInstance();
    public boolean isTimeRunning = true;
    public boolean isChartRunning = true;
    public boolean isRandom = true;
    public boolean isManual = false;
    public boolean manualRainingStatus = false;
    public boolean isManualChartRunning = false;
    public Label clock, thermoNumber, anemoNumber, 
                acStatus, heatherStatus, windowStatus,
                rainingStatus, acPowerStatus, log1, log2, log3;
    public Slider anemoSlider,tempSlider;
    public RadioButton dayButton, nightButton;
    public CheckBox rainingButton;
    public LineChart<String, Number> thermoChart, anemoChart;
    public int WINDOW_SIZE = 20;
    XYChart.Series<String, Number> dataThermo = new XYChart.Series<>();
    XYChart.Series<String, Number> dataAnemo = new XYChart.Series<>();

    public void initialize() {
        tempSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                systemController.setManualTemperature(Math.round(tempSlider.getValue() * 10.0) / 10.0);
                dataThermo.getData().add(new XYChart.Data<String, Number>(String.valueOf(Math.random()),
                        systemController.getManualTemperature()));
                log("Set temperature to " + String.valueOf(systemController.getManualTemperature()));
                if (dataThermo.getData().size() > WINDOW_SIZE){
                    dataThermo.getData().remove(0);
                }
            }
        });

        anemoSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                systemController.setManualAnemometer(Math.round(anemoSlider.getValue() * 10.0) / 10.0);
                dataAnemo.getData().add(new XYChart.Data<String, Number>(String.valueOf(Math.random()),
                        systemController.getManualAnemometer()));
                log("Set anemo to " + String.valueOf(systemController.getManualAnemometer()));
                if (dataAnemo.getData().size() > WINDOW_SIZE){
                    dataAnemo.getData().remove(0);
                }
            }
        });

        startClock();
        log("Live clock turned on.");

        thermoChart.getData().add(dataThermo);
        anemoChart.getData().add(dataAnemo);
        startChartAnimation();
        log("Starting chart animation.");
    }

    public void updateText() {
        isManual = true;
        Thread thread = new Thread(() -> {
            while (isManual) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
                Platform.runLater(() -> {
                    if (systemController.getAirConditioner().getAirConditionerStatus()) {
                        acStatus.setText("ON");
                        String powerStatus = systemController.getAirConditioner().getAirConditionerPowerStatus()
                                .toString();
                        acPowerStatus.setText(powerStatus.substring(0, 1) + powerStatus.substring(1).toLowerCase());
                    } else {
                        acStatus.setText("OFF");
                        acPowerStatus.setText("OFF");
                    }
                    if (systemController.getHeather().getHeatherStatus()) {
                        heatherStatus.setText("ON");
                    } else {
                        heatherStatus.setText("OFF");
                    }
                    if (systemController.getWindow().getWindowStatus()) {
                        windowStatus.setText("Opened");
                    } else {
                        windowStatus.setText("Closed");
                    }
                    if(systemController.getManualRainingStatus()){
                        rainingStatus.setText("Raining");
                    } else {
                        rainingStatus.setText("Sunny");
                    }
                    thermoNumber.setText(String.valueOf(systemController.getManualTemperature()));
                    anemoNumber.setText(String.valueOf(systemController.getManualAnemometer()));
                    final LocalTime tempTime = systemController.getCurrentTime();
                    clock.setText(tempTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                });
            }
        });
        thread.start();
    }

    public void stopManualChartAnimation(){
        isManualChartRunning = false;
    }

    public void startManualChartAnimation(){
        isManualChartRunning = true;
        Thread thread = new Thread(() -> {
            while(isManualChartRunning){
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println(e);
                }
                Platform.runLater(() -> {
                    if (dataThermo.getData().size() > WINDOW_SIZE){
                        dataThermo.getData().remove(0);
                    }
                    if (dataAnemo.getData().size() > WINDOW_SIZE){
                        dataAnemo.getData().remove(0);
                    }
                    dataThermo.getData().add(new XYChart.Data<String, Number>(String.valueOf(Math.random()), systemController.getManualTemperature()));
                    dataAnemo.getData().add(new XYChart.Data<String, Number>(String.valueOf(Math.random()), systemController.getManualAnemometer()));
                });
            }
        });
        thread.start();
    }

    public void startChartAnimation(){
        isChartRunning = true;
        Thread thread = new Thread(() -> {
            while (isChartRunning) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                Platform.runLater(() -> {
                    dataThermo.getData().add(new XYChart.Data<String, Number>(String.valueOf(Math.random()),
                            systemController.getSensorNumber().getTemperatureNumber()));
                    dataAnemo.getData().add(new XYChart.Data<String, Number>(String.valueOf(Math.random()),
                            systemController.getSensorNumber().getAnemoNumber()));
                    anemoNumber.setText(String.valueOf(systemController.getSensorNumber().getAnemoNumber()));
                    thermoNumber.setText(String.valueOf(systemController.getSensorNumber().getTemperatureNumber()));
                    if (systemController.getAirConditioner().getAirConditionerStatus()) {
                        acStatus.setText("ON");
                        String powerStatus = systemController.getAirConditioner().getAirConditionerPowerStatus()
                                .toString();
                        acPowerStatus.setText(powerStatus.substring(0, 1) + powerStatus.substring(1).toLowerCase());
                    } else {
                        acStatus.setText("OFF");
                        acPowerStatus.setText("OFF");
                    }
                    if (systemController.getHeather().getHeatherStatus()) {
                        heatherStatus.setText("ON");
                    } else {
                        heatherStatus.setText("OFF");
                    }
                    if (systemController.getWindow().getWindowStatus()) {
                        windowStatus.setText("Opened");
                    } else {
                        windowStatus.setText("Closed");
                    }
                    if (systemController.getSensorNumber().getRainDropSensorStatus()) {
                        rainingStatus.setText("Raining");
                    } else {
                        rainingStatus.setText("Sunny");
                    }
                    if (dataThermo.getData().size() > WINDOW_SIZE){
                        dataThermo.getData().remove(0);
                    }
                    if (dataAnemo.getData().size() > WINDOW_SIZE){
                        dataAnemo.getData().remove(0);
                    }
                });
            }
        });
        thread.start();
    }

    public void stopThread() {
        isTimeRunning = false;
        isChartRunning = false;
    }

    public void stopUpdateText() {
        isManual = false;
    }

    public void startClock() {
        isTimeRunning = true;
        Thread thread = new Thread(() -> {
            while (isTimeRunning) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final LocalTime tempTime = systemController.getSensorNumber().getTime();
                Platform.runLater(() -> {
                    clock.setText(tempTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                });
            }
        });
        thread.start();
    }

    public void toggleRandom() {
        isRandom = !isRandom;
        if (isRandom) {
            anemoSlider.setDisable(true);
            tempSlider.setDisable(true);
            dayButton.setDisable(true);
            nightButton.setDisable(true);
            rainingButton.setDisable(true);
            stopManualChartAnimation();
            startClock();
            startChartAnimation();
            systemController.setIsManual(false);
            stopUpdateText();
            log("Random turned on.");
        } else {
            anemoSlider.setDisable(false);
            tempSlider.setDisable(false);
            dayButton.setDisable(false);
            nightButton.setDisable(false);
            rainingButton.setDisable(false);
            stopThread();
            systemController.setIsManual(true);
            updateText();
            startManualChartAnimation();
            log("Random turned off, use manual control.");
        }
    }

    public void toggleDay() {
        systemController.setCurrentTme(LocalTime.parse("12:00:00"));
        log("Set time to day.");
    }

    public void toggleNight() {
        systemController.setCurrentTme(LocalTime.parse("21:00:00"));
        log("Set time to night.");
    }

    public void toggleRaining() {
        systemController.setManualRainingStatus(!manualRainingStatus);
        manualRainingStatus = !manualRainingStatus;
        if (manualRainingStatus) {
            log("Set raining to on.");
        } else {
            log("Set raining to off.");
        }
    }

    public void log(String message) {
        log1.setText(log2.getText().isEmpty() ? "" : log2.getText());
        log2.setText(log3.getText().isEmpty() ? "" : log3.getText());
        log3.setText(message);
    }
}
