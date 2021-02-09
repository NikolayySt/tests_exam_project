package uni.pld.masters.model;

/**
 * 
 * Class that represents Viewer, used to make reservations for movies
 * 
 * @author nstoilov
 *
 */
public class Viewer {

    private String firstName;
    private String lastName;

    public Viewer(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
