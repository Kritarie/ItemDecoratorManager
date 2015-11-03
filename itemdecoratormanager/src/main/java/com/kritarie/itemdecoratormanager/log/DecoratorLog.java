package com.kritarie.itemdecoratormanager.log;

/**
 * Created by Sean on 10/22/2015.
 */
public interface DecoratorLog {
    void logItemOffsetStart();
    void logItemOffsetEnd();
    void logDrawUnderStart();
    void logDrawUnderEnd();
    void logDrawOverStart();
    void logDrawOverEnd();
}
