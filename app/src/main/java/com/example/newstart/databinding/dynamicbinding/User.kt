package com.example.newstart.databinding.dynamicbinding

/**
 * Created by Ashutosh Ojha on 07,January,2022
 */
data class User(
    val name: String,
    val email: String,
    val phoneNo: String,
    val message: String,
    val address: String,
    val logType: LogType
){
    enum class LogType{
        CALL,
        EMAIL,
        MESSAGE
    }
    companion object{
        val userList = listOf<User>(
            User("Ali","ali@gmail.com","","","Lahore",LogType.EMAIL),
            User("Usman","","+1234567","","Karachi",LogType.CALL),
            User("Kamran","","","Hi...","Islamabad",LogType.MESSAGE),
            User("Hamza","","+1234567","","Lahore",LogType.CALL),
            User("Saad","","","How are your?","Karachi",LogType.MESSAGE),
            User("Umair","umair@gmail.com","","","Islamabad",LogType.EMAIL),
            User("Sheraz","","","What's up?","Karachi",LogType.MESSAGE),
            User("Sajid","sajid@gmail.com","","","Lahore",LogType.EMAIL),
            User("Nadeem","","+1234567","","Islamabad",LogType.CALL),
            User("Irfan","","","Hello","Karachi",LogType.MESSAGE),
            User("Qasim","qasim@gmail.com","","","Islamabad",LogType.EMAIL)
        )
    }
}