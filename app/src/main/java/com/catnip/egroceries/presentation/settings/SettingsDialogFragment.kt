package com.catnip.egroceries.presentation.settings

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.catnip.egroceries.data.datasource.dummy.LanguageDataSourceImpl
import com.catnip.egroceries.data.datasource.local.datastore.LanguagePreferenceDataSourceImpl
import com.catnip.egroceries.data.datasource.local.datastore.UserPreferenceDataSourceImpl
import com.catnip.egroceries.data.datasource.local.datastore.appDataStore
import com.catnip.egroceries.databinding.FragmentSettingsDialogBinding
import com.catnip.egroceries.presentation.main.MainViewModel
import com.catnip.egroceries.presentation.settings.adapter.LanguageAdapter
import com.catnip.egroceries.utils.GenericViewModelFactory
import com.catnip.egroceries.utils.PreferenceDataStoreHelperImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SettingsDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSettingsDialogBinding

    private val mainViewModel : MainViewModel by activityViewModels()

    private val viewModel: SettingsViewModel by viewModels{
        val dataStore =  this.requireContext().appDataStore
        val dataStoreHelper = PreferenceDataStoreHelperImpl(dataStore)
        val userPreferenceDataSource = UserPreferenceDataSourceImpl(dataStoreHelper)
        val languagePreferenceDataSource = LanguagePreferenceDataSourceImpl(dataStoreHelper)
        GenericViewModelFactory.create(SettingsViewModel(userPreferenceDataSource, languagePreferenceDataSource))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo : observe value dark mode
        observeDarkModePref()
        setSwitchAction()

        setSpinnerLanguage()
    }

    private fun setSpinnerLanguage() {
        val languages = LanguageDataSourceImpl().getLanguage().map {it.selectedLanguage}.toTypedArray()
        val adapter = LanguageAdapter(requireContext(), R.layout.simple_spinner_item, languages)

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerLanguage.adapter = adapter

        viewModel.getSelectedLanguageLiveData.observe(viewLifecycleOwner){
            binding.spinnerLanguage.setSelection(it)
        }


        binding.spinnerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setSelectedLanguage(position)
                    val selectedLanguage = binding.spinnerLanguage.selectedItem.toString()
                    Toast.makeText(
                        requireContext(),
                        "Bahasa dipilih: $selectedLanguage",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                return
            }
        }
    }

    private fun observeDarkModePref() {
        mainViewModel.userDarkModeLiveData.observe(this){
            binding.swDarkMode.isChecked = it
        }
    }

    private fun setSwitchAction() {
        binding.swDarkMode.setOnCheckedChangeListener { _, isUsingDarkMode ->
            viewModel.setDarkModePref(isUsingDarkMode)
        }
    }
}