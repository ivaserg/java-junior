package com.acme.edu.Messages;

import com.acme.edu.Messages.LogMessage;

/**
 * Created by vanbkin on 27.08.2017.
 */
public class DescriptionEnricher {

    public static void enrich(LogMessage message) {
         message.setMessage(message.getTypeDescription() + message.getMessage());
    }
}
