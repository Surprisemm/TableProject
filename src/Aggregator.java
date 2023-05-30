public interface Aggregator {
    void addValue(Object value);
    double getResult();
    void reset();
}

class SumAgg implements Aggregator{

    private double res = 0;

    @Override
    public void addValue(Object value) {
        if(value instanceof String) {
            try {
                res += Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                System.out.println("В ячейке не число");
            }
        }
    }

    @Override
    public double getResult() {
        return res;
    }

    @Override
    public void reset() {
        res = 0d;
    }
}

class AverageAgg implements Aggregator{

    private double sum = 0;
    private double count = 0;

    @Override
    public void addValue(Object value) {

        try {
            if (value instanceof String) {
                sum += Double.parseDouble((String) value);
                count++;
            }
        }catch (NumberFormatException e){
            System.out.println("В ячейке не число");
        }
    }

    @Override
    public double getResult() {
        if(count == 0){return 0;}
        return sum / count;
    }
    @Override
    public void reset() {
        sum = 0d;
        count = 0d;
    }

}

class CounterAgg implements Aggregator{

    private double count = 0;

    @Override
    public void addValue(Object value) {

        double flag = 0;

        if(value instanceof String) {
            try {
                flag = Double.parseDouble((String) value);
                count++;
            } catch (NumberFormatException e) {
                System.out.println("В ячейке не число");
            }
        }

    }

    @Override
    public double getResult() {
        return count;
    }

    @Override
    public void reset() {
        count = 0d;
    }

}

class MaxAgg implements Aggregator{

    private double maxValue = 0;

    @Override
    public void addValue(Object value) {

        try {
            if (value instanceof String) {
                if (maxValue < Double.parseDouble((String) value)) {
                    maxValue = Double.parseDouble((String) value);
                }
            }
        }catch (NumberFormatException e){
            System.out.println("В ячейке не число");
        }
    }

    @Override
    public double getResult() {
        return maxValue;
    }

    @Override
    public void reset() {
        maxValue = 0d;
    }

}

class MinAgg implements Aggregator{

    private double minValue = 0;

    @Override
    public void addValue(Object value) {

        try {
            if (value instanceof String) {
                if (minValue > Double.parseDouble((String) value)) {
                    minValue = Double.parseDouble((String) value);
                }
            }
        }catch (NumberFormatException e){
            System.out.println("В ячейке не число");
        }
    }

    @Override
    public double getResult() {
        return minValue;
    }

    @Override
    public void reset() {
        minValue = 0d;
    }

}

class SqdSumAgg implements Aggregator{

    private double sqdSum = 0;

    @Override
    public void addValue(Object value) {

        try {
            double tmp = Double.parseDouble((String) value);

            if (value instanceof String) {
                sqdSum += tmp * tmp;
            }
        }catch (NumberFormatException e){
            System.out.println("В ячейке не число");
        }
    }

    @Override
    public double getResult() {
        return sqdSum;
    }

    @Override
    public void reset() {
        sqdSum = 0d;
    }

}