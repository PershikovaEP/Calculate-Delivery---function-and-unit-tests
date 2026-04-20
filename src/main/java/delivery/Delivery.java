package delivery;

public class Delivery {

    //стоимость доставки shipping
    //расстояние length;
    //хрупкость fragility
    private static final int min_sum_shipping = 400;
    private static final int max_length = 30;

    public double sumShipping(int length, String cargo, boolean fragility, String workload) throws Exception {

        //Проверка длины
        if (length <= 0) {
            throw new IllegalArgumentException("Расстояние не может быть отрицательным или равным 0");
        }

        //Проверка хрупкости на большом расстоянии
        if (length >= max_length && fragility ) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км");
        }

        double shipping = 0;

        //расчет по расстоянию
        if (length >= max_length) {
            shipping += 300;
        } else if (length >= 10 ) {
            shipping += 200;
        } else if (length >= 2 ) {
            shipping += 100;
        } else if (length > 0 ) {
            shipping += 50;
        }

        //расчет по весогабаритам
        //размер груза не указан
        if (cargo == null) {
            throw new NullPointerException("Размер груза не может быть равен null");
        }

        //cargo = big or small
        CargoDimension cargoDimension = CargoDimension.valueOf(cargo);
        shipping += cargoDimension.getSum_dimensions();

        //расчет по хрупкости
        if (fragility) {
            shipping += 300;
        }

        //коэффициент
        ServiceWorkload serviceWorkload = ServiceWorkload.valueOf(workload);
        shipping *= serviceWorkload.getMultiplierWorkload();

        if (shipping < min_sum_shipping) {
            shipping = min_sum_shipping;
        }

        return shipping;
    }
}
