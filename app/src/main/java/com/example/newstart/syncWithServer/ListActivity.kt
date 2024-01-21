package com.example.newstart.syncWithServer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.newstart.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ListActivity : AppCompatActivity() {

    lateinit var fName: EditText
    lateinit var lName: EditText
    val db = Firebase.firestore
    lateinit var userDao: UserDao

    lateinit var vm: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        userDao = UserDatabase.getInstance(application).useDao()

        fName = findViewById(R.id.firstName)
        lName = findViewById(R.id.secondName)
        vm = ViewModelProvider(this)[ListViewModel::class.java]

//        val list = arrayListOf<UserData>(
//            UserData(0, "first", "second", "asfdsd"),
//            UserData(1, "12", "11", "Test")
//        )

        val rv = findViewById<RecyclerView>(R.id.rv_user)
        val userAdapter = UserAdapter()
        rv.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@ListActivity)
        }

        lifecycleScope.launch {
            vm.flowOfUse.collect {
                userAdapter.updateData(it)

            }
        }


        val btn = findViewById<Button>(R.id.btn_save)
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815,
        )

        btn.setOnClickListener {
            val userData = UserData(
                fName = fName.text.toString(),
                lName = lName.text.toString(),
                date = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.CANADA).format(Date())
            )
//            val id=   userDao.addUser(userData)

            vm.handleSaveClick(userData)
//
//            val data = Data.Builder()
////            .putString("fName", userData.fName)
////            .putString("lName", userData.lName)
////            .putString("date", userData.date)
//                .putLong("id", id)
//                .build()
//
//            val constraints = Constraints.Builder()
//                .setRequiresCharging(true)
//                .build()
//
//            val workRequest: OneTimeWorkRequest =
//                OneTimeWorkRequest.Builder(UserWorkManager::class.java)
//                    .setInputData(data)
//                    .setConstraints(constraints)
//                    .build()
//
//
//            WorkManager.getInstance(application)
//                .enqueue(workRequest)


        }

    }
}