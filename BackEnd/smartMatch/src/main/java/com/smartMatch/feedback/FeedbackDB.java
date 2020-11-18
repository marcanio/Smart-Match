package com.smartMatch.feedback;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class FeedbackDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String data;

    private int severity;

    public FeedbackDB() {
    }

    public FeedbackDB(String data, int severity) {
        this.data = data;
        this.severity = severity;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}