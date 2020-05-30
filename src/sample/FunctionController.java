package sample;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.Func.*;

import static sample.Func.Functions.*;

public class FunctionController {
    String func1 = " f = ( 2tg(t)+|-sin(t)| )^1/3 ";
    String func2 = " f = 2cos( 0.7t sin(t)-1 ) ";
    String der_func_1 = "f = (cos(t)sin(t)-2tg^2(x)-2)/3(-2tg(t)+|-sin(x)|)^(2/3)";
    String der_func_2 = "f = -2((7tcos(t)/10+7tsin(t)/10)sin(0.7tsin(t)) ";
    String check_for_func = "";
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SetFunc;

    @FXML
    private Button Derivative;

    @FXML
    private RadioButton FirstFunc;

    @FXML
    private RadioButton SecondFunc;

    @FXML
    private Label Formula;

    @FXML
    private Label Result;

    @FXML
    private TextField Start;

    @FXML
    private TextField End;

    @FXML
    private Button Build;

    @FXML
    private Button Calculate;

    @FXML
    public LineChart<String, Double> chart;
    @FXML
    private TextField SetNumber;


    @FXML
    void initialize() {
        Build.setOnAction(actionEvent -> {
            Build(check_for_func);
        });
        SetFunc.setOnAction(actionEvent -> {
            check_for_func = SetFunction();
        });
        Derivative.setOnAction(actionEvent -> {
            check_for_func = SetDerFunction();
        });
        Calculate.setOnAction(actionEvent -> {
            String result = calculation(SetNumber.getText(), check_for_func);
            Result.setText(result);
        });

    }

    private void Build(String formula) {
        if (FirstFunc.isSelected() && formula.trim().equals(func1.trim())) {
            drawfirst();
        }
        if (SecondFunc.isSelected() && formula.trim().equals(func2.trim())) {
            drawsecond();
        }
        if (FirstFunc.isSelected() && formula.trim().equals(der_func_1.trim())) {
            drawfirstder();
        }
        if (SecondFunc.isSelected() && der_func_2.trim().equals(formula.trim())) {
            drawsecder();
        }
    }

    void drawfirst() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName(func1);
        int start = Integer.parseInt(Start.getText());
        int end = Integer.parseInt(End.getText());

        for (double i = start; i < end; i+=((end-start)/20)) {

            XYChart.Data<String, Double> data = new XYChart.Data<>(String.format("%.2f", i), first(i));
            series.getData().add(data);
        }
        chart.setCreateSymbols(false);
        chart.getData().clear();
        chart.getData().add(series);
    }

    void drawsecond() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName(func2);
        int start = Integer.parseInt(Start.getText());
        int end = Integer.parseInt(End.getText());

        for (double i = start; i < end; i+=((end-start)/20)) {

            XYChart.Data<String, Double> data = new XYChart.Data<>(String.format("%.2f", i), funct2(i));
            series.getData().add(data);
        }

        chart.setCreateSymbols(false);
        chart.getData().clear();
        chart.getData().add(series);
    }

    void drawfirstder() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName(der_func_1);
        int start = Integer.parseInt(Start.getText());
        int end = Integer.parseInt(End.getText());
        for (double i = start; i < end; i+=((end-start)/20)) {

            XYChart.Data<String, Double> data = new XYChart.Data<>(String.format("%.2f", i), der1(i));
            series.getData().add(data);
        }

        chart.setCreateSymbols(false);
        chart.getData().clear();
        chart.getData().add(series);
    }

    void drawsecder() {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName(der_func_2);
        int start = Integer.parseInt(Start.getText());
        int end = Integer.parseInt(End.getText());
        for (double i = start; i < end; i+=((end-start)/20)) {

            XYChart.Data<String, Double> data = new XYChart.Data<>(String.format("%.2f", i), derivative_function2(i));
            series.getData().add(data);
        }
        chart.setCreateSymbols(false);
        chart.getData().clear();
        chart.getData().add(series);
    }
    String parseToString(double x){
        double par = funct1(x);
        double par1 = derivative_function1(x);
        return String.valueOf(x);
    }

    String calculation(String x, String formula) {
        double par = Double.parseDouble(x);
        String result = "";
        if (FirstFunc.isSelected() && formula.trim().equals(func1.trim())) {
            result = parseToString(first(par));
        }
        if (SecondFunc.isSelected() && formula.trim().equals(func2.trim())) {
            result = String.valueOf(funct2(par));
        }
        if (FirstFunc.isSelected() && der_func_1.trim().equals(formula.trim())) {
            result = String.valueOf(der1(par));
        }
        if (SecondFunc.isSelected() && der_func_2.trim().equals(formula.trim())) {
            result = String.valueOf(derivative_function2(par));
        }
        return result;
    }

    String SetFunction() {
        String func;
        if (FirstFunc.isSelected()) {
            Formula.setText(func1);
            return func = func1;
        } else if (SecondFunc.isSelected()) {
            Formula.setText(func2);
            return func = func2;
        }
        return "";
    }

    String SetDerFunction() {
        String func;
        if (FirstFunc.isSelected()) {
            Formula.setText(der_func_1);
            return func = der_func_1;
        }
        if (SecondFunc.isSelected()) {
            Formula.setText(der_func_2);
            return func = der_func_2;
        }
        return "";
    }

    public Double funct1(double par) {
        Function function = new PowerFunction(1.0 / 3,
                new Sum(new Multiplication(new Constant(-2), new Tan(new Linear(par))),
                        new Multiplication(new Constant(-1), new Sin(new Linear(par)))));
        Double res = Double.parseDouble(String.valueOf(function.calculate(par)));
        return res;
    }

    public Double funct2(double par) {
        Function function = new Sum(new Multiplication(new Constant(-2),
                new Cos(new Multiplication(new Multiplication(new Constant(0.7), new Constant(par)),
                        new Sin(new Linear(par))))), new Constant(-1));
        Double res = Double.parseDouble(String.valueOf(function.calculate(par)));
        return res;
    }

    public Double derivative_function1(double par) {
        Function function = new PowerFunction(1.0 / 3,
                new Sum(new Multiplication(new Constant(-2), new Tan(new Linear(par))),
                        new Multiplication(new Constant(-1), new Sin(new Linear(par)))));
        Double res = Double.parseDouble(String.valueOf(function.derivative().calculate(par)));
        return res;
    }

    public Double derivative_function2(double par) {
        Function function = new Sum(new Multiplication(new Constant(-2),
                new Cos(new Multiplication(new Multiplication(new Constant(0.7), new Constant(par)),
                        new Sin(new Linear(par))))), new Constant(-1));
        Double res = Double.parseDouble(String.valueOf(function.derivative().calculate(par)));
        return res;
    }

}
