package com.ogyct.messageprocessor;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ogyct.DebugLog;
import com.ogyct.db.ManageActor;
import com.ogyct.mappings.Actor;

/**
 * Processor for Actor message
 * @author avgdi
 *
 */
public class ActorMessageProcessor extends BaseMessageProcessor {
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
        //no business rules here yet
    }

    @Override
    public void performSubmit() {
        super.performSubmit();
        ManageActor ma = new ManageActor();
        if ("Y".equals(actor.getDeleteYN())) {
            DebugLog.debug("Deleting");
            if (actor.getActorId() == 0) {
                DebugLog.debug("No id was provided");
                return;
            }
            result = ma.deleteActor(actor.getActorId()).toString();
        } else {
            //insert or update
            if (ma.getActor(actor.getActorId()) == null) {
                DebugLog.debug("Inserting");
                result = ma.addActor(actor.getFirstName(), actor.getLastName()).toString();
            } else {
                DebugLog.debug("Updating");
                result = ma.updateActor(actor.getActorId(), actor.getFirstName(), actor.getLastName()).toString();
            }
        }
    }

}
