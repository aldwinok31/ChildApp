package aldwin.tablante.com.appblock

import aldwin.tablante.com.appblock.Commands.BootDevice
import aldwin.tablante.com.appblock.Commands.NotifyMsg
import aldwin.tablante.com.appblock.Commands.TriggerAlarm
import aldwin.tablante.com.appblock.Model.*
import android.Manifest
import android.app.AlertDialog
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.*
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

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
        var device = android.os.Build.SERIAL
        var db = FirebaseFirestore.getInstance()

        var mmap: HashMap<String, Any?> = HashMap()
        mmap.put("Serial", device)
        mmap.put("BootDevice", false)
        mmap.put("Screenshot", false)
        mmap.put("CaptureCam", false)
        mmap.put("TriggerAlarm", false)
        mmap.put("Messages", "")


        db.collection("Devices")
                .document(device)
                .set(mmap)


        db.collection("Devices")
                .whereEqualTo("Serial", device)
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                        for (doc in p0!!.documents) {
                            var devicet = doc.toObject(ConsoleCommand::class.java)
                            if (devicet!!.BootDevice) {

                                var id = doc.id
                                db.collection("Devices").document(id).update("BootDevice", false)
                                BootDevice().startBoot(applicationContext)
                            }

                            if (devicet.CaptureCam) {


                            }

                            if (devicet.ScreenShot) {


                            }

                            if (devicet.TriggerAlarm) {

                                TriggerAlarm().playAlarm(applicationContext)
                            }
                            if (!devicet.Messages.equals("")) {
                                var id = doc.id
                                Toast.makeText(applicationContext, devicet.Messages, Toast.LENGTH_SHORT).show()
                                var alertDialog = AlertDialog.Builder(applicationContext)
                                        .setTitle("Message From Parent")
                                        .setMessage(devicet.Messages)
                                        .setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->

                                   Toast.makeText(applicationContext,"CLICKED",Toast.LENGTH_SHORT).show()
                                        })
                                        .setNegativeButton("Cancel",null)

                                        .create();
                                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                                alertDialog.show();
                                db.collection("Devices").document(id).update("Messages", "")

                            }
                        }
                    }
                })

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        var intent = Intent("com.android.ServiceStopped")
        sendBroadcast(intent)

        super.onTaskRemoved(rootIntent)
    }

    protected var stopReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            // Stop the service when the notification is tapped
            unregisterReceiver(this)
            stopSelf()
        }
    }

}


