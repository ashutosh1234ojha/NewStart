package com.example.newstart.databinding.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import com.example.newstart.databinding.fragment.Product

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */
class RecycleViewActivity : AppCompatActivity(),IMainActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val fragment = ProductListFragment()

        supportFragmentManager
            .beginTransaction()
            .addToBackStack("Product List Fragment")
            .add(R.id.fragment_container, fragment, "product_list_fragment")
            .commit()

    }
    override fun onProductClick(product: Product) {

        val fragment = ProductDetailFragment()

        var bundle = Bundle()
        bundle.putParcelable("product_key", product)

        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .addToBackStack("Product Detail Fragment")
            .replace(R.id.fragment_container, fragment, "product_detail_fragment")
            .commit()
    }

    override fun onProductImageClick(product: Product) {
        Toast.makeText(this, "${product.name} is clicked", Toast.LENGTH_SHORT).show()
    }
}