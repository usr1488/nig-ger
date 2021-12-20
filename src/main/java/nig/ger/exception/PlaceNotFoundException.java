package nig.ger.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(long placeId) {
        super("Place not found with id: " + placeId);
    }
}
