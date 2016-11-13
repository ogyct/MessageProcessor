package com.ogyct.messageprocessor;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ogyct.MessageProcessor;
import com.ogyct.mappings.Note;

public class NoteMessageProcessor extends MessageProcessor {
    
    private Note note;
    
    public NoteMessageProcessor(InputStream in) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Note.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            note = (Note) jaxbUnmarshaller.unmarshal(in);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(note);
    }

    @Override
    public void performProcess() {
        
        
    }

    @Override
    public void performSubmit() {
        
    }
    


}
