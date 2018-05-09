package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.Adapter

import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.AdapterHolder.DeviceAdapter
import aldwin.tablante.com.appblock.Account.AppBlock.Parent_App.Component.deviceInfo
import aldwin.tablante.com.appblock.Account.Model.User
import aldwin.tablante.com.appblock.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AdapterHolder(list : ArrayList<User>, context: Context): RecyclerView.Adapter<AdapterHolder.UserHolder> () {

var parlist : ArrayList<User> =list
    override fun onBindViewHolder(holder: UserHolder?, position: Int) {
        var para = parlist[position]


        holder!!.devname.text = " User: " + para.Firstname + " " + para.Lastname
        holder.devdescription.text = " Model : " +  para.DeviceModel
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofparents, parent, false)
      return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return parlist.size
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var devname: TextView
        var devdescription: TextView

        init {

            devname = itemView.findViewById(R.id.title)
            devdescription = itemView.findViewById(R.id.description)
        }


    }
}