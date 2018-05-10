package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Device

import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.AppMain._Start_As_Child
import aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App.Component.FireBase.myDevice
import android.content.Context
import android.hardware.usb.UsbDevice.getDeviceId
import android.content.Context.TELEPHONY_SERVICE
import android.telephony.TelephonyManager
import java.util.*


class DeviceProvider {


    fun fetchInfo(mac:String): myDevice {
        var name = android.os.Build.SERIAL

        var desc = android.os.Build.DISPLAY




        var device = myDevice(name, desc)
        return device!!
    }
}