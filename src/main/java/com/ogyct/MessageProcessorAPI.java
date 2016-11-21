package com.ogyct;

import java.io.InputStream;

import com.ogyct.messageprocessor.BaseMessageProcessor;

/**
 * The highest context level of Message Processor
 * @author avgdi
 *
 */
public class MessageProcessorAPI {

    /**
     * Process message
     * @param in InputStream
     * @return
     * @throws Exception 
     */
    public String processMessage(InputStream in) throws Exception {
        String result = "";
        
        try {
            BaseMessageProcessor bmp = BaseMessageProcessor.getProcessor(in);
            
            bmp.performProcess();
            bmp.performSubmit();
            
            result = bmp.getResult();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

}
