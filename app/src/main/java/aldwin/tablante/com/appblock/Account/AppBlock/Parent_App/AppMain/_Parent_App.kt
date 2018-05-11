package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AppMain


import aldwin.tablante.com.appblock.Account.Fetcher._accountFetcher
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.FragmentHolder.Fragment_First
import aldwin.tablante.com.appblock.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.ToggleButton


/**
 * Created by Bobby on 04/05/2018.
 */
class _Parent_App: AppCompatActivity() {

    var isFragmentOneLoaded = true
    var id =""
    val bundle = Bundle()
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_app1)


        val iin = intent
        var b : Bundle = iin.extras


        bundle.putString("value", b.getString("id"))




        initialize()

showFragmentParent()

    }




fun initialize(){


  //  var _a = _accountFetcher().fetchAccount(b.getString("id"))




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