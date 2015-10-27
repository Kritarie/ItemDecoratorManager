package com.kritarie.itemdecortest.orderedmanager.log;

import android.util.Log;

/**
 * Created by Sean on 10/22/2015.
 */
public abstract class BaseLog {

    private String mLogTag;

    public BaseLog(String tag) {
        mLogTag = tag;
    }

    protected void report(String message) {
        Log.d(mLogTag, message);
    }

}
