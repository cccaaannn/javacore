package com.kurtcan.javacore.utilities.message.abstracts;

import com.kurtcan.javacore.utilities.message.dtos.Message;
import com.kurtcan.javacore.utilities.message.exceptions.MessageSenderException;

public interface IMessageSender {
    void send(Message message) throws MessageSenderException;
}
