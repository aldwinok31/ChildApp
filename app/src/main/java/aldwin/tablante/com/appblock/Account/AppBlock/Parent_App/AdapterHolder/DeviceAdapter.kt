package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder

import aldwin.tablante.com.appblock.Account.AppBlock.Model.myDevice
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DeviceAdapter(list: ArrayList<myDevice>, context: Context) : RecyclerView.Adapter<DeviceAdapter.DeviceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DeviceHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofparents, parent, false)


        return DeviceHolder(view)
    }

    var data: ArrayList<myDevice> = list
    override fun onBindViewHolder(holder: DeviceHolder?, position: Int) {
        holder!!.devname.text = "MODEL: " + data[position].MODEL
        holder.devdescription.text = "SERIAL: " + data[position].ID


    }

    override fun getItemCount(): Int {
        return data.size
    }

    class DeviceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var devname: TextView
        var devdescription: TextView

        init {

            devname = itemView.findViewById(R.id.title)
            devdescription = itemView.findViewById(R.id.description)
        }


    }


    fun deleteItem( position: Int){
         data.removeAt(position)
        notifyItemRemoved(position)

    }

}