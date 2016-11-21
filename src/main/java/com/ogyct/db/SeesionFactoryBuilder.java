package com.ogyct.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ogyct.DebugLog;
import com.ogyct.Utils.Utils;
import com.ogyct.mappings.Actor;
import com.ogyct.mappings.Address;

/**
 * 
 * @author avgdi
 *
 */
public class SeesionFactoryBuilder {
    private static SessionFactory factory;

    /**
     * Configure hibernate
     */
    static {
        if (factory == null || factory.isClosed()) {
            try {
                Configuration conf = new Configuration();
                conf.configure(Utils.getResource("hibernate.cfg.xml"));
                conf.addAnnotatedClass(Actor.class);
                conf.addAnnotatedClass(Address.class);
                factory = conf.buildSessionFactory();
            } catch (Exception ex) {
                DebugLog.error("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        } else {
            //do nothing
        }
    }

    /**
     * 
     * @return SessionFactory
     */
    public static SessionFactory getFactory() {
        if (factory == null || factory.isClosed()) {
            throw new RuntimeException("Factory is not initialized or is closed");
        }
        return factory;
    }

    /**
     * Close session factory
     * @return
     */
    public SessionFactory close() {
        if (factory == null) {
            throw new RuntimeException("Factory is not initialized");
        }
        factory.close();
        return factory;
    }

}
