package com.lubulwa.moftcinema.presentation.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.lubulwa.moftcinema.data.ItemDataSourceFactory
import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.presentation.resource.MoftResource
import com.lubulwa.moftcinema.presentation.resource.ResourceState
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TrendingMoviesViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val itemDataSourceFactory: ItemDataSourceFactory
) : ViewModel() {

    private val moviesLiveData = MutableLiveData<MoftResource<PagedList<MoftMovie>>>()
    private var disposable: Disposable? = null

    lateinit var itemPagedList: LiveData<PagedList<MoftMovie>>
    private lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, MoftMovie>>

    fun observeTrendingMovies(): MutableLiveData<MoftResource<PagedList<MoftMovie>>> {
        return moviesLiveData
    }

//    fun fetchMovies(pageNumber: Int) {
//        moviesLiveData.postValue(MoftResource(ResourceState.LOADING, null, null))
//
//        disposable = getMovies.execute(pageNumber).subscribe({
//            moviesLiveData.postValue(MoftResource(ResourceState.SUCCESS, it, null))
//        }, {
//            moviesLiveData.postValue(MoftResource(ResourceState.ERROR, null, it.localizedMessage))
//        })
//    }

    fun fetchMoviesPaged() {
        liveDataSource = itemDataSourceFactory.itemLiveDataSource
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

}
