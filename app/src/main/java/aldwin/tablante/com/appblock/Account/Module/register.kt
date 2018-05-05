package aldwin.tablante.com.appblock.Account.Module

import aldwin.tablante.com.appblock.Account.Fetcher.a_Fetch
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.Account.Model._idGenerator
import aldwin.tablante.com.appblock.Account.Model.registerCheck
import aldwin.tablante.com.appblock.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

/**
 * Created by Bobby on 03/05/2018.
 */
class register : AppCompatActivity (){

    private var user : EditText?=null
    private var pass : EditText?=null
    private var conpass: EditText?=null
    private var reg : Button?=null
    private var email: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_acc)


        this.reg = findViewById(R.id.register)
        var accounts =a_Fetch().getAccounts()
        this.reg!!.setOnClickListener {



                this.user = findViewById(R.id.username)
                this.pass = findViewById(R.id.password)
                this.conpass = findViewById(R.id.confirmpassword)
                this.email = findViewById(R.id.email)

               if(isvalidAcc() && isSame() ){
                   Toast.makeText(this@register, "You Can Log in now",
                           Toast.LENGTH_LONG).show()
                   if(isvalidPass()) {
                       var newacc = User(_idGenerator().getNewId(), this.user!!.text.toString(),
                               this.pass!!.text.toString(),
                               this.email!!.text.toString())


                       if( registerCheck().checkacc(newacc,accounts)){
                          registerCheck().registerNewAccount(newacc)

                           Toast.makeText(this@register, "You Can Log in now",
                                   Toast.LENGTH_LONG).show()
                          val intent = Intent(this, login::class.java)
                          startActivity(intent)

                    }
                       else
                       {
                           Toast.makeText(this@register, "Account Already Existed",
                                   Toast.LENGTH_LONG).show()
                       }

                   }

                  }





        }
    }

































    fun isvalidAcc():Boolean{

        var bool = false

        if(this.user!!.text .toString()!="" &&
                this.pass!!.text.toString() !="" &&
                this.conpass!!.text.toString() != "" &&
                this.email!!.text.toString() != ""


        ){bool = true}
        else{

            this.user!!.error = "Incomplete Data.."
            this.pass!!.error = "Incomplete Data.."
            this.conpass!!.error = "Incomplete Data.."
            this.email!!.error = "Incomplete Data.."
        }

        if(this.user!!.text .toString().length >= 6 &&
                this.pass!!.text.toString().length >= 6 &&
                this.conpass!!.text.toString().length >= 6 &&
                this.email!!.text.toString().length >= 10){bool = true}
        else{

            this.user!!.error = "Incomplete Data.."
            this.pass!!.error = "Incomplete Data.."
            this.conpass!!.error = "Incomplete Data.."
            this.email!!.error = "Incomplete Data.."
        }
        return bool
    }

fun isvalidPass():Boolean{
    var bool = false
    if(this.pass!!.text.toString().toLowerCase()
            == this.conpass!!.text.toString().toLowerCase()){

        bool= true
    }
    else{

        this.conpass!!.error = "Mismatched Password"
    }


    return bool
}


    fun isSame():Boolean{
        var bool = true

        if(this.user!!.text.toString() == this.pass!!.text.toString()){

            this.user!!.error = "Password Cant be same with Username"
            this.pass!!.error = "Password Cant be same with Username"
            bool = false
        }
        return bool
    }

}