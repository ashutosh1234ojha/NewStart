package com.example.newstart.databinding.twowaydatabinding.inverseBindingAdapter

import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.newstart.R
import com.example.newstart.databinding.twowaydatabinding.Cities
import com.example.newstart.databinding.twowaydatabinding.Gender

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */
//region used for infinite loop problem and its solution

//@BindingAdapter("android:text")
//fun setText(view:EditText,value:String){
//    Log.d("MyTag", "setText: called for $value")
//    if(value != view.text.toString())
//        view.setText(value)
//}
//endregion

@BindingAdapter("gender")
fun setGender(view: RadioGroup, gender: Gender) {
    when (gender) {
        Gender.MALE -> view.check(R.id.rb_male)
        Gender.FEMALE -> view.check(R.id.rb_female)
    }
}

@InverseBindingAdapter(attribute = "gender", event = "genderAttrChanged")
fun getGender(view: RadioGroup): Gender {
    return when (view.checkedRadioButtonId) {
        R.id.rb_male -> Gender.MALE
        R.id.rb_female -> Gender.FEMALE
        else -> Gender.MALE
    }
}

@BindingAdapter("genderAttrChanged")
fun setGenderListener(view: RadioGroup, listener: InverseBindingListener) {

    view.setOnCheckedChangeListener { group, checkedId ->
        listener.onChange()
    }

}

@BindingAdapter("setCity")
fun setCity(view: Spinner, city: Cities) {
    view.setSelection(city.ordinal)
}

@InverseBindingAdapter(attribute = "setCity")
fun getCity(view: Spinner): Cities {
    return Cities.values()[view.selectedItemPosition]
}

@BindingAdapter("setCityAttrChanged")
fun setCityListener(view: Spinner, listener: InverseBindingListener) {

    if (view.onItemSelectedListener == null) {
        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listener.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

}
