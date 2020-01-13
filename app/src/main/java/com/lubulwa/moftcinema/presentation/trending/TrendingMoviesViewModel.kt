package com.lubulwa.moftcinema.presentation.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lubulwa.cleanarchsample.presentation.data.MoftResource
import com.lubulwa.cleanarchsample.presentation.data.ResourceState
import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber
import javax.inject.Inject

class TrendingMoviesViewModel @Inject constructor(private val getMovies: GetMovies) : ViewModel() {

    private val moviesLiveData = MutableLiveData<MoftResource<List<MoftMovie>>>()

    init {
        fetchMovies()
    }

    fun observeTrendingMovies(): LiveData<MoftResource<List<MoftMovie>>> {
        return moviesLiveData
    }

    fun fetchMovies() {
        moviesLiveData.postValue(MoftResource(ResourceState.LOADING, null, null))
        getMovies.execute(TrendingMovieSubscriber())
    }

    private inner class TrendingMovieSubscriber : DisposableSubscriber<List<MoftMovie>>() {

        override fun onComplete() { }

        override fun onNext(data: List<MoftMovie>?) {
            moviesLiveData.postValue(MoftResource(ResourceState.SUCCESS, data, null))
        }

        override fun onError(t: Throwable) {
            t.printStackTrace()
            moviesLiveData.postValue(MoftResource(ResourceState.ERROR, null, t.localizedMessage))
        }

    }

    override fun onCleared() {
        getMovies.dispose()
        super.onCleared()
    }

}
