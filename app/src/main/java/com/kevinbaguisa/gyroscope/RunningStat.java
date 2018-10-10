package com.kevinbaguisa.gyroscope;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RunningStat {
    private int n;
    private double oldM, newM, oldS, newS;
    private int precision = -1;

    // An estimate for the t-value (can be read from the t-distribution table)
    private static final double T_THRESHOLD = 1.68;

    public RunningStat(int precision) {
        this.precision = precision;
    }

    public RunningStat() {
    }

    public void clear() {
        n = 0;
    }

    public void push(double x) {
        n++;


        if (n == 1) {
            oldM = newM = x;
            oldS = 0.0;
        } else {
            newM = oldM + (x - oldM) / n;
            newS = oldS + (x - oldM) * (x - newM);

            // set up for next iteration
            oldM = newM;
            oldS = newS;
        }
    }

    public int count() {
        return n;
    }

    public double mean() {
        double mean = (n > 0) ? newM : 0.0;
        if (precision > 0) {
            return round(mean, precision);
        }
        return mean;
    }

    // The upper bound of the mean confidence interval
    public double meanUpper() {
        double mean = (n > 0) ? newM : 0.0;
        double stdError = stdDeviation() / Math.sqrt(n);
        double upperMean = mean + T_THRESHOLD * stdError;
        if (precision > 0) {
            return round((n > 0) ? upperMean : 0.0, precision);
        }
        return upperMean;
    }

    // The lower bound of the mean confidence interval
    public double meanLower() {
        double mean = (n > 0) ? newM : 0.0;
        double stdError = stdDeviation() / Math.sqrt(n);
        double lowerMean = mean - T_THRESHOLD * stdError;
        if (precision > 0) {
            return round((n > 0) ? lowerMean : 0.0, precision);
        }
        return lowerMean;
    }

    public double variance() {
        if (precision > 0) {
            return round(((n > 1) ? newS / (n - 1) : 0.0), precision);
        }
        return ((n > 1) ? newS / (n - 1) : 0.0);
    }

    public double stdDeviation() {
        if (precision > 0) {
            return round(Math.sqrt(variance()), precision);
        }
        return Math.sqrt(variance());
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public static double round(double value, int precision) {
        BigDecimal num = new BigDecimal(value);
        num = num.round(new MathContext(precision, RoundingMode.HALF_UP));
        return num.doubleValue();
    }

    // A small test case
    public static void main(String[] args) {
        int n = 100;
        RunningStat runningStat = new RunningStat();
        double[] data = new double[n];
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            data[i] = i * i;
            sum += data[i];
            runningStat.push(data[i]);
            System.out.println(runningStat.mean() + " - "
                    + runningStat.variance() + " - "
                    + runningStat.stdDeviation());
        }

        double mean = sum / n;
        double sum2 = 0.0;

        for (int i = 0; i < n; i++) {
            sum2 = sum2 + (data[i] - mean) * (data[i] - mean);
        }

        double variance = sum2 / (n - 1);
        System.out.println("\n\n" + mean + " - " + variance + " - "
                + Math.sqrt(variance));
    }
}
