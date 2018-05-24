package aldwin.tablante.com.appblock.Model

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import com.google.firebase.database.FirebaseDatabase

class GetRunningApps {
    lateinit var  activityManager: ActivityManager
   lateinit var RAP : List<ActivityManager.RunningAppProcessInfo>


    fun sendData(context:Context,id:String,serial:String): ArrayList<String> {
        var applist: ArrayList<String> = ArrayList()

        activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        RAP = activityManager.runningAppProcesses
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Accounts")
        val userRef = ref.child(id).child("Devices").child(serial).child("RunningApps")
        for(processInfo in RAP){
            applist.add(processInfo.processName)
        }




        userRef.setValue(applist)


        return applist
    }
}