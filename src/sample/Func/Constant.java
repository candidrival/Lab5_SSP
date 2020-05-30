package sample.Func;
public class Constant implements Function{
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double calculate(double x) {
        return value;
    }

    @Override
    public Function derivative() {
        return ZERO;
    }

    public static final Constant ZERO = new Constant(0);
}
