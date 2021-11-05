package com.ufro.culmingapp.shared.domain;

public interface EmailSender {
    void send(String from, String to, String subject, String text);
}
