package com.ogyct;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ogyct.Utils.Utils;

public class DebugLog {
    public static Logger log = Logger.getLogger(DebugLog.class.getName());
    
    static {
        File propsFile = Utils.getResource("log4j.properties");
        Properties p = new Properties();

        try {
            p.load(new FileInputStream(propsFile));
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
