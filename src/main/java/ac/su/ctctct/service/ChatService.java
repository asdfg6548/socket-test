package ac.su.ctctct.service;

import ac.su.ctctct.domain.Chat;
import ac.su.ctctct.domain.ChatMessage;
import ac.su.ctctct.domain.Room;
import ac.su.ctctct.domain.User;
import ac.su.ctctct.repository.ChatRepository;
import ac.su.ctctct.repository.RoomRepository;
import ac.su.ctctct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public Chat saveChatMessage(ChatMessage chatMessage) {
        Room room = roomRepository.findById(chatMessage.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        User sender = userRepository.findById(chatMessage.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));

        Chat chat = new Chat(room, sender, chatMessage.getContent(), LocalDateTime.now());
        return chatRepository.save(chat);
    }

    public List<Chat> getChatMessagesByRoomNum(Long roomNum) {
        return chatRepository.findByRoomRoomNum(roomNum);
    }

}
