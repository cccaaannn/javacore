package com.kurtcan.javacore.utilities.message.twilio;

import com.kurtcan.javacore.utilities.message.abstracts.IMessageSender;
import com.kurtcan.javacore.utilities.message.dtos.Message;
import com.kurtcan.javacore.utilities.message.exceptions.MessageSenderException;
import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class TwilioMessageSender implements IMessageSender {

    private final String from;

    public TwilioMessageSender(String from, String sid, String token) {
        Twilio.init(sid, token);
        this.from = from;
    }

    @Override
    public void send(Message message) throws MessageSenderException {
        if (Objects.isNull(message.getTo())) {
            throw new MessageSenderException("To list is null");
        }
        try {
            for (String to : message.getTo()) {
                com.twilio.rest.api.v2010.account.Message messageResponse = com.twilio.rest.api.v2010.account.Message.creator(
                                new com.twilio.type.PhoneNumber(to),
                                new com.twilio.type.PhoneNumber(this.from),
                                message.getBody())
                        .create();
                log.debug("Message sent, sid: {}", messageResponse.getSid());
            }

            log.info("Messages sent");
        } catch (Exception e) {
            throw new MessageSenderException();
        }
    }

}
