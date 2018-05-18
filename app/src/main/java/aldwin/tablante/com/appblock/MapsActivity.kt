package aldwin.tablante.com.appblock

import android.app.ActivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val TAG = MapsActivity::class.java.simpleName
    private val mMarkers = HashMap<String, Marker>()

    private lateinit var userList: MutableList<gpslocation>
    private var latitude: String = ""
    private var longitude: String = ""
    private var id=""
    private var serial = ""
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        id = intent.getStringExtra("idpar")
        serial = intent.getStringExtra("serialval")
        userList = mutableListOf()
        Toast.makeText(applicationContext,serial + id , Toast.LENGTH_LONG).show()
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Accounts")
                .child(id)
                .child("Devices")
                .child(serial)
                .child("Locations")

        var cal: ActivityManager

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("ERROR", "ERROR")
            }

            override fun onDataChange(p0: DataSnapshot?) {
                latitude = p0?.child("latitude")?.value.toString()
                longitude = p0?.child("longitude")?.value.toString()
                userList.add(gpslocation(latitude.toDouble(), longitude.toDouble()))

            }
        })


        //Toast.makeText(this@MapsActivity, userList[0].lat.toString()+userList[0].long.toString(),Toast.LENGTH_LONG).show()


    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!;
        mMap.setMaxZoomPreference(16F);
        loginToFirebase();
        //subscribeToUpdates()
    }

    private fun loginToFirebase() {
        val email = "arriesga@yahoo.com"
        val password = "123456"
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "firebase auth success")
                subscribeToUpdates()
            } else {
                Log.d(TAG, "firebase auth failed")
            }
        }
    }

    private fun subscribeToUpdates() {
        var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Accounts")
                .child(id)
                .child("Devices")
                .child(serial)
                .child("Locations")


        ref.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Toast.makeText(this@MapsActivity, "Cancelled", Toast.LENGTH_SHORT).show()
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                Toast.makeText(this@MapsActivity, "Child Moved", Toast.LENGTH_SHORT).show()
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                setMarker(p0)
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {

                setMarker(p0)
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                Toast.makeText(this@MapsActivity, "Child Removed", Toast.LENGTH_SHORT).show()
            }

        });
    }


    private fun setMarker(dataSnapshot: DataSnapshot?) {

        val key: String = dataSnapshot!!.key
        //val value: HashMap<*, *> = dataSnapshot.getValue() as HashMap<*, *>
        val location = LatLng(userList.get(0).lat, userList.get(0).long)
        if (!mMarkers.containsKey(key)) {
            mMarkers.put(key, mMap.addMarker(MarkerOptions().title(key).position(location)))
        } else {
            mMarkers.get(key)!!.setPosition(location);
        }
        val builder: LatLngBounds.Builder = LatLngBounds.Builder()
        for (marker: Marker in mMarkers.values) {
            builder.include(marker.getPosition())
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 200))

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


}
