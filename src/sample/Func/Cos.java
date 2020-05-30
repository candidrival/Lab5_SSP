package sample.Func;
import static java.lang.Math.cos;

public class Cos implements Function {
    public final Function inner_func;

    public Cos(Function inner_func) {
        this.inner_func = inner_func;
    }

    @Override
    public double calculate(double x) {
        return cos(inner_func.calculate(x));
    }

    @Override
    public Function derivative() {
        return new Multiplication(new Difference(new Constant(0), new Sin(inner_func)), inner_func.derivative());
    }
}
