package aldwin.tablante.com.appblock.Account.AppBlock.Database.Data

import aldwin.tablante.com.appblock.Account.AppBlock.Model.DevParent
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext


val DATABASE_NAME = "APPBLOCK"
val TABLE_NAME = "USERS"
val COL_ID = "USER_ID"
val COL_FNAME = "FirstName"
val COL_LNAME = "LastName"
val COL_CODE = "Code"
val COL_ON = "ON/OFF"

class DataBaseHandler (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(p0: SQLiteDatabase?) {


        val createTable = "CREATE TABLE IF NOT EXIST" + TABLE_NAME +
                "(" + COL_ID +
                " VARCHAR(50) PRIMARY KEY, "+ COL_FNAME +
                " VARCHAR(50), "+ COL_LNAME +  " VARCHAR(50), " + COL_CODE + " VARCHAR(50)) "
        p0!!.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(parentUser:DevParent){
        val db =  this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ID,parentUser.accId)
        cv.put(COL_FNAME,parentUser.firstName)
        cv.put(COL_LNAME,parentUser.lastName)
        cv.put(COL_CODE,parentUser.code)


        var result   = db.insert(TABLE_NAME,null,cv)
       var x = -1
        if(result == x.toLong()){

            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }

        else{

            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

        }


    }

    fun readData(): MutableList<DevParent>{
        var list : MutableList<DevParent> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * From " + TABLE_NAME
        val result = db.rawQuery(query,null)
result.moveToNext()
   do {
        var devparent = DevParent()
        devparent.accId = result.getString(result.getColumnIndex(COL_ID)).toString()
        devparent.firstName =result.getString(result.getColumnIndex(COL_FNAME)).toString()
        devparent.lastName= result.getString(result.getColumnIndex(COL_LNAME)).toString()
        devparent.code= result.getString(result.getColumnIndex(COL_CODE)).toString()

    }while(result.moveToNext())



        result.close()
        db.close()
        return list
    }

}