package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AppMain


import aldwin.tablante.com.appblock.Account.Fetcher._accountFetcher
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.FragmentHolder.Fragment_First
import aldwin.tablante.com.appblock.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ToggleButton


/**
 * Created by Bobby on 04/05/2018.
 */
class _Parent_App: AppCompatActivity() {
var myDevice : ToggleButton?= null
    var myOption : ToggleButton? = null
    var myLocation : ToggleButton? = null
    var myIntruct : ToggleButton? = null
    var isFragmentOneLoaded = true
    var id =""
    val bundle = Bundle()
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



    bundle.putString("id", b.getString("id"))

    var _a = _accountFetcher().fetchAccount(b.getString("id"))


    myDevice!!.setOnClickListener {
        showFragmentParent()

        myOption!!.isSelected = false
        myLocation!!.isSelected = false
        myOption!!.isActivated =false
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
        fragment.arguments = bundle
        transaction.replace(R.id.holderfrag,fragment)
        transaction.addToBackStack(null)
        transaction.commit()


    }

    fun showFragmentLocation(){}
}