package com.example.notesappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteRVAdapter.INotesRVAdapter {

     lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRVAdapter(this,this)
        recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->

            list?.let {
                adapter.updateList(it)
            }
        })

        btn_save.setOnClickListener {
            val noteText = et_input.text.toString()
            if (noteText.isNotEmpty()){
                et_input.text.clear()
                viewModel.insertNote(Note(noteText))
            }
        }

    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
    }


}