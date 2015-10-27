package com.kritarie.itemdecortest.orderedmanager.log;

/**
 * Created by Sean on 10/22/2015.
 */
public class LogBasic extends BaseLog implements DecoratorLog {

    private int mItemOffsetCount;
    private int mDrawUnderCount;
    private int mDrawOverCount;

    public LogBasic(String tag) {
        super(tag);
    }

    @Override
    public void logItemOffsetStart() {
        //NOP
    }

    @Override
    public void logItemOffsetEnd() {
        report("Item offset call count: " + ++mItemOffsetCount);
    }

    @Override
    public void logDrawUnderStart() {
        //NOP
    }

    @Override
    public void logDrawUnderEnd() {
        report("Draw under call count: " + ++mDrawUnderCount);
    }

    @Override
    public void logDrawOverStart() {
        //NOP
    }

    @Override
    public void logDrawOverEnd() {
        report("Draw over call count: " + ++mDrawOverCount);
    }
}
