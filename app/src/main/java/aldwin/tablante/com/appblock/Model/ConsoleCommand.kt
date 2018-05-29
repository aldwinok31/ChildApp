package aldwin.tablante.com.appblock.Model

class ConsoleCommand(BootDevice:Boolean,ScreenShot:Boolean,CaptureCam:Boolean,TriggerAlarm:Boolean ,Messages:String) {

    var BootDevice = BootDevice
    var ScreenShot = ScreenShot
    var CaptureCam =CaptureCam
    var TriggerAlarm = TriggerAlarm
    var Messages = Messages


    constructor() : this(false,false,false,false,"")
}