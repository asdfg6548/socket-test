package ac.su.ctctct.domain;


import ac.su.ctctct.constant.RoomStatus;
import ac.su.ctctct.constant.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_num")
    private Long roomNum;

    @Column(name = "room_title")
    private String roomTitle;

    @Column(name = "room_content", columnDefinition = "TEXT")
    private String roomContent;

    private Integer roomUserCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "game_name")
    private String gameName;

    @ManyToOne
    @JoinColumn(name = "room_master_id")
    private User roomMaster;

    @Column(name = "room_status")
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Column(name = "room_password")
    private String roomPassword;

    @Column(name = "room_capacity_limit")
    private Integer roomCapacityLimit;

    @Column(name = "room_update_time")
    private LocalDateTime roomUpdateTime;

    @Column(name = "room_create_at")
    private LocalDateTime roomCreateAt;

    @ManyToMany
    @JoinTable(
            name = "room_participants",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants = new ArrayList<>();

    public Long getRoomMasterId() {
        return this.roomMaster != null ? this.roomMaster.getId() : null;
    }
}