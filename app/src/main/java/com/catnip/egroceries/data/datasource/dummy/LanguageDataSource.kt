package com.catnip.egroceries.data.datasource.dummy

import com.catnip.egroceries.model.Language

interface LanguageDataSource {
    fun getLanguage(): List<Language>
}

class LanguageDataSourceImpl : LanguageDataSource {
    override fun getLanguage(): List<Language> {
        return mutableListOf(
            Language(2, "English"),
            Language(1, "Bahasa Indonesia"),
            Language(3, "Bahasa Melayu"),
        )
    }
}