package ac.su.ctctct.repository;

import ac.su.ctctct.domain.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
    Flux<ChatMessage> findAllByRoomId(Long roomId);
}