package sockets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class ChatWebSocket {
    private ChatService service;
    private Session sesion;

    public ChatWebSocket(ChatService service) {
        this.service = service;
    }

    @OnWebSocketConnect
    public void onOpen(Session sesion) {
        service.add(this);
        this.sesion = sesion;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {service.sendMessage(data);}

    @OnWebSocketClose
    public void onClose(int satusCode, String reason) {service.remove(this);}

    public void sendString(String data) {
        try {
            sesion.getRemote().sendString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
