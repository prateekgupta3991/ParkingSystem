package gojek.constants;

/**
 * Created by prateekgupta on 07/10/17.
 */
public enum InfoType {

    STATUS(1, "Status of Parking Lot"),
    CARS_WITH_COLOR(2, "All cars with Color"),
    PARKING_SLOT_OF_CAR(3, "Parking Lot for a car");

    private Integer code;
    private String description;

    private InfoType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
