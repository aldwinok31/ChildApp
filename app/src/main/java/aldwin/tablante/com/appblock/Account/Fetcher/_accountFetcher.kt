package aldwin.tablante.com.appblock.Account.Fetcher

import aldwin.tablante.com.appblock.Account.Model.User
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class _accountFetcher {


    // Fetch Parent using Username and Password
    fun fetchParentWithUser(code: String, username: String, password: String): ArrayList<User> {
        var parent: User? = null
        var id = ""
        var arrString: ArrayList<User> = ArrayList()
        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts")

        dataref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {

                for (h in p0!!.children) {
                    var user = h.getValue(User::class.java)
                    if (user!!.username == username && user!!.password == password) {
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