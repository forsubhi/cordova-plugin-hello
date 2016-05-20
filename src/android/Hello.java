package com.example.plugin;


import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
public class Hello extends Activity {

    public  static  int success =10;
    private CallbackContext callbackContext;

    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hi, " + name;
            Activity activity = cordova.getActivity();
            PackageManager pm = activity.getPackageManager();
            Intent launchIntent = pm.getLaunchIntentForPackage("com.kuzufab");
            activity.startActivityForResult(launchIntent,success);
            this.callbackContext=callbackContext;
            //   callbackContext.success(message);

            return true;

        } else {

            return false;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackContext.success("Hi Plugin");
    }
}
