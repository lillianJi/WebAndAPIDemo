package com.Utils;

import org.apache.logging.log4j.Logger;


public class LogHelper {


    public static void info(Logger logger, String message) {
        logger.info(message + "\n");
    }

    public static void debug(Logger logger, String message) {
        logger.debug(message);
    }

    public static void warning(Logger logger, String message) {
        logger.warn(message);
    }

    public static void error(Logger logger, String message) {
        logger.error(message);
    }
}
