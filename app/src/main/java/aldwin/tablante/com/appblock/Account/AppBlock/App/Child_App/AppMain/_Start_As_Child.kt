package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.AppMain

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.Adapter.AdapterHolder
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase._setData
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device.DeviceProvider
import aldwin.tablante.com.appblock.Account.AppBlock.Model._accountFetcher
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder.DeviceAdapter
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.appblock_intro.*
import kotlinx.android.synthetic.main.child_app.*

/**
 * Created by Bobby on 06/05/2018.
 */
class _Start_As_Child : AppCompatActivity(){

    var adapter :AdapterHolder?=null
private var ccod : EditText? = null
    private var gochild: Button? = null
    private var id: String? = null
    private var parentList : ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.child_app)
        this.ccod = findViewById(R.id.goCode)
        this.gochild = findViewById(R.id.goChild)
        parentList= _accountFetcher().fetchParent()
        this.gochild!!.setOnClickListener {
            var count = 0
            if (parentList!!.isNotEmpty()) {
                while (parentList!!.size > count) {


                    if (parentList[count].codd == this.ccod!!.text.toString()) {
                        this.id = parentList[count].accID



                    }
                    count++
                }



                var ids = this.id

                 parentList  = _accountFetcher().fetchParenthWithCode(this.ccod!!.text.toString())
                var parent = DeviceProvider().fetchInfo()

                Toast.makeText(this@_Start_As_Child, parentList.toString(),
                        Toast.LENGTH_LONG).show()

                _setData()._setDataFirebase(parent, ids!!)

               adapter = AdapterHolder(parentList,applicationContext)
                var layout_manager = LinearLayoutManager(applicationContext)
                rview.layoutManager = layout_manager
                rview.setHasFixedSize(true)
                rview.adapter = adapter

            }
            else{
                Toast.makeText(this@_Start_As_Child, "Retry Cannot connect to Internet....",
                        Toast.LENGTH_LONG).show()

            }





        }
    }
}