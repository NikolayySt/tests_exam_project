package uni.pld.masters.service;

import java.time.LocalDateTime;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;

import uni.pld.masters.model.Movie;
import uni.pld.masters.model.Viewer;

public class MovieProjectionManagerTest {

    private static MovieProjectionManager movieProjectionManager = new MovieProjectionManager();

    @BeforeClass
    public static void init() {
        Movie movie = new Movie();
        movie.setName("Test movie");
        Viewer viewer = new Viewer("test", "test");

        movieProjectionManager.scheduleMovieProjection(movie, LocalDateTime.now()
                .plusHours(3L));

        movieProjectionManager.makeReservation(viewer, movie, (byte) 1);
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with all null parameters. Expected behavior is to return "Movie name mustn`t be null or empty"
     */
    @Test
    public void scheduleMovieProjection_NullParamsTest_Test() {
        String result = movieProjectionManager.scheduleMovieProjection(null, null);

        MatcherAssert.assertThat(result, Is.is("Movie name mustn`t be null or empty"));
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with null {@linkplain movie} parameter and filled {@linkplain projectionTime}. Expected behavior is to return "Movie name mustn`t be
     * null or empty"
     */
    @Test
    public void scheduleMovieProjection_movieNullTest_Test() {
        String result = movieProjectionManager.scheduleMovieProjection(null, LocalDateTime.now());

        MatcherAssert.assertThat(result, Is.is("Movie name mustn`t be null or empty"));
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with not null {@linkplain movie} parameter, but without name and filled {@linkplain projectionTime}. Expected behavior is to return
     * "Movie name mustn`t be null or empty"
     */
    @Test
    public void scheduleMovieProjection_EmptyMovieName_Test() {
        String result = movieProjectionManager.scheduleMovieProjection(new Movie(), LocalDateTime.now());

        MatcherAssert.assertThat(result, Is.is("Movie name mustn`t be null or empty"));
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with valid {@linkplain movie} parameter and null {@linkplain projectionTime} parameter. Expected behavior is to return "Projection
     * date time mustn`t be null"
     */
    @Test
    public void scheduleMovieProjection_NullDateTimeAndValidMovie_Test() {
        Movie movie = new Movie();
        movie.setName("Test movie");
        
        String result = movieProjectionManager.scheduleMovieProjection(movie, null);

        MatcherAssert.assertThat(result, Is.is("Projection date time mustn`t be null"));
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with valid {@linkplain movie} parameter and invalid {@linkplain projectionTime} parameter(projection date must be at least after 2
     * hours from now). Expected behavior is to return "Projection date time must be at least after 2 hours from now"
     */
    @Test
    public void scheduleMovieProjection_InvalidFeatureDateAndValidMovie_Test() {
        Movie movie = new Movie();
        movie.setName("Test movie");

        String result = movieProjectionManager.scheduleMovieProjection(movie, LocalDateTime.now());

        MatcherAssert.assertThat(result, Is.is("Projection date time must be at least after 2 hours from now"));
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with valid {@linkplain movie} parameter and invalid {@linkplain projectionTime} parameter(projection date must be at least after 2
     * hours from now). Expected behavior is to return "Projection date time must be at least after 2 hours from now"
     */
    @Test
    public void scheduleMovieProjection_InvalidDateInPastAndValidMovie_Test() {
        Movie movie = new Movie();
        movie.setName("Test movie");

        String result = movieProjectionManager.scheduleMovieProjection(movie, LocalDateTime.now()
                .minusDays(5L));

        MatcherAssert.assertThat(result, Is.is("Projection date time must be at least after 2 hours from now"));
    }

    /**
     * Test that tests the functionality of scheduleMovieProjection({@link Movie} {@linkplain movie}, {@link LocalDateTime} {@linkplain projectionTime}) method.
     * Calling the method with valid {@linkplain movie} and {@linkplain projectionTime} parameters(projection date must be at least after 2 hours from now).
     * Expected behavior is to return "Movie projection scheduled successfully"
     */
    @Test
    public void scheduleMovieProjection_WithValidParameters_Test() {
        Movie movie = new Movie();
        movie.setName("Test movie");

        String result = movieProjectionManager.scheduleMovieProjection(movie, LocalDateTime.now()
                .plusHours(5L));

        MatcherAssert.assertThat(result, Is.is("Movie projection scheduled successfully"));
        MatcherAssert.assertThat(movieProjectionManager.getMovieProjections()
                .size(), Is.is(2));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with null viewer and movie parameters and invalid seatNumber - 0. Expected behavior is to return
     * "Viewer mustn`t be null".
     */
    @Test
    public void makeReservation_NullParameters_Test() {
        /*---Setup---*/
        Viewer viewer = null;
        Movie movie = null;
        byte seatNumber = (byte) 0;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer mustn`t be null"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with viewer with null names and valid movie parameters and seatNumber. Expected behavior is to return
     * "Viewer mustn`t be null".
     */
    @Test
    public void makeReservation_ViewerWithNullNames_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer(null, null);
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer names mustn`t be null"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with viewer with first name filled, but lastName null and valid movie parameters and seatNumber.
     * Expected behavior is to return "Viewer mustn`t be null".
     */
    @Test
    public void makeReservation_ViewerWithFilledFirstNameAndNullLastName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("test", null);
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer names mustn`t be null"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with viewer with first name null, but lastName filled and valid movie parameters and seatNumber.
     * Expected behavior is to return "Viewer names mustn`t be null".
     */
    @Test
    public void makeReservation_ViewerWithNullFirstNameAndFilledLastName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer(null, "test");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer names mustn`t be null"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with viewer with first name lastName filled, but empty and valid movie parameters and seatNumber.
     * Expected behavior is to return "Viewer names mustn`t be empty".
     */
    @Test
    public void makeReservation_ViewerWithFilledAsEmptyFirstAndLastName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("", "");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer names mustn`t be empty"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with viewer with valid first name and empty lastName, but empty and valid movie parameters and
     * seatNumber. Expected behavior is to return "Viewer names mustn`t be empty".
     */
    @Test
    public void makeReservation_ViewerWithValidFirstNameaAndEmptyLastName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("test", "");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer names mustn`t be empty"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with viewer with empty first name and valid last name, but empty and valid movie parameters and
     * seatNumber. Expected behavior is to return "Viewer names mustn`t be empty".
     */
    @Test
    public void makeReservation_ViewerWithEmptyFirstNameaAndValidLastName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("", "test");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Viewer names mustn`t be empty"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer, but null movie and valid seatNumber. Expected behavior is to return "Movie mustn`t
     * be null".
     */
    @Test
    public void makeReservation_WithNullMovie_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = null;
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Movie mustn`t be null"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer, but null movie name and valid seatNumber. Expected behavior is to return "Movie
     * name mustn`t be null".
     */
    @Test
    public void makeReservation_WithNullMovieName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName(null);
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Movie name mustn`t be null"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer, but empty movie name and valid seatNumber. Expected behavior is to return "Movie
     * name mustn`t be empty".
     */
    @Test
    public void makeReservation_WithEmptyMovieName_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName("");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Movie name mustn`t be empty"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer and movie, but invalid seatNumber(seat number must be from 1 to 30). Expected
     * behavior is to return "Invalid seat number, seat number must be from 0 to 30".
     */
    @Test
    public void makeReservation_WithNegativeSeatNumber_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) -15;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Invalid seat number, seat number must be from 0 to 30"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer and movie, but invalid seatNumber(seat number must be from 1 to 30). Expected
     * behavior is to return "Invalid seat number, seat number must be from 0 to 30".
     */
    @Test
    public void makeReservation_WithTooHighSeatNumber_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 80;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Invalid seat number, seat number must be from 0 to 30"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer and movie, but invalid seatNumber(seat number must be from 1 to 30). Expected
     * behavior is to return "Reservation was made successfully".
     */
    @Test
    public void makeReservation_WithValidParameters_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 15;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Reservation was made successfully"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer and movie, but already taken seatNumber. Expected behavior is to return "Seat is
     * already taken".
     */
    @Test
    public void makeReservation_WithAlreadyTakenSeat_Test() {
        /*---Setup---*/
        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName("Test movie");
        byte seatNumber = (byte) 1;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Seat is already taken"));
    }

    /**
     * Test that test the functionality of makeReservation({@link Viewer} {@linkplain viewer}, {@link Movie} {@linkplain movie}, {@link Byte}
     * {@linkplain seatNumber}) method. Calling the method with valid viewer, movie and seat number, but less than 60 minutes till the movie projection.
     * Expected behavior is to return "Too late for reservation for this movie".
     */
    @Test
    public void makeReservation_WithLessThan60MinutesBeforeMovieStart_Test() {
        /*---Setup---*/
        scheduleMovieProjectionForAfter50mins();

        Viewer viewer = new Viewer("tester", "test");
        Movie movie = new Movie();
        movie.setName("Movie after 50 mins");
        byte seatNumber = (byte) 2;

        /*---Call---*/
        String result = movieProjectionManager.makeReservation(viewer, movie, seatNumber);

        /*---Assert---*/
        MatcherAssert.assertThat(result, Is.is("Too late for reservation for this movie"));
    }

    private void scheduleMovieProjectionForAfter50mins() {
        Movie movie = new Movie();
        movie.setName("Movie after 50 mins");

        movieProjectionManager.scheduleMovieProjection(movie, LocalDateTime.now()
                .plusHours(3L));

        movieProjectionManager.getMovieProjections()
                .stream()
                .filter(projection -> projection.getMovie()
                        .getName()
                        .contentEquals(movie.getName()))
                .findFirst()
                .ifPresent(projection -> projection.setProjectionStartTime(LocalDateTime.now()
                        .plusMinutes(50L)));
    }

}
