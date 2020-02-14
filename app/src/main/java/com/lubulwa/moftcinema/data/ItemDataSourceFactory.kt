package com.lubulwa.moftcinema.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.lubulwa.moftcinema.remote.model.MoftMovie
import javax.inject.Inject

class ItemDataSourceFactory @Inject constructor(private val itemDataSource: ItemDataSource) : DataSource.Factory<Int, MoftMovie>() {

    val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, MoftMovie>> = MutableLiveData()

    override fun create(): DataSource<Int, MoftMovie> {
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

}