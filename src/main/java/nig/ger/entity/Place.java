package nig.ger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Place {
    @Id
    @GeneratedValue
    private long placeId;
    private String name;
    private String country;
    private String city;
    private String location;
    private String description;
    private PlaceCategory placeCategory;
    private int rate;
}
