package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Algorithm

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device.DeviceProvider
import com.google.firebase.database.FirebaseDatabase

class DeleteFromFireBase() {
private val database = FirebaseDatabase.getInstance()
    private val dataref = database.getReference("Accounts")

    fun DeleteThis(userId:String,serial:String){


        dataref.child(userId).child("Devices").removeValue()
    }
}