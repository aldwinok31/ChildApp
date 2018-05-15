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

import kotlin.collections.ArrayList


/**
 * Created by Bobby on 06/05/2018.
 */
class _Start_As_Child : AppCompatActivity() {
    private var bool = false
    private var parent: myDevice? = null
    private var username: EditText? = null
    private var progress: ProgressBar? = null
    private var password: EditText? = null
    private var ccod: EditText? = null
    private var gochild: Button? = null
    private var boolist: ArrayList<checkId> = ArrayList()
    private var loader: Loader? = null
    private var startservice:Button? = null
    private var usersId :ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.child_app)




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


            startService(Intent(this, TrackerService::class.java))
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
            Toast.makeText(this@_Start_As_Child, "Checking Data",
                    Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            parent = DeviceProvider().fetchInfo("")
            Thread.sleep(2000)
            usersId = _accountFetcher().fetchParentWithUser(ccod!!.text.toString(), username!!.text.toString(), password!!.text.toString())
            Thread.sleep(3000)
            publishProgress()


if(usersId.isNotEmpty()) {
    boolist = DeviceChild().hasExist(usersId[0].accID, parent!!.ID)
    Thread.sleep(2000)

    publishProgress()

    if (!boolist[0]!!.bool && !boolist[0]!!.id.equals("") && boolist.isNotEmpty()) {
        _setData()._setDataFirebase(parent!!, boolist[0]!!.id)

    }


}



            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
            Toast.makeText(this@_Start_As_Child, usersId[0].Firstname.toString() ,Toast.LENGTH_SHORT).show()

        }


        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Toast.makeText(this@_Start_As_Child, "Saved",
                    Toast.LENGTH_SHORT).show()
            progress!!.visibility = View.INVISIBLE
        }

    }
}