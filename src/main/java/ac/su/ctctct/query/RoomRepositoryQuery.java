package ac.su.ctctct.query;



import ac.su.ctctct.domain.Room;

import java.util.List;

public interface RoomRepositoryQuery {
    List<Room> getRoomByContent(String roomContent);
}