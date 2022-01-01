package com.example.newstart.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture

/**
 * Created by Ashutosh Ojha on 30,November,2021
 */
class TestListenableWorker(appContext: Context, workerParams: WorkerParameters) :ListenableWorker(appContext,
    workerParams
) {
    override fun startWork(): ListenableFuture<Result> {
        TODO("Not yet implemented")
    }

    override fun onStopped() {
        super.onStopped()
    }
}