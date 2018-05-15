package aldwin.tablante.com.appblock.Account.Fetcher

import aldwin.tablante.com.appblock.Account.Model.User
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class _accountFetcher {


    fun fetchAccount(id: String): User {
        var user: User? = null
        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts").child(id)


        dataref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {
                user = p0!!.getValue(User::class.java)
            }

            override fun onCancelled(p0: DatabaseError?) {
                Log.d("", "Disconnected")
            }
        })
        return user!!


    }

    fun fetchParent(): ArrayList<User> {
        var usert: User? = null
        var arr: ArrayList<User> = ArrayList()
        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts")

        dataref.addValueEventListener(object : ValueEventListener {


            override fun onCancelled(p0: DatabaseError?) {
                Log.d("Not Accepted", " No net")
            }

            override fun onDataChange(p0: DataSnapshot?) {

                for (h in p0!!.children) {

                    usert = h.getValue(User::class.java)
                    arr.add(usert!!)

                }
            }
        })




        return arr


    }


       // Fetch Parent using Username and Password
    fun fetchParentWithUser(code: String, username:String, password:String):ArrayList<User> {
        var parent: User? = null
        var id = ""
        var arrString: ArrayList<User> = ArrayList()
        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts")

        dataref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {

                for (h in p0!!.children) {
                   var  user = h.getValue(User::class.java)
if(user!!.username == username && user!!.password == password){
    arrString.add(user!!)
}




                }

            }

            override fun onCancelled(p0: DatabaseError?) {
                Log.d("Not Accepted", " No net")
            }

        })


        return arrString

    }
}