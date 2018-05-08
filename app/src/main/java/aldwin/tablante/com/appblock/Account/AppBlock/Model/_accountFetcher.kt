package aldwin.tablante.com.appblock.Account.AppBlock.Model

import aldwin.tablante.com.appblock.Account.Model.User
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class _accountFetcher {

    fun fetchAccount(id:String): Boolean {

        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts").child(id)

dataref.addValueEventListener(object : ValueEventListener{
    override fun onDataChange(p0: DataSnapshot?) {
var user = p0!!.child(id).childrenCount

        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())
        Log.d("Aldwin",user.toString())



    }

    override fun onCancelled(p0: DatabaseError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
})



        return true
    }
}