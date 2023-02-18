package com.ayushwalker.broadcastreceivers

//https://www.geeksforgeeks.org/broadcast-receiver-in-android-with-example/

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var receiver : AirplaneModeChangedReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = AirplaneModeChangedReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver,it) // this will do a memory leak
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}

/*
NOTES/STEPS:
The intent filter specifies the types of intents that an activity, service, or broadcast receiver can respond.
Intent filters are declared in the Android manifest file.
Intent filter must contain <action>

Broadcast Receivers are used to respond to these system-wide events. Broadcast Receivers allow us to register for the system and application events,
and when that event happens, then the register receivers get notified. There are mainly two types of Broadcast Receivers:

Static Broadcast Receivers: These types of Receivers are declared in the manifest file and works even if the app is closed.
Dynamic Broadcast Receivers: These types of receivers work only if the app is active or minimized.


 */