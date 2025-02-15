package com.example.oyunmerkezi.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oyunmerkezi.R
import com.example.oyunmerkezi.databinding.FragmentGamesBinding
import com.example.oyunmerkezi.viewmodels.ViewModelProviderFactory
import com.google.android.material.chip.Chip
import javax.inject.Inject
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class GamesFragment : DaggerFragment() {

    @Inject
    lateinit var dataStore: DataStore<Preferences>
    private lateinit var binding: FragmentGamesBinding
    private lateinit var adapter: GameAdapter

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory  // ✅ Inject Factory

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(this, providerFactory)[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_games, container, false
        )

        adapter =
            GameAdapter(
                GameListener { },
                gameViewModel,
                binding.root
            )

        inflateChips(binding)

        binding.gameList.layoutManager = LinearLayoutManager(requireContext())
        binding.gameList.adapter = adapter
        binding.lifecycleOwner = this

        gameViewModel.games.observe(viewLifecycleOwner) { it ->
            it?.let {
                adapter.submitList(it)
            }
        }

        return binding.root
    }


//    private fun inflateChips(binding: FragmentGamesBinding) {
//        val platformsArray: Array<String> = resources.getStringArray(R.array.platforms)
//        val chipGroup = binding.platformList
//        val inflater = LayoutInflater.from(chipGroup.context)
//
//        lifecycleScope.launch {
//            // Combine active platforms and current platform flows
//            combine(
//                dataStore.data.map { preferences ->
//                    platformsArray.associateWith { key ->
//                        preferences[stringPreferencesKey(key)] as? Boolean ?: true
//                    }
//                },
//                dataStore.data.map { preferences ->
//                    preferences[stringPreferencesKey("current")] ?: "PS4"
//                }
//            ) { activePlatforms, currentPlatform ->
//                Pair(activePlatforms, currentPlatform)
//            }.collect { (activePlatforms, currentPlatform) ->
//                // Filter active platforms
//                val activePlatformsList = activePlatforms.filterValues { it }.keys
//
//                // Only proceed if there are multiple active platforms
//                if (activePlatformsList.size > 1) {
//                    chipGroup.removeAllViews() // Clear existing views
//
//                    // Inflate and add chips for active platforms
//                    activePlatformsList.forEach { platform ->
//                        val chip = inflater.inflate(R.layout.platform, chipGroup, false) as Chip
//                        chip.text = platform
//                        chip.tag = platform
//                        chip.isChecked = platform == currentPlatform
//
//                        // Handle chip selection
//                        chip.setOnCheckedChangeListener { button, isChecked ->
//                            if (isChecked) {
//                                lifecycleScope.launch {
//                                    savePlatForm("current", button.text.toString())
//                                }
//                            }
//                        }
//
//                        chipGroup.addView(chip)
//                    }
//                }
//            }
//        }
//    }


    private fun inflateChips(binding: FragmentGamesBinding) {
        val platformsArray: Array<String> = resources.getStringArray(R.array.platforms)
        val chipGroup = binding.platformList
        val inflater = LayoutInflater.from(chipGroup.context)

        val activePlatforms: Flow<Map<String, Boolean>> = dataStore.data.map { preferences ->
            platformsArray.associateWith { key ->
                preferences[stringPreferencesKey(key)] as? Boolean ?: true
            }
        }

        val currentPlatform: Flow<String> = dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("current")] ?: "PS4"
        }

        lifecycleScope.launch {
            combine(activePlatforms, currentPlatform) { platformsMap, selectedPlatform ->
                platformsMap.filterValues { it } to selectedPlatform
            }.collect { (enabledPlatforms, selectedPlatform) ->
                chipGroup.removeAllViews()
                if (enabledPlatforms.size > 1) {
                    enabledPlatforms.keys.forEach { key ->
                        val chip = inflater.inflate(R.layout.platform, chipGroup, false) as Chip
                        chip.text = key
                        chip.tag = key
                        chip.isChecked = (key == selectedPlatform)
                        chip.setOnCheckedChangeListener { button, isChecked ->
                            if (isChecked) {
                                lifecycleScope.launch {
                                    savePlatForm("current", button.text.toString())
                                }
                            }
                        }
                        chipGroup.addView(chip)
                    }
                }
            }
        }
    }

    private suspend fun savePlatForm(key: String, platform: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = platform
        }
    }
}