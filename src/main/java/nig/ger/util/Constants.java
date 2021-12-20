package nig.ger.util;

import java.util.Objects;

public final class Constants {

    private Constants() {
    }

    public static final String ROOT_REDIRECT = "redirect:/";
    public static final String PROFILE_REDIRECT = "redirect:/profile/{userId}";
    public static final String IMAGE_FORMAT = "jpg";
    public static final String IMAGE_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/img/")
    ).getPath();
    public static final String PROFILE_PHOTO_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/profile_img/")
    ).getPath();
}
