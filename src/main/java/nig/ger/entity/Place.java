package nig.ger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String address;
    private String description;
    private PlaceCategory placeCategory;
    private int rate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place", orphanRemoval = true)
    private List<Image> images;
}
