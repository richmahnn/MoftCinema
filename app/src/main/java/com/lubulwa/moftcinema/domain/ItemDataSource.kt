package com.lubulwa.moftcinema.domain

import androidx.paging.PageKeyedDataSource
import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.presentation.resource.MoftResource
import com.lubulwa.moftcinema.presentation.resource.ResourceState
import com.lubulwa.moftcinema.remote.MovieService
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class ItemDataSource @Inject constructor(private val getMovies: GetMovies) : PageKeyedDataSource<Int, MoftMovie>() {

    private val firstPage = 1
    private var disposable: Disposable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MoftMovie>) {
        disposable = getMovies.execute(firstPage).subscribe({
            callback.onResult(it.moftMovies, null, firstPage + 1)
        }, {
            //callback.onResult(null, null, null)
            //moviesLiveData.postValue(MoftResource(ResourceState.ERROR, null, it.localizedMessage))
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoftMovie>) {
        disposable = getMovies.execute(params.key).subscribe({
            val key = if (it.page < it.totalPages) params.key + 1 else 1
            callback.onResult(it.moftMovies, key)
        }, {
            //moviesLiveData.postValue(MoftResource(ResourceState.ERROR, null, it.localizedMessage))
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoftMovie>) {
        disposable = getMovies.execute(params.key).subscribe({
            val key = if (params.key > 1) params.key - 1 else 1
            callback.onResult(it.moftMovies, key)
        }, {
            //moviesLiveData.postValue(MoftResource(ResourceState.ERROR, null, it.localizedMessage))
        })
    }


}