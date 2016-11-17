package com.ogyct.messageprocessor;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ogyct.DebugLog;
import com.ogyct.MessageProcessor;
import com.ogyct.db.ManageActor;
import com.ogyct.mappings.Actor;

public class ActorMessageProcessor extends MessageProcessor {
    private Actor actor;

    public ActorMessageProcessor(InputStream in) {
        DebugLog.debug("Processing actor message");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Actor.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            actor = (Actor) jaxbUnmarshaller.unmarshal(in);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        DebugLog.debug(actor.getFirstName());
    }

    @Override
    public void performProcess() {

    }

    @Override
    public void performSubmit() {
        ManageActor ma = new ManageActor();
        if ("Y".equals(actor.getDeleteYN())) {
            DebugLog.debug("Deleting");
            if (actor.getActorId() == 0) {
                DebugLog.debug("No id was provided");
                return;
            }
            ma.deleteActor(actor.getActorId());
        } else {
            //insert or update
            if (ma.getActor(actor.getActorId()) == null) {
                DebugLog.debug("Inserting");
                ma.addActor(actor.getFirstName(), actor.getLastName());
            } else {
                DebugLog.debug("Updating");
                ma.updateActor(actor.getActorId(), actor.getFirstName(), actor.getLastName());
            }

        }

    }

}
