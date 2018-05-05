package aldwin.tablante.com.appblock.Account.Introduction.Main.Activity

import aldwin.tablante.com.appblock.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private  var register : Button?= null
    private  var login: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

        register = findViewById(R.id.register)
        login = findViewById(R.id.button)


        this.register!!.setOnClickListener {

            val intent = Intent(this, aldwin.tablante.com.appblock.Account.Module.register::class.java)
            startActivity(intent)
        }

        this.login!!.setOnClickListener {

            val intent = Intent(this, aldwin.tablante.com.appblock.Account.Module.login::class.java)
            startActivity(intent)
        }
    }
}