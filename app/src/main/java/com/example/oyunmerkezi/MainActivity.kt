package com.example.oyunmerkezi

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oyunmerkezi.databinding.ActivityMainBinding
import com.example.oyunmerkezi.ui.main.GameAdapter
import com.example.oyunmerkezi.ui.main.GameListener
import com.example.oyunmerkezi.ui.main.GameViewModel
import com.example.oyunmerkezi.util.FirebaseGame
import com.example.oyunmerkezi.util.Game
import com.example.oyunmerkezi.viewmodels.ViewModelProviderFactory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GameAdapter
    private val gameList = mutableListOf<FirebaseGame>()
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//         Setup RecyclerView
        gameViewModel =
            ViewModelProvider(this, providerFactory).get(GameViewModel::class.java)

        adapter =
            GameAdapter(
                GameListener {  },
                gameViewModel,
                binding.root
            )
        binding.gameList.layoutManager = LinearLayoutManager(this)
        binding.gameList.adapter = adapter
//
//        // Fetch Data from Firebase
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("platforms/PS3")


        gameViewModel.games.observe(this) { games ->
            adapter.submitList(games)
        }
    }
}
