package aldwin.tablante.com.appblock.Model

import android.content.Context
import android.os.AsyncTask

class GetParentDevices {
    inner class fetch : AsyncTask<Context, Context, ArrayList<User>>() {
        var parentid = android.os.Build.SERIAL
        var mContext: Context? = null
        private var parentidlist: ArrayList<User> = ArrayList()
        private var text = ""
        override fun doInBackground(vararg p0: Context?): ArrayList<User> {
            var listofParents: ArrayList<User> = ArrayList()

            try {
                parentid = android.os.Build.SERIAL
                var count = 0
                parentidlist = fetchData().fetchParent(parentid)
                Thread.sleep(3000)
                publishProgress()
                mContext = p0[0]
                if (parentidlist.isNotEmpty()) {

                    while (parentidlist.size > count) {
                        text = parentidlist[count].accID
                        publishProgress(p0[0])
                        count++
                    }
                } else {
                    publishProgress(p0[0])
                    text = ""
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }

            return listofParents
        }

        override fun onProgressUpdate(vararg values: Context?) {
            GetCurrentLocations().requestLocationUpdates(mContext!!, text)
            super.onProgressUpdate(*values)
        }


    }
}