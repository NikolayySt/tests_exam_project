package uni.pld.masters.model;

/**
 * 
 * Class that represent a single Seat(place) for a {@link MovieProjection}. {@linkplain Seat} can be taken or free
 * 
 * @author nstoilov
 *
 */
public class Seat {

    private byte number;
    private boolean taken;

    public Seat(byte number) {
        this.number = number;
    }

    public byte getNumber() {
        return number;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isTaken() {
        return taken;
    }

}
