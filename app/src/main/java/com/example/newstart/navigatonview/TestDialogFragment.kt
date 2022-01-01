package com.example.newstart.navigatonview

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 21,October,2021
 */
class TestDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.FullScreenDialogStyle
        );
    }

}