package com.catnip.egroceries.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.egroceries.data.datasource.local.datastore.UserPreferenceDataSource
import kotlinx.coroutines.Dispatchers

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(
    private val userPreferenceDataSource: UserPreferenceDataSource
) : ViewModel() {
    val userDarkModeLiveData = userPreferenceDataSource.getUserDarkModePrefFlow().asLiveData(Dispatchers.IO)
}