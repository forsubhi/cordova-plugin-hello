package com.example.plugin;


import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
public class Hello extends CordovaPlugin  {

    public  static  int success =10;
    private CallbackContext callbackContext;

    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hi, " + name;
            final Activity activity = cordova.getActivity();
            PackageManager pm = activity.getPackageManager();
            Intent launchIntent = pm.getLaunchIntentForPackage("com.kuzufab");
            activity.startActivityForResult(launchIntent,success);
            this.callbackContext=callbackContext;
            //   callbackContext.success(message);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        activity.runOnUiThread(new Runnable() {
                                                   @Override
                                                   public void run() {
                                                       Toast.makeText(activity, "working", Toast.LENGTH_SHORT).show();
                                                   }
                                               }
                        );
                    }
                }
            });
            thread.start();

            return true;

        } else {

            return false;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackContext.success("Hi Plugin");
    }
}
