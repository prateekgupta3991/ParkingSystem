package gojek.entities;

/**
 * Created by prateekgupta on 07/10/17.
 */
public class ParkingSlot {

    private Integer slotId;
    private Boolean vacant;

    public ParkingSlot() {
    }

    public ParkingSlot(Integer slotId, Boolean vacant) {
        this.slotId = slotId;
        this.vacant = vacant;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Boolean getVacant() {
        return vacant;
    }

    public void setVacant(Boolean vacant) {
        this.vacant = vacant;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotId=" + slotId +
                ", vacant=" + vacant +
                '}';
    }
}
