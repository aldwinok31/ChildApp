package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.FragmentHolder

import aldwin.tablante.com.appblock.Account.AppBlock.Model.myDevice
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder.DeviceAdapter
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.Account.Fetcher.a_Fetch
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.R
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.appblock_intro.*

class Fragment_First : android.support.v4.app.Fragment() {
    var devicelist: ArrayList<myDevice> = ArrayList()
    var dev : ArrayList<myDevice> = ArrayList()
    var device: ArrayList<myDevice> = ArrayList()
    var adapter: DeviceAdapter? = null
    var prog:ProgressBar? = null
    var id: String = ""
    var text= ""
    var user: deviceInfo? = null
    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context,"Shifting",Toast.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.appblock_intro, container, false)




    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id = this.arguments.getString("value")
if(!id.equals("")) {
    var fetch = fetching()
    fetch.execute()
}
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    inner class fetching : AsyncTask<Void, Void, Void>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(context,"Fetching Devices",Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg p0: Void?): Void? {

    devicelist = a_Fetch().getAccountDevices(id)
    Thread.sleep(1000)



    if (devicelist.isNotEmpty()) {
         var count =0
        while(devicelist.size > count){

           var devobject = devicelist[count]

            dev.add(devobject)
            adapter = DeviceAdapter(dev, activity.applicationContext)
            publishProgress()
count++
        }

        text = " Finished "

    } else {

        text = " No Existing Device Connected"
    }

            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)

            var layout_manager = LinearLayoutManager(activity.applicationContext)
            recycle.layoutManager = layout_manager
            recycle.setHasFixedSize(true)
            recycle.adapter = adapter
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)

            Toast.makeText(context,text + " " + devicelist[1].ID.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}