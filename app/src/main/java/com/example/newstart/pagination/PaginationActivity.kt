package com.example.newstart.pagination

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
class PaginationActivity : AppCompatActivity() {

    lateinit var paginationAdapter: PaginationAdapter

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination)
     //   intiRv()
        intiRvStates()

        intiVm()
    }

    private fun intiRvStates() {
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        paginationAdapter = PaginationAdapter()
        rv.apply {
            adapter =
                paginationAdapter.withLoadStateFooter(LoaderStateAdapter { paginationAdapter.retry() }
                )
            layoutManager = LinearLayoutManager(this@PaginationActivity)


            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun intiRv() {
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        paginationAdapter = PaginationAdapter()
        rv.apply {
            adapter = paginationAdapter
            layoutManager = LinearLayoutManager(this@PaginationActivity)

            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    @ExperimentalPagingApi
    fun intiVm() {
        val vm: MainActivityVM by viewModels()
        //Direct network  call
        lifecycleScope.launch {
            vm.getListData().collectLatest {
                paginationAdapter.submitData(it)
            }
        }

        //Mediator Call
//        lifecycleScope.launch {
//            vm.getListDataUsingMediator().collectLatest {
//                paginationAdapter.submitData(it)
//            }
//        }
    }
}