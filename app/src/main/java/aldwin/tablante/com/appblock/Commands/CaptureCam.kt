package aldwin.tablante.com.appblock.Commands

import android.graphics.Bitmap
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.os.Environment
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.provider.MediaStore
import android.content.Intent
import android.hardware.Camera

import android.net.Uri
import android.hardware.Camera.CameraInfo
import android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT
import android.hardware.Camera.getCameraInfo
import android.hardware.Camera.getNumberOfCameras
import android.util.Log


class CaptureCam {
fun openFrontCamera():Camera {

    var cameraCount = 0
    var cam: Camera? = null
    val cameraInfo = Camera.CameraInfo()
    cameraCount = Camera.getNumberOfCameras()
    for (camIdx in 0 until cameraCount) {
        Camera.getCameraInfo(camIdx, cameraInfo)
        if (cameraInfo.facing === Camera.CameraInfo.CAMERA_FACING_FRONT) {
            try {
                cam = Camera.open(camIdx)
            } catch (e: RuntimeException) {
                Log.e("He", "Camera failed to open: " + e.toString())
            }

        }
    }

    return cam!!


}
}