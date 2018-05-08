package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App


import aldwin.tablante.com.appblock.Account.AppBlock.Model._accountFetcher
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.FragmentHolder.Fragment_First
import aldwin.tablante.com.appblock.R
import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ToggleButton
import android.support.v4.app.NotificationCompat.getExtras



/**
 * Created by Bobby on 04/05/2018.
 */
class _Parent_App: AppCompatActivity() {
var myDevice : ToggleButton?= null
    var myOption : ToggleButton? = null
    var myLocation : ToggleButton? = null
    var myIntruct : ToggleButton? = null
    var isFragmentOneLoaded = true
    var id : String? = null
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_app1)



        initialize()


    }




fun initialize(){
    myDevice = findViewById(R.id.toggleButton)
    myOption = findViewById(R.id.toggleButton2)
    myLocation = findViewById(R.id.toggleButton3)
    myIntruct = findViewById(R.id.toggleButton4)
    val iin = intent
    var b : Bundle = iin.extras
    this.id = b.getString("id")



    var _a = _accountFetcher().fetchAccount(this.id!!)


    myDevice!!.setOnClickListener {
        showFragmentParent()

        myOption!!.isSelected = false
        myLocation!!.isSelected = false
        myIntruct!!.isSelected = false
        myDevice!!.isSelected = true


    }

    myOption!!.setOnClickListener {


        myOption!!.isSelected = true
        myLocation!!.isSelected = false
        myIntruct!!.isSelected = false
        myDevice!!.isSelected = false
    }


    myLocation!!.setOnClickListener {

        myOption!!.isSelected = false
        myLocation!!.isSelected = true
        myIntruct!!.isSelected = false
        myDevice!!.isSelected = false
    }

    myIntruct!!.setOnClickListener{

        myOption!!.isSelected = false
        myLocation!!.isSelected = false
        myIntruct!!.isSelected = true
        myDevice!!.isSelected = false
    }

 }


    fun showFragmentParent(){
val transaction = manager.beginTransaction()
        val fragment = Fragment_First()
        transaction.replace(R.id.holderfrag,fragment)
        transaction.addToBackStack(null)
        transaction.commit()


    }

    fun showFragmentLocation(){}
}