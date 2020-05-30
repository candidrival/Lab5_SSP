package sample.Func;

public class Division implements Function {
    public final Function a, b;

    public Division(Function a, Function b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculate(double x) {
        return a.calculate(x) / b.calculate(x);
    }

    @Override
    public Function derivative() {
        return (new Multiplication(a, new PowerFunction(-1, b))).derivative();
    }
}
