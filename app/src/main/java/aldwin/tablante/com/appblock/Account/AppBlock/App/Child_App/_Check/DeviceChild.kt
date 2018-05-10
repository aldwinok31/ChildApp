package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App._Check

import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.Account.Model._idGenerator
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*

class DeviceChild {



    fun hasExist(user:ArrayList<String>,deviceId : String):Boolean{
var bool = false
var count =0
        var count2 = 0
        var data = FirebaseDatabase.getInstance()
        var ref = data.getReference("Accounts")
        var users = user.size


        ref.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot?) {
                if(p0!!.exists()){

                   for (h in p0.children) {




                   }

                }
            }

            override fun onCancelled(p0: DatabaseError?) {
Log.d("error","error")            }

        })





        return bool



    }
}