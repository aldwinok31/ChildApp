package aldwin.tablante.com.appblock.Account.Model

import android.util.Patterns
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

/**
 * Created by Bobby on 03/05/2018.
 */
class registerCheck {

    private lateinit var database : FirebaseDatabase
    private lateinit var dataref : DatabaseReference


    // Creating a New Account
    fun registerNewAccount(a:User){


        this.database = FirebaseDatabase.getInstance()
        this.dataref = this.database.getReference("Accounts")
        val accID = this.dataref.push().key
        val acc = User(a.accID,a.username.toLowerCase(),
                a.password.toLowerCase(),
                a.email.toLowerCase() )
        dataref.child(accID).setValue(acc)



    }

//  Checking of Accounts if Already exist
    fun checkacc(acc: User,acclist:ArrayList<User>) : Boolean{
        var count1=0
        var bool=true
if (!acclist.isEmpty()) {
    while (acclist.size > count1) {
        if (acclist.get(count1).username == acc.username
                || acclist.get(count1).password == acc.password
                || acclist.get(count1).email == acc.email) {


            bool = false
        }

        count1++
    }


}



return bool

    }


    fun checkEmail(email:String):Boolean{
        var bool = false

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            bool = true

        }
        return bool
    }



}