package aldwin.tablante.com.appblock.Account.Model

import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

/**
 * Created by Bobby on 03/05/2018.
 */
class registerCheck {

    private lateinit var database: FirebaseDatabase
    private lateinit var dataref: DatabaseReference


    // Creating a New Account
    fun registerNewAccount(a: User) {
        this.database = FirebaseDatabase.getInstance()
        this.dataref = this.database.getReference("Accounts")
        val acc = User(a.accID, a.username.toLowerCase(),
                a.password.toLowerCase(),
                a.email.toLowerCase(), a.codd.toLowerCase(), a.Firstname, a.Lastname)
        dataref.child(a.accID).setValue(acc)


    }

    //  Checking of Accounts if Already exist
    fun checkacc(acc: User, acclist: ArrayList<User>): Boolean {
        var count1 = 0
        var bool = true
        if (!acclist.isEmpty()) {
            while (acclist.size > count1) {
                if (acclist[count1].username == acc.username
                        || acclist[count1].password == acc.password
                        || acclist[count1].email == acc.email) {


                    bool = false
                    break
                }

                count1++
            }


        }



        return bool

    }

}