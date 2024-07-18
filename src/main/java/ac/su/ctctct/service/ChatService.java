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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<ChatMessage> getChatMessagesByRoomId(Long roomId) {
        List<Chat> chats = chatRepository.findByRoomRoomNum(roomId);
        return chats.stream()
                .map(chat -> new ChatMessage(chat.getChatId(), chat.getSender().getId(), chat.getSender().getUsername(), chat.getContent()))
                .collect(Collectors.toList());
    }

    public void saveChatMessage(Long roomId, ChatMessage chatMessage) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        User sender = userRepository.findById(chatMessage.getSenderId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Chat chat = new Chat();
        chat.setRoom(room);
        chat.setSender(sender);
        chat.setContent(chatMessage.getMessage());
        chat.setTimestamp(LocalDateTime.now());
        chatRepository.save(chat);
    }
}
