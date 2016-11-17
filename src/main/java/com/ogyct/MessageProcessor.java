package com.ogyct;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.ogyct.messageprocessor.ActorMessageProcessor;
import com.ogyct.messageprocessor.NoteMessageProcessor;

public abstract class MessageProcessor {

    public abstract void performProcess();

    public abstract void performSubmit();

    public static MessageProcessor getInstance(final InputStream in) {
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
            }
            if ("actor".equals(msgName)) {
                return new ActorMessageProcessor(in);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
