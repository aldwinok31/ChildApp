package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase.myDevice
import aldwin.tablante.com.appblock.Account.Model._idGenerator
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class _setData {



    fun _setDataFirebase(a: myDevice, id:String){
        var database : FirebaseDatabase
        var dataref : DatabaseReference

        database = FirebaseDatabase.getInstance()
        dataref = database.getReference("Accounts").child(id).child("Devices")
        val acc = a
        dataref.child(_idGenerator().getNewId()).setValue(acc)
    }

}