package ac.su.ctctct.service;

import ac.su.ctctct.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ChatService {
    @Transactional
    public Mono<ChatMessage> saveChatMessage(RequestMessageDto chat) {
        return chatMessageRepository.save(
                new ChatMessage(chat.ge(), chat.getContent(), chat.getWriterId(), new Date()));
    }
}
