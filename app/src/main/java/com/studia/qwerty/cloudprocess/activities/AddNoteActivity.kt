package com.studia.qwerty.cloudprocess.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.google.firebase.database.FirebaseDatabase
import com.studia.qwerty.cloudprocess.R
import com.studia.qwerty.cloudprocess.model.Note
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!TextUtils.isEmpty(noteTitle.text) && !TextUtils.isEmpty(noteContent.text)) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Notes")
            val note = Note(noteTitle.text.toString(), noteContent.text.toString(), System.currentTimeMillis())
            myRef.child(note.hashCode().toString()).setValue(note);
        }
    }
}