import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightManagerTest {

    @Test
    public void should_show_available_seats() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight("LH123", 5);
        manager.addFlight("AA900", 10);

        //when
        int seats = manager.getAvailableSeats("LH123");

        //then
        assertEquals(5, seats);
    }

    @Test
    public void should_deal_with_missing_flight() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight("LH123", 5);

        //expect
        Assertions.assertThatThrownBy(
                () -> manager.getAvailableSeats("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }
}
