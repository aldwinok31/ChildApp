package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder

import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DeviceAdapter(list : ArrayList<deviceInfo>,context: Context): RecyclerView.Adapter<DeviceAdapter.DeviceHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):DeviceHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofparents, parent, false)


        return DeviceHolder(view)    }

    override fun onBindViewHolder(holder: DeviceHolder?, position: Int) {
    holder!!.devname.text = ""
        holder.devdescription.text = ""


    }

    override fun getItemCount(): Int {
return 1  }

    class DeviceHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var devname : TextView
        var devdescription : TextView

        init{

            devname =itemView.findViewById(R.id.title)
            devdescription = itemView.findViewById(R.id.description)
        }




    }
}