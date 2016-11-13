package com.ogyct.db;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import com.ogyct.DebugLog;
import com.ogyct.mappings.Actor;

public class ManageActor {
    private static SessionFactory factory;

    public ManageActor() {
        try {
            factory = new Configuration().configure().addAnnotatedClass(Actor.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
//
//        /* Add few employee records in database */
//        Long empID1 = MA.addActor("Zara", "Ali");
//        Long empID2 = MA.addActor("Daisy", "Das");
//        Long empID3 = MA.addActor("John", "Paul");
//
//        /* List down all the employees */
//        MA.listActors();
//
//        /* Update employee's records */
//        MA.updateActor(empID1, 5000);
//
//        /* Delete an employee from the database */
//        MA.deleteActor(empID2);
//
//        /* List down new list of the employees */

        DebugLog.debug("TestLog");
    }

    /* Method to CREATE an employee in the database */
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
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return actorID;
    }

    /* Method to  READ all the employees */
    public void listActors() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Actor> actors = session.createQuery("FROM Actor").list();
            for (Iterator<Actor> iterator = actors.iterator(); iterator.hasNext();) {
                Actor employee = iterator.next();
                DebugLog.debug("First Name: " + employee.getFirstName());
                DebugLog.debug("Last Name:  " + employee.getLastName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateActor(Long ActorID, String name, String lastName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Actor actor = (Actor) session.get(Actor.class, ActorID);
            actor.setFirstName(name);
            actor.setLastName(lastName);
            DebugLog.debug("Updating actor " + actor.toString());
            session.update(actor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteActor(Long ActorID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Actor actor = (Actor) session.get(Actor.class, ActorID);
            session.delete(actor);
            DebugLog.debug("Deleting " + actor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
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
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
