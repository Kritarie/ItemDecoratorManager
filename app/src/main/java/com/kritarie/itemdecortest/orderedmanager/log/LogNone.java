package com.kritarie.itemdecortest.orderedmanager.log;

/**
 * Created by Sean on 10/22/2015.
 */
public class LogNone extends BaseLog implements DecoratorLog {

    public LogNone(String tag) {
        super(tag);
    }

    @Override
    public void logItemOffsetStart() {
        //NOP
    }

    @Override
    public void logItemOffsetEnd() {
        //NOP
    }

    @Override
    public void logDrawUnderStart() {
        //NOP
    }

    @Override
    public void logDrawUnderEnd() {
        //NOP
    }

    @Override
    public void logDrawOverStart() {
        //NOP
    }

    @Override
    public void logDrawOverEnd() {
        //NOP
    }
}
