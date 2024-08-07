package ac.su.ctctct.controller;

import ac.su.ctctct.domain.User;
import ac.su.ctctct.repository.UserRepository;
import org.kurento.client.KurentoClient;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 채팅 연결 및 메시지를 관리하는 WebSocket 핸들러
public class ChatWebSocketHandler extends TextWebSocketHandler {

    // 미디어 스트림 처리를 위한 KurentoClient 인스턴스
    private final KurentoClient kurentoClient;
    // 사용자 세션을 관리하기 위한 ConcurrentHashMap
    private final Map<String, UserSession> userSessions = new ConcurrentHashMap<>();
    User user = new User(); // 사용자의 정보를 담는 객체

    // 생성자, KurentoClient 인스턴스를 초기화
    public ChatWebSocketHandler(KurentoClient kurentoClient) {
        this.kurentoClient = kurentoClient;
    }

    // 클라이언트가 WebSocket 연결을 맺었을 때 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 새로운 사용자 세션 생성 및 저장
        UserSession userSession = new UserSession(session);
        userSessions.put(session.getId(), userSession);
        System.out.println("Chat Connection established with session ID: " + session.getId());
    }

    // 클라이언트로부터 텍스트 메시지를 받았을 때 호출되는 메서드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // JSON 형태의 메시지를 Map으로 변환
        Map<String, Object> msg = new ObjectMapper().readValue(payload, Map.class);
        String id = (String) msg.get("id");

        // 메시지 타입에 따라 처리
        if ("message".equals(id)) {
            handleMessage(session, msg);
        }
    }

    // 실제 메시지 처리 로직
    private void handleMessage(WebSocketSession session, Map<String, Object> msg) {
        String message = (String) msg.get("message");
        userSessions.values().forEach(u -> {
            try {
                // 로그인 시 닉네임 받아오게 해야함
                String randomName = "User" + (int) (Math.random() * 1000);
                // 현재 세션이 아니고 세션이 열려 있는 경우 메시지를 전송
                if (!u.getSession().getId().equals(session.getId()) && u.getSession().isOpen()) {
                    // 타임스탬프 생성
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    // 응답 메시지 생성
                    ObjectNode response = new ObjectMapper().createObjectNode();
                    response.put("id", "message");
                    response.put("message", message);
                    response.put("sessionId", session.getId());
                    response.put("nickName", randomName); // 로그인 시 닉네임 받아오게 해야함
                    response.put("timestamp", timestamp); // 타임스탬프 추가
                    // 메시지 전송
                    u.getSession().sendMessage(new TextMessage(response.toString()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 클라이언트가 WebSocket 연결을 종료했을 때 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 사용자 세션 제거
        userSessions.remove(session.getId());
        System.out.println("Chat Connection closed with session ID: " + session.getId());
    }
}
