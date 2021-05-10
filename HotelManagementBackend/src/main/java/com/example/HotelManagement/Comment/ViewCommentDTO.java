package com.example.HotelManagement.Comment;

public class ViewCommentDTO {

    private int  guestId;

    private String guestName;

    private String text;

    private Long date;

    private String topic;

    public ViewCommentDTO(int guestId, String guestName, String text, Long date, String topic) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.text = text;
        this.date = date;
        this.topic = topic;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
