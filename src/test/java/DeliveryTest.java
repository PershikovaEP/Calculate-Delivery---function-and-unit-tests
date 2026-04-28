import delivery.DeliveryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    DeliveryService delivery = new DeliveryService();

    @ParameterizedTest
    @DisplayName("Позитивные сценарии")
    @Tag("Positive")
    @CsvSource(
            {"31, SMALL, false, HIGH, 560.0",
             "29, BIG, true, ELEVATED, 840.0",
             "15, SMALL, false, VERY_HIGH, 480.0",
             "8, BIG, true, HIGH, 840.0",
             "1, BIG, true, NORMAL, 550.0"
    })
    void positiveTest(int length, String cargo, boolean fragility, String workload, double expected) throws Exception{

       // List<Double> expectedDelivery = Arrays.asList(560.0, 840.0, 480.0, 840.0, 550.0);
        double actual = delivery.sumShipping(length, cargo, fragility, workload);

        //assertTrue(expectedDelivery.contains(actual));
        assertEquals(expected, actual, 0.001);
    }

    @Test
    @DisplayName("Проверка стоимости доставки меньше 400")
    @Tag("Negative")
    void deliveryLessThan400Test() throws Exception{

        double actual = delivery.sumShipping(1, "BIG", false, "ELEVATED");

        assertEquals(400, actual);
    }

    @Test
    @DisplayName("Ошибка при перевозке хрупкого груза более 30км")
    @Tag("Negative")
    void fragilityAtDistanceOfMoreThan30Test() throws Exception{

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> delivery.sumShipping(31, "BIG", true, "VERY_HIGH"));

        assertEquals("Хрупкие грузы нельзя возить на расстояние более 30 км", thrown.getMessage());
    }

    @Test
    @DisplayName("Расстояние должно быть больше 0")
    @Tag("Negative")
    void shouldDistanceOfMoreThan0Test() throws Exception{

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> delivery.sumShipping(-10, "BIG", true, "VERY_HIGH"));

        assertEquals("Расстояние не может быть отрицательным или равным 0", thrown.getMessage());
    }

    //ошибка на null
    @Test
    @DisplayName("Ошибка null")
    @Tag("Negative")
    void exceptionTest() {

        Exception thrown = assertThrows(NullPointerException.class, () -> delivery.sumShipping(10, null, true, "VERY_HIGH"));

        assertEquals("Размер груза не может быть равен null", thrown.getMessage());
    }

    //граничные значения 2,10,30

}
