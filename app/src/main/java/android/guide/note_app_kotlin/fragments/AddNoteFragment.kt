package android.guide.note_app_kotlin.fragments

import android.guide.note_app_kotlin.MainActivity
import android.guide.note_app_kotlin.R
import android.guide.note_app_kotlin.data.DataSource
import android.guide.note_app_kotlin.databinding.FragmentAddNoteBinding
import android.guide.note_app_kotlin.model.Note
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
// TODO 7 : create add note fragment
class AddNoteFragment : Fragment() {
    private var _binding : FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var saveBtn : Button
    private lateinit var deleteBtn : Button
    private lateinit var cancelBtn : Button
    private lateinit var titleTextView : TextView
    private lateinit var bodyTextView : TextView

/*  this var is important to determine if this fragment start from floating button
     or from clicking to a list item.
     * if this fragment started from floating button )
        -> this mean the noteIndex = -1
            -> this mean this fragment will create a new item

     * if this fragment starts from clicking an item from the list,
        -> this mean the noteIndex = item index from DataSource.noteList
            -> this lead to update or remove existing item

     * noteIndex value passed as arguments in nav_graph     */
    private var noteIndex: Int = -1
    companion object {
        val index = "index"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arg from nav graph
        arguments?.let {
            noteIndex = it.getInt(index)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        if(noteIndex<0){
//        noteIndex value = -1 // started from floating btn
           saveNote()
        }
        else{
//       noteIndex might be 0 or more // so that i can update or remove existing note
            editNote()
            deleteNote()
        }
//        enable cancel btn
        cancelNote()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initViews(){
        saveBtn = binding.saveBtn
        deleteBtn = binding.deleteBtn
        cancelBtn = binding.cancelBtn
        titleTextView = binding.titleEditTextView
        bodyTextView = binding.bodyEditTextView
    }


/*
            ALL METHODS BELLOW WILL REPRESENT THE BUTTON JOB
 */
    fun saveNote(){
        saveBtn.setOnClickListener {
            val title = titleTextView.text.toString()
            val body = bodyTextView.text.toString()
            val note = Note(title,body)

            DataSource.noteList.add(note)
            returnToListFragment()
        }
    }

    fun deleteNote(){
        deleteBtn.setOnClickListener {
            DataSource.noteList.removeAt(noteIndex)
            returnToListFragment()
        }
    }

    fun cancelNote(){
        cancelBtn.setOnClickListener {
            returnToListFragment()
        }
    }

    fun editNote(){
//        show exist item data in views
        titleTextView.text = DataSource.noteList[noteIndex].noteTitle
        bodyTextView.text = DataSource.noteList[noteIndex].noteBody
//        rename save btn to be update
        saveBtn.text = "update"
//        show delete btn in UI
        deleteBtn.visibility = View.VISIBLE

//        take the edited data from views and save it in item list
        saveBtn.setOnClickListener{
            val title = titleTextView.text.toString()
            val body = bodyTextView.text.toString()
            val note = Note(title,body)

            DataSource.noteList[noteIndex]= note
            returnToListFragment()
        }

    }

    // this method called after save - delete - update a note to return to noteListFragment
    fun returnToListFragment(){
        findNavController().navigate(R.id.action_EditNoteFragment_to_NoteListFragment)
    }
}