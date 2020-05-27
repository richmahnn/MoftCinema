package com.lubulwa.moftcinema.presentation.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.lubulwa.moftcinema.domain.ItemDataSourceFactory
import com.lubulwa.moftcinema.presentation.resource.MoftResource
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.disposables.Disposable
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val PREFETCH_DISTANCE = 10

class TrendingMoviesViewModel @Inject constructor(
    private val itemDataSourceFactory: ItemDataSourceFactory
) : ViewModel() {

    private val moviesLiveData = MutableLiveData<MoftResource<PagedList<MoftMovie>>>()
    private var disposable: Disposable? = null

    lateinit var itemPagedList: LiveData<PagedList<MoftMovie>>
    private lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, MoftMovie>>

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

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
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

}
