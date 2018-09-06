package com.studia.qwerty.cloudprocess.activities

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
    }

    private fun getNotesFromServer() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Notes")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val t = object : GenericTypeIndicator<List<@JvmSuppressWildcards Note>>() {

                }

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

    private fun setupRecyclerView(notes: List<Note>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        /*       val list = arrayListOf<Note>()
               list.addAll(listOf(Note("Title1", "VFBDFBFBD", 111111), Note("Title2", "VFBDFvdvBFBD", 11), Note("Title3", "VFBDFBdfvFBD", "1111221")))*/
        val notesAdapter = NotesAdapter(notes, this)
        recyclerView.adapter = notesAdapter
    }

}
