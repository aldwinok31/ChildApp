package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase.myDevice

class DeviceProvider {


    fun fetchInfo(): myDevice {
     var name = "Model:" + android.os.Build.MANUFACTURER + " " + android.os.Build.PRODUCT + " " + android.os.Build.BOARD +  " " +
             android.os.Build.MODEL
      var desc =   android.os.Build.DISPLAY

        var device  = myDevice(name, desc)




        return device!!
    }
}