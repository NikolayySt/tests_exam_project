package uni.pld.masters.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import uni.pld.masters.model.Movie;
import uni.pld.masters.model.MovieProjection;
import uni.pld.masters.model.Seat;
import uni.pld.masters.model.Viewer;

/**
 * 
 * Class that is managing the whole functionality for {@link MovieProjection}.
 * 
 * @author nstoilov
 *
 */
public class MovieProjectionManager {

    private List<MovieProjection> movieProjections = new ArrayList<>();

    /**
     * The method is used for creation of {@link MovieProjection}.
     * 
     * @Important {@linkplain projectionTime} Must be set for at least after 2 hours from the creation time.
     * 
     * @param movie - Mandatory parameter(also name is mandatory) - This is the movie name
     * @param projectionTime - Mandatory parameter. This is when the movie projection will be started. Must be set for at least after 2 hours from now, so the
     *            users could see that movie projection
     * 
     * @return Message with an error message or success message for the given parameters.
     */
    public String scheduleMovieProjection(Movie movie, LocalDateTime projectionTime) {
        if (movie == null || movie.getName() == null) {
            return "Movie name mustn`t be null or empty";
        }

        if (projectionTime == null) {
            return "Projection date time mustn`t be null";
        }

        if (LocalDateTime.now()
                .plusHours(2L)
                .isAfter(projectionTime)) {
            return "Projection date time must be at least after 2 hours from now";
        }

        MovieProjection movieProjection = new MovieProjection();
        movieProjection.setMovie(movie);
        movieProjection.setProjectionStartTime(projectionTime);

        movieProjections.add(movieProjection);

        return "Movie projection scheduled successfully";
    }

    /**
     * The method is used for reserving a {@link Seat} for certain movie.
     * 
     * @Important Reservations could be made only till 1 hour before the projection start.
     * 
     * @param viewer - Mandatory parameter(names are also mandatory) - This is to which name the reservation will be
     * @param movie - Mandatory(with filled name). This is for which movie the user want to make reservation.
     * @param seatNumber - Mandatory(values from 1-30 available). This is the number of the desired seat, reservation is only successful if the
     *            {@linkplain seatNumber} for the {@linkplain movie} is free.
     * 
     * @return Message with an error message or success message for the given parameters.
     */
    public String makeReservation(Viewer viewer, Movie movie, byte seatNumber) {
        String errorMessage = null;

        errorMessage = validateViewer(viewer);
        if (errorMessage != null) {
            return errorMessage;
        }

        errorMessage = validateMovie(movie);
        if (errorMessage != null) {
            return errorMessage;
        }

        errorMessage = validateSeatNumber(movie, seatNumber);
        if (errorMessage != null) {
            return errorMessage;
        }

        errorMessage = validateReservationTime(movie);
        if (errorMessage != null) {
            return errorMessage;
        }

        String desiredMovieName = movie.getName();
        movieProjections.stream()
                .filter(projection -> desiredMovieName.equals(projection.getMovie()
                        .getName()))
                .forEach(projection -> projection.takeSeat(seatNumber));

        return "Reservation was made successfully";
    }

    List<MovieProjection> getMovieProjections() {
        return movieProjections;
    }

    private String validateViewer(Viewer viewer) {
        if (viewer == null) {
            return "Viewer mustn`t be null";
        }

        if (viewer.getFirstName() == null || viewer.getLastName() == null) {
            return "Viewer names mustn`t be null";
        }

        if (viewer.getFirstName()
                .trim()
                .isEmpty()
                || viewer.getLastName()
                        .trim()
                        .isEmpty()) {
            return "Viewer names mustn`t be empty";
        }

        return null;
    }

    private String validateMovie(Movie movie) {
        if (movie == null) {
            return "Movie mustn`t be null";
        }

        if (movie.getName() == null) {
            return "Movie name mustn`t be null";
        }

        if (movie.getName()
                .trim()
                .isEmpty()) {
            return "Movie name mustn`t be empty";
        }

        return null;
    }

    private String validateSeatNumber(Movie movie, byte seatNumber) {
        if (seatNumber < 0 || seatNumber > 30) {
            return "Invalid seat number, seat number must be from 0 to 30";
        }

        Predicate<MovieProjection> movieMatcher = dto -> dto
                .getMovie()
                .getName()
                .equals(movie.getName());

        Predicate<MovieProjection> seatTaken = dto -> dto
                .getSeats()
                .stream()
                .anyMatch(seat -> seat.getNumber() == seatNumber && seat.isTaken());

        if (movieProjections.stream()
                .anyMatch(projection -> movieMatcher.test(projection) && seatTaken.test(projection))) {
            return "Seat is already taken";
        }
        
        return null;
    }

    private String validateReservationTime(Movie movie) {
        Predicate<MovieProjection> movieNameMatcher = projection ->  projection
                .getMovie()
                .getName()
                .equals(movie.getName());
        
        Predicate<MovieProjection> notEnoughTimeForReservationMathcer = projection -> projection.getProjectionStartTime()
                .isBefore(LocalDateTime
                        .now()
                        .plusHours(1L));
        
        if (movieProjections.stream()
                .anyMatch(projection -> movieNameMatcher.test(projection) && notEnoughTimeForReservationMathcer.test(projection))) {
            return "Too late for reservation for this movie";
        }

        return null;
    }

}
