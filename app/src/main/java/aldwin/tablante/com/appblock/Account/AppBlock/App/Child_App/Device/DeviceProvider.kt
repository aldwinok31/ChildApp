package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device

import aldwin.tablante.com.appblock.Account.AppBlock.Model.myDevice


class DeviceProvider {


    fun fetchInfo(mac:String): myDevice {
        var name = android.os.Build.SERIAL
        var desc = android.os.Build.MODEL + " " + android.os.Build.BRAND
        var device = myDevice(desc, name)
        return device!!
    }
}