package aldwin.tablante.com.appblock.Account.Model

/**
 * Created by Bobby on 03/05/2018.
 */
class User(accID : String,username: String , password: String , email :  String , code:String) {

    var username = username
    var password = password
    var email = email
    var accID = accID
    var codd = code
    constructor() : this("","","","" , "")



}