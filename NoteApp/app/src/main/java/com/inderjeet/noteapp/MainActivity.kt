package com.inderjeet.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.inderjeet.noteapp.adapter.Adapter
import com.inderjeet.noteapp.databinding.ActivityMainBinding
import com.inderjeet.noteapp.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val noteAdapter by lazy {
        Adapter(
            {
                homeViewModel.deleteNote(it)
            },{
                Bottom_fragment(it).show(supportFragmentManager,"EditNote")
            },{
                Bottom_fragment(it,true).show(supportFragmentManager,"showNote")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        homeViewModel.initDB(applicationContext)
        onClick()
        initView()
        observer()
    }

    private fun observer() {
        CoroutineScope(Dispatchers.IO).launch {
            homeViewModel.getNotes().collectLatest {
                withContext(Dispatchers.Main) {
                    noteAdapter.submitList(it)
                }
            }
        }
    }

    private fun initView() {
        binding.apply {
            rvNotes.adapter = noteAdapter
        }
    }

    private fun onClick() {
        binding.apply {
            favAddNote.setOnClickListener {
                Bottom_fragment().show(supportFragmentManager, "AddNote")
            }
        }
    }
}
