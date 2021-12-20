package nig.ger.util;

import java.util.Objects;

public final class Constants {
    private Constants() {
    }

    public static final String ROOT_REDIRECT = "redirect:/";
    public static final String PROFILE_REDIRECT = "redirect:/profile/";
    public static final String IMAGE_FORMAT = "jpg";
    public static final String PLACE_IMAGE_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/place_image/"),
            "Folder for place images not found"
    ).getPath();
    public static final String PROFILE_IMAGE_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/profile_image/"),
            "Folder for profile images not found"
    ).getPath();
}
