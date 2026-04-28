package delivery;

public class DeliveryService {

    //стоимость доставки shipping
    //расстояние length;
    //хрупкость fragility
    private static final int min_sum_shipping = 400;
    private static final int max_length = 30;

    public double sumShipping(int length, String cargo, boolean fragility, String workload) throws Exception {

        ServiceWorkload serviceWorkload = ServiceWorkload.valueOf(workload);

        double shipping = (shippingLehgth(length) + shippingCargo(cargo) + shippingFraglity(fragility, length)) * serviceWorkload.getMultiplierWorkload();

        return (shipping < min_sum_shipping) ? min_sum_shipping : shipping;
    }

    //рассчет по расстоянию
    public double shippingLehgth(int length) {

        //Проверка длины
        if (length <= 0) {
            throw new IllegalArgumentException("Расстояние не может быть отрицательным или равным 0");
        }

        double shipping = 0;
        if (length >= max_length) {
            shipping = 300;
        } else if (length >= 10) {
            shipping = 200;
        } else if (length >= 2) {
            shipping = 100;
        } else if (length > 0) {
            shipping = 50;
        }
        return shipping;
    }

    public double shippingCargo(String cargo) {

        //размер груза не указан
        if (cargo == null) {
            throw new NullPointerException("Размер груза не может быть равен null");
        }

        //cargo = big or small
        CargoDimension cargoDimension = CargoDimension.valueOf(cargo);
        return cargoDimension.getSum_dimensions();
    }

    public double shippingFraglity(boolean fragility, int length) {

        //Проверка хрупкости на большом расстоянии
        if (length >= max_length && fragility) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя возить на расстояние более 30 км");
        }

        return fragility ? 300 : 0;
    }
}
