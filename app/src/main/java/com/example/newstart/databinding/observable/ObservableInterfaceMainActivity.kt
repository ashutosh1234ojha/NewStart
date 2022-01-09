package com.example.newstart.databinding.observable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.example.newstart.R
import com.example.newstart.databinding.ActivityObservableMainBinding

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */

class ObservableInterfaceMainActivity : AppCompatActivity() {

    private var person = ObservablePerson("Ali", 35)
    private var observableArrayList=ObservableArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding :ActivityObservableMainBinding=
            DataBindingUtil.setContentView(this, R.layout.activity_observable_main)

        binding.person = person
        binding.handler = this
        binding.observalbleArrayList=observableArrayList

        observableArrayList.add("Asutosh")
        observableArrayList.add("Ojha")
        observableArrayList.add("Singh")
    }

    fun onClick(view: View) {
        person.age++
        person.name = "Usman"
        Toast.makeText(this, "Person age is ${person.age}", Toast.LENGTH_SHORT).show()

        observableArrayList.set(0,"Kashyap")
    }
}
