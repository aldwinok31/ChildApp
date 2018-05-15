package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class TrackerService : Service() {
    private val TAG = TrackerService::class.java.simpleName

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        buildNotification()
        requestLocationUpdates()
    }

    private fun buildNotification() {
        val stop = "stop"
        registerReceiver(stopReceiver, IntentFilter(stop))
        val broadcastIntent = PendingIntent.getBroadcast(
                this, 0, Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT)
        // Create the persistent notification

    }

    protected var stopReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "received stop broadcast")
            // Stop the service when the notification is tapped
            unregisterReceiver(this)
            stopSelf()
        }
    }



    private fun requestLocationUpdates() {
        val request = LocationRequest()
        request.interval = 10000
        request.fastestInterval = 5000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val blue: BluetoothAdapter
        blue = BluetoothAdapter.getDefaultAdapter()
        val bluetoothName = blue.name
        val client = LocationServices.getFusedLocationProviderClient(this)
        val model = android.os.Build.MODEL
        val serial = android.os.Build.SERIAL
        //val path = getString(R.string.firebase_path) + "/" + getString(R.string.firebase_child)
        var database: FirebaseDatabase
        var dataref: DatabaseReference
        database = FirebaseDatabase.getInstance()
        dataref = database.getReference("Accounts").child("-LCD262HxzMoMdK0aqdM").child("Devices")

        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    val location = locationResult!!.lastLocation
                    if (location != null) {

                        dataref.child(serial).child("Locations").setValue(location)
                        Toast.makeText(applicationContext,serial.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }, null)
        }
    }
}
