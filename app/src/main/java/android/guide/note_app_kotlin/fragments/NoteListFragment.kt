package android.guide.note_app_kotlin.fragments

import android.guide.note_app_kotlin.adapter.NoteAdapter
import android.guide.note_app_kotlin.data.DataSource
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.guide.note_app_kotlin.databinding.FragmentNoteListBinding
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

//TODO 9 CREATE NOTE LIST FRAGMENT to show all notes we will create
class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        recyclerView = binding.noteRecyclerView  //  just normal declaration of recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)  // might be a grid layout
        recyclerView.adapter = NoteAdapter(context) // set the adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}