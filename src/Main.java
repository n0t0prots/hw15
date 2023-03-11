public class Main {
    public static void main(String[] args) {

        ValueCalculator valueCalculator = new ValueCalculator();
        try{
            valueCalculator.doCalc();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}