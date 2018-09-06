package com.studia.qwerty.cloudprocess.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.studia.qwerty.cloudprocess.R
import com.studia.qwerty.cloudprocess.adapter.NotesAdapter
import com.studia.qwerty.cloudprocess.model.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        val list = arrayListOf<Note>()
        list.addAll(listOf(Note("Title1", "VFBDFBFBD", "11111"), Note("Title2", "VFBDFvdvBFBD", "11111"), Note("Title3", "VFBDFBdfvFBD", "1111221")))
        val notesAdapter = NotesAdapter(list, this)
        recyclerView.adapter = notesAdapter
    }

}
