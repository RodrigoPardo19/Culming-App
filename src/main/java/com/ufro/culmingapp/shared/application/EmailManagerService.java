package com.ufro.culmingapp.shared.application;

import com.ufro.culmingapp.shared.domain.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailManagerService {

    private EmailSender sender;

    @Autowired
    public EmailManagerService(EmailSender sender) {
        this.sender = sender;
    }

    public void sendEmail(String from, String to, String subject, String text) {
        this.sender.send(from, to, subject, text);
    }

}
