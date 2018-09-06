package com.studia.qwerty.cloudprocess.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studia.qwerty.cloudprocess.R
import com.studia.qwerty.cloudprocess.model.Note
import kotlinx.android.synthetic.main.note_item.view.*
import java.text.SimpleDateFormat
import java.util.*


class NotesAdapter(val items: HashMap<String, Note>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle?.text = items.values.toTypedArray()[position].title
        holder.noteContent?.text = items.values.toTypedArray()[position].content

        val dateString = getDate(items.values.toTypedArray()[position].createDate, "dd/MM/yyyy hh:mm")
        holder.noteDate?.text = dateString
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getDate(milliSeconds: Long, dateFormat: String): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val noteTitle = view.noteTitle
    val noteContent = view.noteContent
    val noteDate = view.noteDate
}