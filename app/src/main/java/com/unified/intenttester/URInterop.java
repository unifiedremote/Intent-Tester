package com.unified.intenttester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

import java.util.List;

public class URInterop
{
    public static final String ACTION_URI_SEND = "com.unifiedremote.ACTION_URI_SEND";
    public static final String ACTION_URI_CONFIGURE = "com.unifiedremote.ACTION_URI_CONFIGURE";
    public static final String EXTRA_URI = "com.unifiedremote.EXTRA_URI";

    public static void configure(Activity activity, int requestCode) {
        Intent intent = new Intent(ACTION_URI_CONFIGURE);

        // verify that the intent resolves (i.e Unified Remote is installed)
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            Toast.makeText(activity, "Unified Remote not installed...", Toast.LENGTH_LONG).show();
        }
    }

    public static String result(Intent data) {
        if (data != null)
            if (data.hasExtra(EXTRA_URI))
                return data.getStringExtra(EXTRA_URI);
        return "";
    }

    public static void send(Context context, String uri) {
        context.sendBroadcast(new Intent(ACTION_URI_SEND).putExtra(EXTRA_URI, uri));
    }
}
