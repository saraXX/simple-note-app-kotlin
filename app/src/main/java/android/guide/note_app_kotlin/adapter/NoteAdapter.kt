package android.guide.note_app_kotlin.adapter

import android.content.Context
import android.guide.note_app_kotlin.R
import android.guide.note_app_kotlin.data.DataSource
import android.guide.note_app_kotlin.fragments.NoteListFragmentDirections
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import android.guide.note_app_kotlin.fragments.NoteListFragment as NoteListFragment1
// TODO 8 CREATE NOTE LIST ADAPTER SHOWS IN NoteListFragment

// must pass the context
class NoteAdapter(
    private val context: Context?) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

//    get reference of noteList
    private var noteList = DataSource.noteList

// inner class to handel views
    class NoteViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val noteTitleView: TextView = view.findViewById(R.id.title_itemView)
        val noteBodyView: TextView = view.findViewById(R.id.body_itemView)
    }
// handel layout of list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.note_list_item,parent,false)

        return NoteViewHolder(adapterLayout)
    }

//    all the logic here
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//    the noteItem will used to display a single Note[title, body]
        val noteItem = noteList[position]
//    show note data in views
        holder.noteTitleView.text = noteItem.noteTitle
        holder.noteBodyView.text = noteItem.noteBody
//    what happened if i click to any item list ?
        holder.view.setOnClickListener {
            /*
            actionNoteListFragmentToAddNoteFragment -> the action from nav graph.
            index -> the arguments in the nav graph(set in AddNoteFragment arguments pane)

            so here we pass the index of noteList to be updated or removed
             */
            val action = NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(
                index = position)

//            start the other fragment with proper args
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = noteList.size
}