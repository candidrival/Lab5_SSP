package sample.Func;
import static java.lang.Math.tan;

public class Tan implements Function {
    public final Function function;

    public Tan(Function function) {
        this.function = function;
    }
    @Override
    public double calculate(double x) {
        return tan(function.calculate(x));
    }

    @Override
    public Function derivative() {
        return new Multiplication(new Division(
                new Constant(1), new PowerFunction(2, new Cos(function))),
                function.derivative());
    }
}
