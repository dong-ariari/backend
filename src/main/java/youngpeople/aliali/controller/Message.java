package youngpeople.aliali.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Data
@NoArgsConstructor
public class Message {

    private String message;
    private Object data;
    private String accessToken;
    private String refreshToken;

    public void addToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Message(String message, Object data, String accessToken, String refreshToken) {
        this.message = message;
        this.data = data;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
