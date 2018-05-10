package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.AppMain

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.Adapter.AdapterHolder
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase._setData
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device.DeviceProvider
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App._Check.DeviceChild
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App._Check.ToDevParent
import aldwin.tablante.com.appblock.Account.Fetcher._accountFetcher
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.R
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*
import android.provider.Settings.Secure
import android.hardware.usb.UsbDevice.getDeviceId
import android.net.wifi.WifiManager
import android.net.wifi.WifiInfo
import kotlin.collections.ArrayList


/**
 * Created by Bobby on 06/05/2018.
 */
class _Start_As_Child : AppCompatActivity() {

    var adapter: AdapterHolder? = null
    private var ccod: EditText? = null
    private var gochild: Button? = null
    private var id: String? = null
    private var usersId: ArrayList<String> = ArrayList()
    private var parentList: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.child_app)
        initializer()

    }























    fun initializer(){

        this.ccod = findViewById(R.id.goCode)
        this.gochild = findViewById(R.id.goChild)
      //  val manager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
       // val info = manager.connectionInfo
      //  val address = info.macAddress
        var parent = DeviceProvider().fetchInfo("")
        parentList = _accountFetcher().fetchParent()

        Toast.makeText(this@_Start_As_Child, parentList!!.toString(),
                Toast.LENGTH_LONG).show()

        var bool = false
        this.gochild!!.setOnClickListener {

            usersId = _accountFetcher().fetchParenthWithCode(this.gochild!!.text.toString())
            bool = DeviceChild().hasExist(usersId,parent.name)

            Toast.makeText(this@_Start_As_Child, usersId!!.toString(),
                    Toast.LENGTH_LONG).show()

            var count = 0
            if (parentList!!.isNotEmpty()) {
                while (parentList!!.size > count) {
                    if (parentList[count].codd == this.ccod!!.text.toString()) {
                        this.id = parentList[count].accID
                    }
                    count++
                }

                var ids = this.id
                _setData()._setDataFirebase(parent, ids!!)
            }
            else {
                Toast.makeText(this@_Start_As_Child, "Retry Cannot connect to Internet....",
                        Toast.LENGTH_LONG).show()

            }


        }


    }
}