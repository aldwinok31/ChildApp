package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.AppMain

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase._setData
import aldwin.tablante.com.appblock.Account.AppBlock.Model.myDevice
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device.DeviceProvider
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Algorithm.DeviceChild
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Algorithm.checkId
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.TrackerService
import aldwin.tablante.com.appblock.Account.Fetcher._accountFetcher
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.R
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.child_app.*

import kotlin.collections.ArrayList


/**
 * Created by Bobby on 06/05/2018.
 */
class Child_app : AppCompatActivity() {
    private var bool = false
    private var text= ""
    private var valuetointent=""
    private var arrintent : ArrayList<String> = ArrayList()
    private var parent: myDevice? = null
    private var username: EditText? = null
    private var progress: ProgressBar? = null
    private var password: EditText? = null
    private var ccod: EditText? = null
    private var gochild: Button? = null
    private var boolist: ArrayList<checkId> = ArrayList()
    private var loader: Loader? = null
    private var startservice: Button? = null
    private var usersId: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.child_app)



        parent = DeviceProvider().fetchInfo("")
        initializer()

    }


    fun initializer() {
        this.progress = findViewById(R.id.progressBar2)
        this.progress!!.visibility = View.INVISIBLE
        this.ccod = findViewById(R.id.goCode)
        this.gochild = findViewById(R.id.goChild)
        this.username = findViewById(R.id.user)
        this.password = findViewById(R.id.pass)
        this.startservice = findViewById(R.id.service)

        this.startservice!!.setOnClickListener {

            val intent = Intent(this@Child_app, TrackerService::class.java)
            var data = ""
            var data2 = ""
            data = valuetointent


           intent.putStringArrayListExtra("userids",arrintent)
           intent.putExtra("value",data)
            startService(intent)

            finish()


        }

        this.gochild!!.setOnClickListener {

            if (this.username!!.text.toString().equals("") &&
                    this.ccod!!.text.toString().equals("") &&
                    this.password!!.text.toString().equals("")) {


                this.username!!.error = "Missing Requirement!"
                this.ccod!!.error = "Missing Requirement!"
                this.password!!.error = "Missing Requirement!"
            } else {
                var lder = Loader()

                if (loader != null) {
                    loader = null
                }

                lder.execute()
                loader = lder
            }
        }


    }


    inner class Loader : AsyncTask<Void, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progress!!.visibility = View.VISIBLE
            Toast.makeText(this@Child_app, "Checking Data",
                    Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg p0: Void?): Void? {

            parent = DeviceProvider().fetchInfo("")
            Thread.sleep(2000)
            usersId = _accountFetcher().fetchParentWithUser(ccod!!.text.toString(), username!!.text.toString(), password!!.text.toString())
            Thread.sleep(3000)



            if (usersId.isNotEmpty()) {
                boolist = DeviceChild().hasExist(usersId[0].accID, parent!!.ID)
                valuetointent = usersId[0].accID
                arrintent.add(valuetointent)
                Thread.sleep(2000)
                text = "SuccessFully Saved Into :"
                publishProgress()
                if (!boolist[0]!!.bool && !boolist[0]!!.id.equals("") && boolist.isNotEmpty()) {
                    _setData()._setDataFirebase(parent!!, boolist[0]!!.id)

                    bool = true

                }
                else{

                    text = "Success: "
                }


            }
            else{

                text = "Failed to Save :"
            }


            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)

            Toast.makeText(this@Child_app,text , Toast.LENGTH_SHORT).show()

        }


        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)

            if(bool){


                startservice!!.isEnabled = true
            }
            Toast.makeText(this@Child_app, "Finish",
                    Toast.LENGTH_SHORT).show()
            progress!!.visibility = View.INVISIBLE
        }

    }
}