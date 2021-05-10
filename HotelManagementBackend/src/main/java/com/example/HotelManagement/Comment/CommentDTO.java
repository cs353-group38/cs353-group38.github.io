package com.example.HotelManagement.Comment;

public class CommentDTO {

    private int reservationId;

    private String text;

    private Long date;

    private String topic;

    public CommentDTO(int reservationId, String text, Long date, String topic) {
        this.reservationId = reservationId;
        this.text = text;
        this.date = date;
        this.topic = topic;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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
