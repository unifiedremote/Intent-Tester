# Intent-Tester
This is a test application for integrating Unified Remote with a 3rd party application that wants to be able to build and send action URIs. Action URIs can be used to open parts of the apps, open a specific remote control, or even send specific actions (like volume up/down or a specific key stroke). 

[``URInterop.java``](https://github.com/unifiedremote/Intent-Tester/blob/master/app/src/main/java/com/unified/intenttester/URInterop.java) is the main implementation.

## Launching the Action Builder
A 3rd party app can easily integrate the Action Builder by simply starting the activity with the correct action string. The following code also verifies that Unified Remote is indeed installed on the user's device.

````java
public static final String ACTION_URI_SEND = "com.unifiedremote.ACTION_URI_SEND";
public static final String ACTION_URI_CONFIGURE = "com.unifiedremote.ACTION_URI_CONFIGURE";
public static final String EXTRA_URI = "com.unifiedremote.EXTRA_URI";
    
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
````

## Receiving the Action URI
Once the user has finished building an action using the Action Builder, a URI is returned as an activity result.

````java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE) {
        if (resultCode == RESULT_OK) {
            String uri = data.getStringExtra(EXTRA_URI);
        }
    }
}
````

## Sending an Action URI
To send an action URI, simply create a broadcast with the correct action string.

````java
context.sendBroadcast(new Intent(ACTION_URI_SEND).putExtra(EXTRA_URI, uri));
````

## Creating URIs in the app
![Image of Yaktocat](https://raw.githubusercontent.com/unifiedremote/Intent-Tester/master/create_uri.png)

## Action URI Syntax
The URI action syntax is documented [here](https://github.com/unifiedremote/Docs/blob/master/concepts/uri.md).
