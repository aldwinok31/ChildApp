package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App._Check

import aldwin.tablante.com.appblock.Account.AppBlock.Model.myDevice
import android.util.Log
import com.google.firebase.database.*

class DeviceChild {


    fun hasExist(userid:String, deviceId: String): ArrayList<checkId> {

        var count = 0
        var count2 = 0
        var devlist:ArrayList<checkId> = ArrayList()
        var data = FirebaseDatabase.getInstance()
        var ref = data.getReference("Accounts")
        ref = data.getReference("Accounts")

        if (!userid.equals("")) {

                ref.addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(p0: DataSnapshot?) {


                        if (p0!!.child(userid).child("Devices").child(deviceId).exists()) {
                            var user = p0!!.child(userid).child("Devices").child(deviceId).getValue(myDevice::class.java)


                          devlist.add(checkId(user!!.ID, true))

                        }

                        else {

                            devlist.add(checkId(userid, false))


                        }


                    }

                    override fun onCancelled(p0: DatabaseError?) {
                        Log.d("error", "error")
                    }

                })


                count++


        }

        return devlist


    }
}