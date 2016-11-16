package com.ogyct;

import org.apache.log4j.Logger;

public class DebugLog {
    public static Logger log = Logger.getLogger(DebugLog.class.getName());

    public static void debug(Object o) {
        log.debug(o);
    }

    public static void error(Object o) {
        log.error(o);
    }

}
