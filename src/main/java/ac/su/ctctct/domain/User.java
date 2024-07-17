package ac.su.ctctct.domain;

import ac.su.ctctct.constant.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "user_profile")
    private String userProfile;

    @Column(name = "user_profile_pic")
    private String userProfilePic;

    @Column(name = "user_temperature_level")
    private Integer userTemperatureLevel;


    @Column(name = "user_nickname", nullable = false, unique = true)
    private String userNickname;

    @Column(name = "user_password", nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private UserStatus status; // enum: ONLINE, OFFLINE

    @OneToMany(mappedBy = "sender")
    private List<Chat> sentChats;

}

