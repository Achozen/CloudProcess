package com.studia.qwerty.cloudprocess.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.database.*
import com.studia.qwerty.cloudprocess.R
import com.studia.qwerty.cloudprocess.adapter.NotesAdapter
import com.studia.qwerty.cloudprocess.model.Note
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNotesFromServer()
        addNoteButton.setOnClickListener { startActivity(Intent(this, AddNoteActivity::class.java)) }
    }

    private fun getNotesFromServer() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Notes")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val t = object : GenericTypeIndicator<HashMap<String, @JvmSuppressWildcards Note>>() {}
                val noteArray = dataSnapshot.getValue(t)
                if (noteArray != null) {
                    setupRecyclerView(noteArray)
                }

                Log.d("TAGTAG", "Value is: " + noteArray.toString()!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAGTAG", "Failed to read value.", error.toException())
            }
        })
    }

    private fun setupRecyclerView(notes: HashMap<String, Note>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        val notesAdapter = NotesAdapter(notes, this)
        recyclerView.adapter = notesAdapter
    }

}
