package sample.Func;

public class Sum implements Function {
    private final Function a, b;

    public Sum(Function a, Function b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculate(double x) {
        return a.calculate(x) + b.calculate(x);
    }

    @Override
    public Function derivative() {
        return new Sum(a.derivative(), b.derivative());
    }
}
