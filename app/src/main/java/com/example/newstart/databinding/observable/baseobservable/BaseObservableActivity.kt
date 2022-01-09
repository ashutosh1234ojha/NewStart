package com.example.newstart.databinding.observable.baseobservable

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newstart.R
import com.example.newstart.databinding.ActivityBaseObservableBinding

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */

class BaseObservableActivity : AppCompatActivity() {

    lateinit var viewModel: BaseObservableViewModel
//    private var observableArrayList=ObservableArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityBaseObservableBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_base_observable)

        viewModel = ViewModelProvider(this).get(BaseObservableViewModel::class.java)

        binding.observablePersonViewModel = viewModel
        binding.handler = this


    }

    fun onClick(view: View) {

        viewModel.baseObservablePerson.name = "Ashutosh"
        viewModel.baseObservablePerson.age = 99
//        person.age++
//        person.name = "Usman"
//        Toast.makeText(this, "Person age is ${person.age}", Toast.LENGTH_SHORT).show()
//
//        observableArrayList.set(0,"Kashyap")
    }
}
