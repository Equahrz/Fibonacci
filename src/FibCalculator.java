import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

public class FibCalculator {

    public BigInteger fibonacci(int n) {
        Instant start = Instant.now();
        BigInteger result = fibonacciRecursive(n);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Berechnungsdauer: " + timeElapsed.toMillis() + " Millisekunden");
        return result;
    }


    private BigInteger fibonacciRecursive(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            return fibonacciRecursive(n - 1).add(fibonacciRecursive(n - 2));
        }
    }
}