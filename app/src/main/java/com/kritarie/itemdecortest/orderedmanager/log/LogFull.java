package com.kritarie.itemdecortest.orderedmanager.log;

/**
 * Created by Sean on 10/22/2015.
 */
public class LogFull extends LogBasic implements DecoratorLog {

    private long mTimeBuffer;

    public LogFull(String tag) {
        super(tag);
    }

    @Override
    public void logItemOffsetStart() {
        mTimeBuffer = System.currentTimeMillis();
    }

    @Override
    public void logItemOffsetEnd() {
        super.logItemOffsetEnd();
        report("Item offset in: " + (System.currentTimeMillis() - mTimeBuffer) + " millis");
    }

    @Override
    public void logDrawUnderStart() {
        mTimeBuffer = System.currentTimeMillis();
    }

    @Override
    public void logDrawUnderEnd() {
        super.logDrawUnderEnd();
        report("Items drawn under in: " + (System.currentTimeMillis() - mTimeBuffer) + " millis");
    }

    @Override
    public void logDrawOverStart() {
        mTimeBuffer = System.currentTimeMillis();
    }

    @Override
    public void logDrawOverEnd() {
        super.logDrawOverEnd();
        report("Items drawn over in: " + (System.currentTimeMillis() - mTimeBuffer) + " millis");
    }
}
