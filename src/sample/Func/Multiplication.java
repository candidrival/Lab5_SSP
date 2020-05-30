package sample.Func;

public class Multiplication implements Function{
    public final Function a, b;

    public Multiplication(Function a, Function b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculate(double x) {
        return a.calculate(x) * b.calculate(x);
    }

    @Override
    public Function derivative() {
        return new Sum(new Multiplication(a, b.derivative()), new Multiplication(a.derivative(), b));
    }
}
