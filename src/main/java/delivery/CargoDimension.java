package delivery;

public class CargoDimension {

    //габариты
    private int sum_dimensions;

    public CargoDimension(String dimensions) throws Exception {

        if (dimensions == null) {
            throw new NullPointerException("Размер груза не может быть равен null");
        }
        if (dimensions.equals("big")) {
            sum_dimensions = 200;
        } else if (dimensions.equals("small")) {
            sum_dimensions = 100;
        } else
            throw new Exception("Некорректный размер" + dimensions);
        }

    public int getSum_dimensions() {
        return sum_dimensions;
    }
}
