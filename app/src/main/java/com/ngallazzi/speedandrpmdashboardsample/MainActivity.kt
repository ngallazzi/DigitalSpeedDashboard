package com.ngallazzi.speedandrpmdashboardsample

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var speed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the Handler object (on the main thread by default)
        val handler = Handler()
        srDashboard.setMaxSpeed(200)
        // Define the code block to be executed
        val runnableCode = object : Runnable {
            override fun run() {
                // Do something here on the main thread
                speed++
                srDashboard.setSpeed(speed)
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 100)
                if (speed == 200) {
                    speed = 0
                }
            }
        }
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode)
    }

    companion object {
        val TAG = MainActivity::class.simpleName
    }
}
