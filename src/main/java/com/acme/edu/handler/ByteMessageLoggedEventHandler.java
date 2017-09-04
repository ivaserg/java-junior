package com.acme.edu.handler;

import com.acme.edu.event.ByteMessageLoggedEvent;
import com.acme.edu.exception.MessageFormattingException;
import com.acme.edu.exception.MessageHandlingException;
import com.acme.edu.exception.MessageSavingException;
import com.acme.edu.formatter.Formatter;
import com.acme.edu.framework.Handler;
import com.acme.edu.saver.Saver;

public class ByteMessageLoggedEventHandler implements Handler<ByteMessageLoggedEvent>{
    private static final String TYPE_DESCRIPTION = "primitive: ";
    private int aggregatedValue;

    private Saver saver;
    private Formatter formatter;

    public ByteMessageLoggedEventHandler(Saver saver, Formatter formatter) {
        this.saver = saver;
        this.formatter = formatter;
    }


    @Override
    public void onEvent(ByteMessageLoggedEvent event) throws MessageHandlingException {
        byte currentValue = Byte.valueOf(event.getMessage());
        if (event.isCollectionNeeded()) {
            if (isOverflow(currentValue)) {  // overFlow
                processMessage(TYPE_DESCRIPTION + aggregatedValue);
                aggregatedValue = currentValue;
            }
            else {
                aggregatedValue += currentValue;
            }

        } else {
            if (isOverflow(currentValue)) {  // overFlow
                processMessage(TYPE_DESCRIPTION + aggregatedValue);
                processMessage(TYPE_DESCRIPTION + currentValue);
            }
            else {
                aggregatedValue += currentValue;
                processMessage(TYPE_DESCRIPTION + aggregatedValue);
            }

            aggregatedValue=0;
        }

    }

    private void processMessage(String message) throws MessageHandlingException {
        try {
            saver.save(formatter.format(message));
        } catch (MessageSavingException e) {
            throw new MessageHandlingException(String.format("Failed to save BYTE message: \"%s\"", message), e.getCause());
        } catch (MessageFormattingException e) {
            throw new MessageHandlingException(String.format("Failed to format BYTE message: \"%s\"", message), e.getCause());
        }
    }

    public boolean isOverflow(int currentValue) {
        if (aggregatedValue > 0 && currentValue > 0) {
            return (Byte.MAX_VALUE - aggregatedValue < currentValue);
        }
        else if (aggregatedValue < 0 && currentValue < 0) {
            return  (Byte.MIN_VALUE  - aggregatedValue >  currentValue);
        }
        else {
            return false;
        }

    }

}
