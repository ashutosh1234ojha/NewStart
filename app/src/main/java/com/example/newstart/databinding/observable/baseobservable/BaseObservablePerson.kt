package com.example.newstart.databinding.observable.baseobservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.newstart.BR

/**
 * Created by Ashutosh Ojha on 09,January,2022
 */
class BaseObservablePerson(pName: String, pAge: Int) : BaseObservable() {

    @get:Bindable
    var name: String = pName
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)

//            listener.notifyChange(this, BR.name)
        }


    //    @Bindable
//    get() {
//        return field.toUpperCase()
//    }
    @get:Bindable
    var age: Int = pAge
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
//            listener.notifyChange(this, BR.age)
        }

//    val listener: PropertyChangeRegistry = PropertyChangeRegistry()

//    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
//        listener.add(callback)
//    }
//
//    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
//        listener.remove(callback)
//    }
}