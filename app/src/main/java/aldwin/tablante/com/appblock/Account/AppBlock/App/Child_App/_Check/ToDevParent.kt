package aldwin.tablante.com.appblock.Account.AppBlock.App.Child_App._Check

import aldwin.tablante.com.appblock.Account.AppBlock.Model.DevParent
import aldwin.tablante.com.appblock.Account.Model.User

class ToDevParent {

    fun ParseObject(user:User):DevParent{
        val accID = user.accID
        val Firstname = user.Firstname
        val Lastname = user.Lastname
        val code = user.codd


        return DevParent(accID,Firstname,Lastname,code)

    }
}