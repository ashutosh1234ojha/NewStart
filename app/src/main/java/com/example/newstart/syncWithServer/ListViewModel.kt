package com.example.newstart.syncWithServer

import android.app.Application
import android.net.wifi.WifiAvailableChannel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkQuery
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ListViewModel(application: Application) : AndroidViewModel(application) {
    var userDao: UserDao
    var mDatabase: FirebaseDatabase
    var databaseReference: DatabaseReference
    var flowOfUse = MutableStateFlow(emptyList<UserData>())
//    var sd MutableSharedFlow<Int>(replay = 2)
//

    init {
        userDao = UserDatabase.getInstance(application).useDao()
        mDatabase = FirebaseDatabase.getInstance()
        databaseReference = mDatabase.getReference("user")
//        flowOfUse.value = userDao.getUserList()

        viewModelScope.launch {
            userDao.getUserList().collect {

                flowOfUse.value=it

            }
        }
    }

    fun handleSaveClick(userData: UserData) {

        val id = userDao.addUser(userData)

        val data = Data.Builder()
//            .putString("fName", userData.fName)
//            .putString("lName", userData.lName)
//            .putString("date", userData.date)
            .putLong("id", id)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest: OneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UserWorkManager::class.java)
                .setInputData(data)
                .setConstraints(constraints)
                .build()


        WorkManager.getInstance(getApplication()).enqueue(workRequest)

//            db.collection("users")
//                .add(user)
//                .addOnSuccessListener { documentReference ->
//                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w("TAG", "Error adding document", e)
//                }
//            val key = databaseReference.push().key
//            databaseReference.child(key ?: "").child("Name").setValue("Je")
//            databaseReference.child(key ?: "").child("Age").setValue("34")

    }

//    fun getListOfUser() {
//        flowOfUser = userDao.getUserList()
//    }
}