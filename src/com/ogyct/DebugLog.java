package com.ogyct;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DebugLog {
    public static Logger log = Logger.getLogger(DebugLog.class.getName());
    
    static {
        String log4JPropertyFile = "resources/log4j.properties";
        Properties p = new Properties();

        try {
            p.load(new FileInputStream(log4JPropertyFile));
            PropertyConfigurator.configure(p);
        } catch (IOException e) {
            
        }
    }

    public static void debug(Object o) {
        log.debug(o);
    }

    public static void error(Object o) {
        log.error(o);
    }

}
