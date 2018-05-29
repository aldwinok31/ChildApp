package aldwin.tablante.com.appblock.Model

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.*

class GetCurrentLocations {


    fun requestLocationUpdates(context: Context, id: String) {
        val request = LocationRequest()
        request.interval = 10000
        request.fastestInterval = 5000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val blue: BluetoothAdapter
        blue = BluetoothAdapter.getDefaultAdapter()
        val bluetoothName = blue.name
        val client = LocationServices.getFusedLocationProviderClient(context)
        val model = android.os.Build.MODEL
        val serial = android.os.Build.SERIAL
        var database: FirebaseDatabase
        var dataref: DatabaseReference
        database = FirebaseDatabase.getInstance()
        var device = childDevice(serial, bluetoothName)
        dataref = database.getReference("Devices").child(serial)
        dataref.setValue(device)
        val permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {

            request.setFastestInterval(2500)
                    .setInterval(5000)
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    val location = locationResult!!.lastLocation
                    if (location != null) {
                        if (id != "") {

                            var ref = database.getReference("Accounts").child(id).child("Devices").child(serial)

                            ref.addValueEventListener(object : ValueEventListener {

                                override fun onCancelled(p0: DatabaseError?) {
//232131
                                }

                                override fun onDataChange(p0: DataSnapshot?) {
                                    if (p0!!.hasChild("Loacations")) {
                                        var loca: HashMap<String, Any>? = HashMap()
                                        loca!!.put("Locations",location)
                                        p0.ref.child(id).child("Devices").child(serial).updateChildren(loca)
                                    }
                                    else{
                                        ref.child("Locations").setValue(location)

                                    }
                                }
                            })
                            dataref.child("Locations").setValue(location)
                           dataref.child("ParentList").child(id).setValue(id)
                           dataref.child("ParentList").child(id).child("Connection").setValue("Paired")
                            GetRunningApps().sendData(context, id, serial)


                        } else {
                            dataref.child("Locations").setValue(location)
                        }

                    } else {
                        dataref.child("Status").setValue("down")


                    }
                }
            }

                    , null)
        } else {
            Toast.makeText(context, " Not Found", Toast.LENGTH_LONG).show()

        }
        
    }
}