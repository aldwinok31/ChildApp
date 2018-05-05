package aldwin.tablante.com.appblock.Account.Fetcher

import aldwin.tablante.com.appblock.Account.Model.User
import android.os.AsyncTask
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by Bobby on 03/05/2018.
 */
class a_Fetch (){




    fun getAccounts():ArrayList<User>{

        var acclist : ArrayList<User> = ArrayList()

        var data = FirebaseDatabase.getInstance()
        var ref = data.getReference("Accounts")

        ref.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                       for (h in p0.children){
                       var value =h.getValue(User::class.java)
                            acclist.add(value!!)

                        }


                    }



        }

        )

        return acclist
    }




}