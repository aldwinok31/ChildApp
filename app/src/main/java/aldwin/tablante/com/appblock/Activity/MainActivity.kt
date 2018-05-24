package aldwin.tablante.com.appblock.Activity

import aldwin.tablante.com.appblock.R
import aldwin.tablante.com.appblock.TrackerService
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.pm.PackageManager
import aldwin.tablante.com.appblock.Activity.MainActivity
import android.content.ComponentName
import android.renderscript.RenderScript


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this@MainActivity, TrackerService::class.java)

        startService(intent)
      finish()

    }
}
