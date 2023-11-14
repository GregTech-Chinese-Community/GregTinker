package cn.gtcommunity.gregtinker.api.utils;

import org.apache.logging.log4j.Logger;

public class GTiLog {
    public static Logger logger;

    public GTiLog() {/**/}

    public static void init(Logger modLogger)
    {
        logger = modLogger;
    }
}
