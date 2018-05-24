package aldwin.tablante.com.appblock.Model

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class fetchData() {


    fun fetchParent(id: String): ArrayList<User> {
        var ArrParent: ArrayList<User> = ArrayList()

        var data = FirebaseDatabase.getInstance()
        var ref = data.getReference("Accounts")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot?) {
                for(h in p0!!.children){
                    if(h.child("Devices").hasChild(id)) {
                        var user = h.getValue(User::class.java)
                        ArrParent.add(user!!)
                    }
                }



            }
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("Error", "101")
            }


        }
        )


        return ArrParent
    }
}