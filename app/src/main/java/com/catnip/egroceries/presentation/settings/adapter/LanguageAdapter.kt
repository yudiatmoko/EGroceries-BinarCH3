package com.catnip.egroceries.presentation.settings.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.catnip.egroceries.model.Language

class LanguageAdapter(
    context: Context,
    resource: Int,
    languages: Array<String>
    ) : ArrayAdapter<String>(context,resource,languages)