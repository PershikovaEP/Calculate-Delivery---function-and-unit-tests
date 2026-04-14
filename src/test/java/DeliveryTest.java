import delivery.Delivery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    Delivery delivery = new Delivery();

    @ParameterizedTest
    @DisplayName("Позитивные сценарии")
    @Tag("Хеппи-пас")
    @CsvSource(
            {"31, small, false, high",
             "29, big, true, elevated",
             "15, small, false, very_high",
             "8, big, true, high",
             "1, big, true, norm"
    })
    void positiveTest(int length, String cargo, boolean fragility, String workload) throws Exception{

        List<Integer> expectedDelivery = Arrays.asList(560, 840, 480, 840, 550);
        int actual = delivery.sumShipping(length, cargo, fragility, workload);

        assertTrue(expectedDelivery.contains(actual));
    }

    @Test
    @DisplayName("Проверка стоимости доставки меньше 400")
    @Tag("Негативные сценарии")
    void deliveryLessThan400Test() throws Exception{

        double actual = delivery.sumShipping(1, "big", false, "elevated");

        assertEquals(400, actual);
    }

    @Test
    @DisplayName("Ошибка при перевозке хрупкого груза более 30км")
    @Tag("Негативные сценарии")
    void fragilityAtDistanceOfMoreThan30Test() throws Exception{

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> delivery.sumShipping(31, "big", true, "very_high"));

        assertEquals("Хрупкие грузы нельзя возить на расстояние более 30 км", thrown.getMessage());
    }

    @Test
    @DisplayName("Расстояние должно быть больше 0")
    @Tag("Негативные сценарии")
    void shouldDistanceOfMoreThan0Test() throws Exception{

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> delivery.sumShipping(-10, "big", true, "very_high"));

        assertEquals("Расстояние не может быть отрицательным или равным 0", thrown.getMessage());
    }

    //ошибка на null
    @Test
    @DisplayName("Ошибка null")
    @Tag("Негативные сценарии")
    void exceptionTest() {

        Exception thrown = assertThrows(NullPointerException.class, () -> delivery.sumShipping(10, null, true, "very_high"));

        assertEquals("Размер груза не может быть равен null", thrown.getMessage());
    }

    //граничные значения 2,10,30

}
