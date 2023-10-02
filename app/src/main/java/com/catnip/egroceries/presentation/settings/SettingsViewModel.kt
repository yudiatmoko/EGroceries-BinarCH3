package com.catnip.egroceries.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.catnip.egroceries.data.datasource.local.datastore.LanguagePreferenceDataSource
import com.catnip.egroceries.data.datasource.local.datastore.UserPreferenceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class SettingsViewModel(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val languagePreferenceDataSource: LanguagePreferenceDataSource
) : ViewModel() {

    fun setDarkModePref(isUsingDarkMode: Boolean){
        viewModelScope.launch {
            userPreferenceDataSource.setUserDarkModePref(isUsingDarkMode)
        }
    }

    fun setSelectedLanguage(langID: Int){
        viewModelScope.launch {
            languagePreferenceDataSource.setSelectedLanguage(langID)
        }
    }

    val getSelectedLanguageLiveData = languagePreferenceDataSource.getSelectedLanguageFlow().asLiveData(Dispatchers.IO)
}