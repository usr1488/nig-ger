package nig.ger.util;

import java.util.Objects;

public interface PathConstants {
    String IMAGE_LOCATION = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("static/img/")
    ).getPath();
}
