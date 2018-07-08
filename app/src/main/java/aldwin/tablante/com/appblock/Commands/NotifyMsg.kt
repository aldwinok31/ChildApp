package aldwin.tablante.com.appblock.Commands

import android.app.AlertDialog
import android.app.Notification
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.DialogInterface

import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class NotifyMsg {


    fun alertMsg(context: Context, id: String, msg: String) {

        var db = FirebaseFirestore.getInstance()


        var alertDialog = AlertDialog.Builder(context.applicationContext)
                .setTitle("Message From Parent")
                .setMessage(msg)
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                .setNegativeButton("Cancel", null)
                .create()
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
        alertDialog.show()

        db.collection("Devices").document(id).update("Messages", "")
    }


    fun alertPairing(context: Context, id: String, name: String, RequestId: String) {

        var alertDialog = AlertDialog.Builder(context.applicationContext)
                .setTitle("Pairing Message")
                .setMessage(name + " Wants to pair with your Device.")
                .setPositiveButton("Accept", DialogInterface.OnClickListener { dialogInterface, i ->

                    var db = FirebaseFirestore.getInstance()
                    db.collection("Requests")
                            .document(RequestId + "+" + id)
                            .delete()
                    addDevice(RequestId, id,name)


                })
                .setNegativeButton("Decline") { dialogInterface, i ->
                    var db = FirebaseFirestore.getInstance()
                    db.collection("Requests")
                            .document(RequestId + "+" + id)
                            .delete()

                }

                .create()
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
        alertDialog.show()

    }


    fun addDevice(accID: String, serial: String,name:String) {
       val blue = BluetoothAdapter.getDefaultAdapter()
        val bluetoothName = blue.name
        var data = FirebaseDatabase.getInstance()
        var ref = data.getReference("Accounts").child(accID).child("Devices").child(serial)
        var devref = data.getReference("Devices").child(serial).child("ParentList").child(accID).child("Connection")

        var mmap: HashMap<String, Any?> = HashMap()
        mmap.put("distance",0.0.toDouble())
        mmap.put("id",serial)
        mmap.put("myId",accID)
        mmap.put("myName",name)
        mmap.put("name",bluetoothName)
        ref.setValue(mmap)


        devref.setValue("Paired")

    }
}