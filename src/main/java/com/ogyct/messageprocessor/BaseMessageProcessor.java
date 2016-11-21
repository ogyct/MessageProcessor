package com.ogyct.messageprocessor;

import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.ogyct.DebugLog;

/**
 * Base abstract class for message processors
 * @author avgdi
 *
 */
public abstract class BaseMessageProcessor {

    protected String result;

    /**
     * Business rules should be performed here
     */
    public void performProcess() {
        DebugLog.debug("performProcess() called");
    }

    /**
     * DB persistence operations should be performed here
     */
    public void performSubmit() {
        DebugLog.debug("performSubmit() called");
    }

    /**
     * Parse xml message and return right message processor
     * @param in
     * @return
     * @throws Exception
     */
    public static BaseMessageProcessor getProcessor(final InputStream in) throws Exception {
        XMLStreamReader streamReader = null;
        String msgName = null;
        try {
            //Stax parser for the fastest xml name
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            streamReader = inputFactory.createXMLStreamReader(in);
            if (streamReader.hasNext()) {
                streamReader.next();
                msgName = streamReader.getLocalName();
                in.reset();
            }
            if ("note".equals(msgName)) {
                return new NoteMessageProcessor(in);
            } else if ("actor".equals(msgName)) {
                return new ActorMessageProcessor(in);
            } else if ("address".equals(msgName)) {
                return null;
            } else {
                throw new RuntimeException("Unsupported message type");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
