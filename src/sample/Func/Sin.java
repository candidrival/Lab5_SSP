
package sample.Func;
import static java.lang.Math.sin;

public class Sin implements Function{
    public final Function function;

    public Sin(Function function) {
        this.function = function;
    }

    @Override
    public double calculate(double x) {
        return sin(function.calculate(x));
    }

    @Override
    public Function derivative() {
        return new Multiplication(new Cos(function), function.derivative());
    }
}
