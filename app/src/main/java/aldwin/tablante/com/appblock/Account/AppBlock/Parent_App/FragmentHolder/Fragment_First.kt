package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.FragmentHolder

import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder.DeviceAdapter
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.R
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.appblock_intro.*

class Fragment_First: android.support.v4.app.Fragment() {
    var devicelist : ArrayList<deviceInfo> = ArrayList()
    var device = deviceInfo("","","",false)
    var adapter :DeviceAdapter?=null
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.appblock_intro,container,false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        devicelist.add(deviceInfo("","","",false))
        adapter = DeviceAdapter(devicelist,activity.applicationContext)
        var layout_manager = LinearLayoutManager(activity.applicationContext)
        recycle.layoutManager = layout_manager
        recycle.setHasFixedSize(true)
        recycle.adapter = adapter


    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}