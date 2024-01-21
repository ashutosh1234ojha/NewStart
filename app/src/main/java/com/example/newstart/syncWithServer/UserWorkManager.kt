package com.example.newstart.syncWithServer

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserWorkManager(context: Context, userParameters: WorkerParameters) :
    Worker(context, userParameters) {
    var mDatabase: FirebaseDatabase
    var databaseReference: DatabaseReference
    val db = Firebase.firestore
    val userDb: UserDatabase


    init {
        mDatabase = FirebaseDatabase.getInstance()
        databaseReference = mDatabase.getReference("user")
        userDb = UserDatabase.getInstance(context)
    }

    override fun doWork(): Result {

        var result: Result = Result.failure()
        val key = databaseReference.push().key

        val keyDb = inputData.getLong("id", -1)
//        databaseReference.child(key ?: "").child("fName").setValue(inputData.getString("fName"))
//        databaseReference.child(key ?: "").child("lName").setValue(inputData.getString("lName"))
//
//        databaseReference.child(key ?: "").child("date").setValue(inputData.getString("date"))
//            .addOnSuccessListener {
//
//              c
//            }

//
//        databaseReference.child(key ?: "").setValue(
//            UserData(
//                fName = inputData.getString("fName") ?: "",
//                lName = inputData.getString("lName") ?: "",
//                date = inputData.getString("date") ?: ""
//            )
//        )

//        GlobalScope.launch {
//            val list = userDb.useDao().getUnSyncedUserList()
//            for (item in list) {
//                item.isSynced = 1
//                db.collection("users").add(
//                    item
//                ).addOnSuccessListener { documentReference ->
//                    userDb.useDao().addUser(item)
//                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
//                    result = Result.success()
//                }.addOnFailureListener { e ->
//                    Log.w("TAG", "Error adding document", e)
//                    result = Result.failure()
//                }
//            }


//        }
//        GlobalScope.launch {

       val item=     userDb.useDao().getDataById(keyDb)
//                for (item in it) {
                    item.isSynced = 1
                    db.collection("users").add(
                        item
                    ).addOnSuccessListener { documentReference ->
                        userDb.useDao().addUser(item)
                        result=Result.success()

                        Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }.addOnFailureListener { e ->
                        Log.w("TAG", "Error adding document", e)
                        result=Result.failure()

                    }
//                }
//            }


//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("TAG", "Error adding document", e)
//            }
//        return Result.failure()
        return result
    }
}