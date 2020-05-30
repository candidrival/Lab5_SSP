package sample.Func;

import sample.Func.*;

import static java.lang.Math.*;
import static java.lang.Math.sin;

public class Functions {
    public static double first(double x) {
        return Math.cbrt(-2 * Math.tan(x) + Math.abs(-1 * Math.sin(x)));
    }
//    static double second(double x) {
//        return (-2 * cos(0.7 * x * Math.sin(x)) - 1);
//    }
    public static double der1(double x) { return (cos(x) * sin(x) - 2 * pow(tan(x), 2) - 2) / (3 * pow((-2 * tan(x) + abs(-sin(x))), 2 / 3)); }
//    static double der2(double x) { return 2 * ((7 * cos(x) / 10 + 7 * sin(x) / 10) * sin(0.7 * x * sin(x))); }

}
