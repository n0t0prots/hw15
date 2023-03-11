import java.util.Arrays;
import java.util.stream.DoubleStream;

public class ValueCalculator extends Thread {
    private double[] arrayDouble;
    private final int sizeFullArray = 1000000;
    private final int halfOfFullArray = sizeFullArray / 2;

    public void doCalc() throws InterruptedException {
        long start = System.currentTimeMillis();
        arrayDouble = DoubleStream.generate(() -> 2)
                .limit(sizeFullArray)
                .toArray();
        double[] firstArray = Arrays.stream(arrayDouble, 0, halfOfFullArray)
                .toArray();
        double[] secondArray = Arrays.stream(arrayDouble, halfOfFullArray, sizeFullArray)
                .toArray();
        ValueCalculator firstValueCalculator = new ValueCalculator();
        firstValueCalculator.setArrayDouble(firstArray);

        ValueCalculator secondValueCalculator = new ValueCalculator();
        secondValueCalculator .setArrayDouble(secondArray);

        Thread firstThread = new Thread(firstValueCalculator);
        Thread secondThread = new Thread(secondValueCalculator);
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        double[] result = DoubleStream
                .concat(Arrays.stream(firstArray),
                        Arrays.stream(secondArray))
                .toArray();
        long timeToComplete = System.currentTimeMillis() - start;
        System.out.println("Elapsed time from start to finish of the program: " + timeToComplete + " millisecond");
    }
    public void setArrayDouble(double[] arrayDouble) {
        this.arrayDouble = arrayDouble;
    }
    @Override
    public void run() {
        for (int i = 0; i < halfOfFullArray; i++) {
            arrayDouble[i] = (float) (arrayDouble[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}