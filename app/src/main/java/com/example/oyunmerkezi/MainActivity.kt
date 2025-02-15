package com.example.oyunmerkezi

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.oyunmerkezi.databinding.ActivityMainBinding
import com.example.oyunmerkezi.ui.main.GamesFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,GamesFragment()).commit()
    }
}
