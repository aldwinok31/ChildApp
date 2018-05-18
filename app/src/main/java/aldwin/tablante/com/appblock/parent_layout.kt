package aldwin.tablante.com.appblock

import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.FragmentHolder.Fragment_First
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_parent_layout.*

class parent_layout : AppCompatActivity() {

    var isFragmentOneLoaded = true
    var id =""
    var fullname = ""
    val bundle = Bundle()
    val manager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                welcomView.setText(" Welcome "+fullname)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                showFragmentParent()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_layout)

        val iin = intent
        var b : Bundle = iin.extras

       fullname = b.getString("name")
        bundle.putString("value", b.getString("id"))


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    fun showFragmentParent(){

        val transaction = manager.beginTransaction()
        val fragment = Fragment_First()
        fragment.arguments = bundle
        transaction.replace(R.id.frag,fragment)
        transaction.addToBackStack(null)
        transaction.commit()


    }
}
