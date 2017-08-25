package com.codespurt.googleanalytics.engine;

import android.content.Context;

import com.codespurt.googleanalytics.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CodeSpurt on 25-08-2017.
 */

public final class AnalyticsTrackers {

    private static AnalyticsTrackers mInstance;
    private final Map<Target, Tracker> mTrackers;
    private final Context context;

    public enum Target {
        APP
    }

    private AnalyticsTrackers(Context context) {
        this.context = context.getApplicationContext();
        mTrackers = new HashMap<>();
    }

    public static synchronized void initialize(Context context) {
        if (mInstance != null) {
            throw new IllegalStateException("Extra call to initialize analytics trackers");
        }
        mInstance = new AnalyticsTrackers(context);
    }

    public static synchronized AnalyticsTrackers getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Call initialize() before getInstance()");
        }
        return mInstance;
    }

    public synchronized Tracker get(Target target) {
        if (!mTrackers.containsKey(target)) {
            Tracker tracker;
            switch (target) {
                case APP:
                    tracker = GoogleAnalytics.getInstance(context).newTracker(R.xml.app_tracker);
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled analytics target " + target);
            }
            mTrackers.put(target, tracker);
        }
        return mTrackers.get(target);
    }
}
