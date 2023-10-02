package com.catnip.egroceries.data.datasource.local.datastore

import androidx.datastore.preferences.core.intPreferencesKey
import com.catnip.egroceries.utils.PreferenceDataStoreHelper
import kotlinx.coroutines.flow.Flow

interface LanguagePreferenceDataSource {
    fun getSelectedLanguageFlow(): Flow<Int>
    suspend fun getSelectedLanguage(): Int
    suspend fun setSelectedLanguage(langID: Int)
}

class LanguagePreferenceDataSourceImpl(
    private val preferenceDataStoreHelper: PreferenceDataStoreHelper
) : LanguagePreferenceDataSource{

    override suspend fun getSelectedLanguage(): Int {
        return preferenceDataStoreHelper.getFirstPreference(
            PREF_SELECTED_LANGUAGE, 0
        )
    }

    override suspend fun setSelectedLanguage(langID: Int) {
        preferenceDataStoreHelper.putPreference(
            PREF_SELECTED_LANGUAGE, langID
        )
    }

    override fun getSelectedLanguageFlow(): Flow<Int> {
        return preferenceDataStoreHelper.getPreference(
            PREF_SELECTED_LANGUAGE, 0
        )
    }

    companion object{
        val PREF_SELECTED_LANGUAGE = intPreferencesKey("PREF_SELECTED_LANGUAGE")
    }

}