package nig.ger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Place {
    private long placeId;
    private String name;
    private String country;
    private String city;
    private String location;
    private String description;
    private Category category;
    private int rate;
}
