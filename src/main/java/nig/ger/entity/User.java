package nig.ger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    private long userId;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String country;
    private String city;
    private List<Long> placeIdsLst;
    private List<Long> friendIdsLst;
    private String status;
    private boolean isOnline;
}
