package com.ameen.notekeeper.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.ameen.notekeeper.R
import com.ameen.notekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private var isButtonAdd = true  // 0 -> Add  -- 1 -> Edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment

        val navController = navHostFragment.navController

        binding.addNoteButton.setOnClickListener {
            if (isButtonAdd) {
                isButtonAdd = false
                binding.addNoteButton.setImageResource(R.drawable.ic_done)
                navController.navigate(R.id.action_home_note_fragment_to_add_edit_fragment)
                Log.i(TAG, "onCreate: Add Pressed")
                Log.i(TAG, "onCreate: ButtonValue -> $isButtonAdd")
            } else {
                isButtonAdd = true
                binding.addNoteButton.setImageResource(R.drawable.ic_add)
                navController.popBackStack()
                Log.i(TAG, "onCreate: Complete Pressed")
            }
        }
    }
}