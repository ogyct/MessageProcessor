package com.ogyct.db;

import java.util.List;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.ogyct.DebugLog;
import com.ogyct.mappings.Actor;

/**
 * Class serves to execute basic DB persistence operations for Actor Table.
 * @author avgdi
 *
 */
public class ManageActor {
    private SessionFactory factory;

    /**
     * Create hibernate factory
     */
    public ManageActor() {
        factory = SeesionFactoryBuilder.getFactory();
    }

    /** 
     * Method to CREATE an employee in the database 
     * 
     */
    public Long addActor(String fname, String lname) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long actorID = null;
        try {
            tx = session.beginTransaction();
            Actor actor = new Actor();
            actor.setFirstName(fname);
            actor.setLastName(lname);
            actorID = (Long) session.save(actor);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return actorID;
    }

    /**
     * Returns list of actors
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Actor> listActors() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Actor> actors = null;
        try {
            tx = session.beginTransaction();
            actors = session.createQuery("FROM Actor").list();
            for (Iterator<Actor> iterator = actors.iterator(); iterator.hasNext();) {
                Actor actor = iterator.next();
                DebugLog.debug("First Name: " + actor.getFirstName());
                DebugLog.debug("Last Name:  " + actor.getLastName());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return actors;
    }

    /**
     * Updates a row in Actor table
     * @param ActorID
     * @param name
     * @param lastName
     * @return actor
     */
    public Actor updateActor(Long ActorID, String name, String lastName) {
        Session session = factory.openSession();
        Transaction tx = null;
        Actor actor = null;
        try {
            tx = session.beginTransaction();
            actor = (Actor) session.get(Actor.class, ActorID);
            actor.setFirstName(name);
            actor.setLastName(lastName);

            DebugLog.debug("Updating " + actor);
            session.update(actor);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return actor;
    }

    /**
     * Delete actor row
     * @param ActorID
     * @return
     */
    public Actor deleteActor(Long ActorID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Actor actor = null;
        try {
            tx = session.beginTransaction();
            actor = (Actor) session.get(Actor.class, ActorID);
            session.delete(actor);
            DebugLog.debug("Deleting " + actor);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return actor;
    }

    /**
     * Returns Actor object if found in DB, else returns null
     * @param ActorID
     * @return
     */
    public Actor getActor(Long ActorID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Actor actor = (Actor) session.get(Actor.class, ActorID);
            if (actor == null) {
                return null;
            }
            return actor;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
