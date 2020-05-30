package sample.Func;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class Exp implements Function{
    public final Function func;

    public Exp(Function func) {
        this.func = func;
    }

    @Override
    public double calculate(double x) {
        return exp(func.calculate(x));
    }

    @Override
    public Function derivative() {
        return new Multiplication(func.derivative(), new Exp(func));
    }
}
