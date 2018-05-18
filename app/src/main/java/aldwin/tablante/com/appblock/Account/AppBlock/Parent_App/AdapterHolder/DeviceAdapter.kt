package aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Algorithm.DeleteFromFireBase
import aldwin.tablante.com.appblock.Account.AppBlock.Model.myDevice
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.MapsActivity
import aldwin.tablante.com.appblock.R
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class DeviceAdapter(list: ArrayList<myDevice>, context: Context) : RecyclerView.Adapter<DeviceAdapter.DeviceHolder>() {
    val mContext = context
    var v: View? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DeviceHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofparents, parent, false)
        v = view

        return DeviceHolder(view)
    }

    var data: ArrayList<myDevice> = list
    override fun onBindViewHolder(holder: DeviceHolder?, position: Int) {
        holder!!.devname.text = "Model: " + data[position].MODEL
        holder.devdescription.text = "Serial: " + data[position].ID


        var id = data[position].ID
        var parentid = data[position].parentId

        holder.devclickable.setOnClickListener {
            val intent = Intent(mContext, MapsActivity::class.java)
            intent.putExtra("idpar", parentid)
            intent.putExtra("serialval",id)
            v!!.context.startActivity(intent)


        }

        holder.deletebut.setOnClickListener {
            deleteItem(position, id, parentid)
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    class DeviceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var devname: TextView
        var devdescription: TextView
        var devclickable: LinearLayout
        var deletebut: ImageView

        init {
            deletebut = itemView.findViewById(R.id.deletebut)
            devclickable = itemView.findViewById(R.id.clickable)
            devname = itemView.findViewById(R.id.title)
            devdescription = itemView.findViewById(R.id.description)
        }


    }


    fun deleteItem(position: Int, id: String, parentId: String) {
        data.removeAt(position)
        notifyItemRemoved(position)
        DeleteFromFireBase().DeleteThis(parentId, id)


    }

}