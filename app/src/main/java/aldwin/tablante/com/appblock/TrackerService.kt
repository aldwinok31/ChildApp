package aldwin.tablante.com.appblock

import aldwin.tablante.com.appblock.Model.*
import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.IBinder
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.*

class TrackerService : Service() {
    private val TAG = TrackerService::class.java.simpleName
    private var parentid = ""
    private var text = ""
    private var parentidlist: ArrayList<User> = ArrayList()
    override fun onBind(intent: Intent): IBinder? {

        return null
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

       // var fetch = fetch()
        //fetch.execute()
        var fetching = GetParentDevices().fetch()
        fetching.execute(applicationContext)

        return super.onStartCommand(intent, flags, startId)
    }

    inner class fetch : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                parentid = android.os.Build.SERIAL
                var count = 0
                parentidlist = fetchData().fetchParent(parentid)
                Thread.sleep(3000)
                publishProgress()

                if (parentidlist.isNotEmpty()) {

                    while (parentidlist.size > count) {
                        text = parentidlist[count].accID
                        publishProgress()
                        count++
                    }
                } else {
                    publishProgress()
                    text = ""
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }

            //buildNotification()
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {

            GetCurrentLocations().requestLocationUpdates(applicationContext,text)
            super.onProgressUpdate(*values)
        }

    }
    override fun onTaskRemoved(rootIntent: Intent?) {
        var intent = Intent("com.android.ServiceStopped")
        sendBroadcast(intent)

        super.onTaskRemoved(rootIntent)
    }


}


