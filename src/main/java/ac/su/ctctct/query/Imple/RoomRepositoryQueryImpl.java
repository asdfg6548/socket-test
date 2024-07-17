package ac.su.ctctct.query.Imple;


import ac.su.ctctct.domain.Room;
import ac.su.ctctct.query.RoomRepositoryQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryQueryImpl implements RoomRepositoryQuery {
    private final EntityManager em;

    @Override
    public List<Room> getRoomByContent(String roomContent) {
        String jpql = "SELECT r FROM Room r WHERE r.roomContent = :roomContent";
        return em.createQuery(jpql, Room.class)
                .setParameter("roomContent", roomContent)
                .getResultList();
    }
}