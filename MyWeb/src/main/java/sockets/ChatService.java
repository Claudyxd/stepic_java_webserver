package sockets;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {
    private Set<ChatWebSocket> chatWebSockets;

    public ChatService() {
        chatWebSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatWebSocket socket : chatWebSockets) {
            try {
                socket.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(ChatWebSocket socket) {chatWebSockets.add(socket);}

    public void remove(ChatWebSocket socket) {chatWebSockets.remove(socket);}
}
