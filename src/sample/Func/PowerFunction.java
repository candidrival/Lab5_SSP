package sample.Func;
import static java.lang.Math.pow;
public class PowerFunction implements Function {
    public final double degree;
    public final Function func;

    public PowerFunction(double degree, Function func) {
        this.degree = degree;
        this.func = func;
    }

    @Override
    public double calculate(double x) {
        return pow(func.calculate(x), degree);
    }

    @Override
    public Function derivative() {
        return new Multiplication(new Constant(degree),
                new Multiplication(func.derivative(), new PowerFunction(degree - 1, func)));
    }
}
