package com.studia.qwerty.cloudprocess.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studia.qwerty.cloudprocess.R
import com.studia.qwerty.cloudprocess.model.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter(val items: ArrayList<Note>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle?.text = items[position].title
        holder.noteDate?.text = items[position].createDate
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val noteTitle = view.noteTitle
    val noteDate = view.noteDate
}