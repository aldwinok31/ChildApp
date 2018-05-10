package aldwin.tablante.com.appblock.Account.Fetcher

import aldwin.tablante.com.appblock.Account.Model.User
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class _accountFetcher {
    var user: User?= null
    fun fetchAccount(id:String): User{

        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts").child(id)


dataref.addValueEventListener(object : ValueEventListener{
    override fun onDataChange(p0: DataSnapshot?) {
user = p0!!.getValue(User::class.java)
    }

    override fun onCancelled(p0: DatabaseError?) {
Log.d("","Disconnected")    }
})
        return user!!


    }

    fun fetchParent() : ArrayList<User>{
        var usert : User ? = null
        var sss : String = ""
        var arr : ArrayList<User> = ArrayList()
        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts")

        dataref.addValueEventListener(object:ValueEventListener{


            override fun onCancelled(p0: DatabaseError?) {
                Log.d("Not Accepted"," No net")
            }
            override fun onDataChange(p0: DataSnapshot?) {

                for (h in p0!!.children) {
                    usert = h.getValue(User::class.java)
                    arr.add(usert!!)





                }
            }



        } )




return arr



    }

    fun fetchParenthWithCode(code:String) : ArrayList<String>{
        var arr : ArrayList<User> = ArrayList()
        var sss : String = ""
        var arrString : ArrayList<String> = ArrayList()
        var database = FirebaseDatabase.getInstance()
        var dataref = database.getReference("Accounts")

        dataref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot?) {

                for (h in p0!!.children) {
                    user = h.getValue(User::class.java)

                        arr.add(user!!)
                    sss = user!!.accID


                }
            }

            override fun onCancelled(p0: DatabaseError?) {
Log.d("Not Accepted"," No net")            }

        } )

        return arrString

    }
}