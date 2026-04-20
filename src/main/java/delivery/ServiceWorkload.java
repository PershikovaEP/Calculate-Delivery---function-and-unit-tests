package delivery;

public enum ServiceWorkload {

    VERY_HIGH(1.6),
    HIGH(1.4),
    ELEVATED(1.2),
    NORMAL(1);

    private final double multiplierWorkload;

    ServiceWorkload(double multiplierWorkload) {
        this.multiplierWorkload = multiplierWorkload;
    }

    //конструктор, если указана загруженность


    public double getMultiplierWorkload() {
        return multiplierWorkload;
    }
}
