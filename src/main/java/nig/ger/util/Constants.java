package nig.ger.util;

import java.util.Objects;

public final class Constants {
    private Constants() {
    }

    public static final String ROOT_REDIRECT = "redirect:/";
    public static final String IMAGE_FORMAT = "jpg";
    public static final String IMAGE_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/img/")
    ).getPath();
}
