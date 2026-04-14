package delivery;

public class Delivery {

    //стоимость доставки shipping
    //расстрояние length;
    //хрупкость fragility
    private static final int min_sum_shipping = 400;
    private static final int max_length = 30;

    public int sumShipping(int length, String cargo, boolean fragility, String workload) throws Exception {

        //Проверка длины
        if (length <= 0) {
            throw new IllegalArgumentException("Расстояние не может быть отрицательным или равным 0");
        }

        //Проверка хрупкости на большом расстоянии
        if (length >= max_length && fragility ) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км");
        }

        int shipping = 0;

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
        //cargo = big or small
        CargoDimension cargoDimension = new CargoDimension(cargo);
        shipping += cargoDimension.getSum_dimensions();

        //расчет по хрупкости
        if (fragility) {
            shipping += 300;
        }

        //коэффициент
        ServiceWorkload serviceWorkload = new ServiceWorkload(workload);
        shipping *= serviceWorkload.getMultiplier_workload();

        if (shipping < min_sum_shipping) {
            shipping = min_sum_shipping;
        }

        return shipping;
    }
}
