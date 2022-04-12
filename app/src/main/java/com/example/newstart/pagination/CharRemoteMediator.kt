package com.example.newstart.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.example.newstart.pagination.network.CharacterData
import com.example.newstart.pagination.network.RetroService
import com.example.newstart.pagination.room.CharDb
import kotlinx.coroutines.supervisorScope
import java.io.IOException

/**
 * Created by Ashutosh Ojha on 12,April,2022
 */
@OptIn(ExperimentalPagingApi::class)
class CharRemoteMediator(
    private val query: Int,
    private val database: CharDb,
    private val networkService: RetroService
) : RemoteMediator<Int, CharacterData>() {
    val userDao = database.userDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterData>
    ): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    lastItem.uid
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = networkService.getDataFromAPI(
                query = query
            )

            supervisorScope {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                       // userDao.d(query)
                        userDao.clearAll()
                    }

                    // Insert new users into database, which invalidates the
                    // current PagingData, allowing Paging to present the updates
                    // in the DB.
                    //   userDao.insertAll(response.users)
                    userDao.insertAll(response.results)
                }
            }



            MediatorResult.Success(
                endOfPaginationReached = response.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}