package com.example.mascotaszone.models;

public class ChatPreview {
    private String id;
    private String userName;
    private String lastMessage;
    private String time;

    public ChatPreview(String id, String userName, String lastMessage, String time) {
        this.id = id;
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public String getId() { return id; }
    public String getUserName() { return userName; }
    public String getLastMessage() { return lastMessage; }
    public String getTime() { return time; }
}
