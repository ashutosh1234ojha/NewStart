package com.example.newstart.databinding.event

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newstart.R
import com.example.newstart.databinding.ActivityEventBinding

/**
 * Created by Ashutosh Ojha on 04,January,2022
 */
class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityEventBinding>(this, R.layout.activity_event)

        binding.listener=MyListener()
        binding.listenerOne=this
        binding.listenerTwo=this
    }

    fun onClickHandleOne(view: View) {
        Toast.makeText(this, "onClickHandleOne", Toast.LENGTH_SHORT).show()
    }

    //Here this method should have same signatur as onClick(view:View) method  of View.OnClckListener()
    fun listenerOneMethod(view:View){
        Toast.makeText(this, "listenerOne", Toast.LENGTH_SHORT).show()

    }

    //Listeners Binding
    fun listenerTwoMethod(str:String) {
        Toast.makeText(this, "listenerTwoMethod $str", Toast.LENGTH_SHORT).show()
    }
}

class MyListener :View.OnClickListener{
    override fun onClick(view: View) {
        Toast.makeText(view.context, "MyListener", Toast.LENGTH_SHORT).show()
    }

}