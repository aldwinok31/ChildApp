package aldwin.tablante.com.appblock.Account.Module

import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AppMain._Parent_App
import aldwin.tablante.com.appblock.Account.Fetcher.a_Fetch
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.Account.Model.loginCheck
import aldwin.tablante.com.appblock.R
import aldwin.tablante.com.appblock.parent_layout
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/**
 * Created by Bobby on 03/05/2018.
 */
class login : AppCompatActivity(){
   private var id : String = ""
    private var name = ""
    private var lastname = ""
    private var submit : Button? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var loginacc : User? =  null
    private var acclist: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_acc)



        username = findViewById(R.id.editText)
        password = findViewById(R.id.editText2)
        submit = findViewById(R.id.login)
         this.acclist= a_Fetch().getAccounts()


      submit!!.setOnClickListener {
          if(loginCheck().isFilled(this.username!!.text.toString(),this.password!!.text.toString())) {
              if (isExist()) {
                  val intent = Intent(this, parent_layout::class.java)
                  Toast.makeText(this@login, "Log-in Success",
                          Toast.LENGTH_LONG).show()
                  intent.putExtra("id",this.id)
                  intent.putExtra("name",name + lastname)
                  startActivity(intent)

              }

              else{

                  Toast.makeText(this@login, "Account Doesnt Exist",
                          Toast.LENGTH_LONG).show()

              }
          }
          else{

              this.username!!.error = " Fill up the Required Data"
              this.password!!.error = " Fill up the Required Data"
          }

        }


    }

    fun isExist():Boolean{
        var bool = false
        var count =0
        while(this.acclist.size > count){

            if(this.username!!.text!!.toString() == this.acclist[count].username &&
                    this.password!!.text!!.toString() == this.acclist[count].password
            ){

               this.id = this.acclist[count].accID
               this.name = this.acclist[count].Firstname
                this.lastname = this.acclist[count].Lastname
                this.loginacc = this.acclist[count]
                bool = true
            }



            count++
        }

        return bool
    }




}