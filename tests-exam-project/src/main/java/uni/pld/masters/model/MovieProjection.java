package uni.pld.masters.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Class that represents Movie projection, which is for {@link Movie}. Each movie projection has start time and 30 available seats in the beginning.
 * 
 * @author nstoilov
 *
 */
public class MovieProjection {

    private Movie movie;
    private LocalDateTime projectionStartTime;
    private List<Seat> seats = new ArrayList<>();

    public MovieProjection() {
        for (int index = 1; index <= 30; index++) {
            Seat seat = new Seat((byte) index);

            seats.add(seat);
        }
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setProjectionStartTime(LocalDateTime projectionStartTime) {
        this.projectionStartTime = projectionStartTime;
    }

    public LocalDateTime getProjectionStartTime() {
        return projectionStartTime;
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

    public void takeSeat(byte seatNumber) {
        Optional<Seat> seat = seats.stream()
                .filter(s -> s.getNumber() == seatNumber)
                .findFirst();

        if (seat.isPresent()) {
            seat.get()
                    .setTaken(true);
        }
    }

}
