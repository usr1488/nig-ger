package nig.ger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Image {
    @Id
    @GeneratedValue
    private long imageId;
    @ManyToOne(optional = false)
    private Place place;
}
