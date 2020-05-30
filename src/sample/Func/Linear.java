package sample.Func;

public class Linear implements Function {
    public final double coefficient;

    public Linear(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public double calculate(double x) {
        return coefficient * x;
    }

    @Override
    public Function derivative() {
        return new Constant(coefficient);
    }
}
