package com.ogyct;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ogyct.Utils.Utils;

/**
 * Main debug class
 * @author avgdi
 *
 */
public class DebugLog {
    public static Logger log;

    static {
        File propsFile = Utils.getResource("log4j.properties");
        Properties p = new Properties();

        try {
            p.load(new FileInputStream(propsFile));
            PropertyConfigurator.configure(p);
            log = Logger.getLogger(DebugLog.class.getName());
        } catch (IOException e) {
            throw new RuntimeException("Error initializing logger");
        }
    }

    public static void debug(Object o) {
        log.debug(o);
    }

    public static void error(Object o) {
        log.error(o);
    }

}
