package delivery;

public enum CargoDimension {

    BIG(200),
    SMALL(100);

    //габариты
    private int dimensions;

    CargoDimension(int dimensions) {
        this.dimensions = dimensions;
    }

    public int getSum_dimensions() {
        return dimensions;
    }
}
