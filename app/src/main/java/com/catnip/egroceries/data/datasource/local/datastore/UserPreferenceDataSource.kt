package com.catnip.egroceries.data.datasource.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.catnip.egroceries.utils.PreferenceDataStoreHelper
import kotlinx.coroutines.flow.Flow

interface UserPreferenceDataSource {
    suspend fun getUserDarkModePref(): Boolean
    suspend fun setUserDarkModePref(isUsingDarkMode: Boolean)
    fun getUserDarkModePrefFlow(): Flow<Boolean>
}

class UserPreferenceDataSourceImpl(
    private val preferenceDataStoreHelper: PreferenceDataStoreHelper
): UserPreferenceDataSource{
    override suspend fun getUserDarkModePref(): Boolean {
        return preferenceDataStoreHelper.getFirstPreference(
            PREF_USER_DARK_MODE, false)
    }

    override suspend fun setUserDarkModePref(
        isUsingDarkMode: Boolean,
    ) {
        preferenceDataStoreHelper.putPreference(
            PREF_USER_DARK_MODE, isUsingDarkMode)
    }

    override fun getUserDarkModePrefFlow(): Flow<Boolean> {
        return preferenceDataStoreHelper.getPreference(
            PREF_USER_DARK_MODE,false)
    }

    companion object{
        val PREF_USER_DARK_MODE = booleanPreferencesKey("PREF_USER_DARK_MODE")
    }

}