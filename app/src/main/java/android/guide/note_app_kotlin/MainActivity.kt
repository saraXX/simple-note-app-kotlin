package android.guide.note_app_kotlin


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.guide.note_app_kotlin.databinding.ActivityMainBinding
import android.guide.note_app_kotlin.fragments.NoteListFragmentDirections
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
// TODO 10 SETUP THE MainActivity AS A PARENT VIEW OF TWO FRAGMENT
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment // declare fragments container
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph) // setup app bar

        setupActionBarWithNavController(navController, appBarConfiguration)

//        setup floating action button to create a new note

        binding.fab.setOnClickListener { view ->
            val action = NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(
                index = -1
            ) // pass -1 to that this for create a new note, not for updating or deleting a note
            navController.navigate(action)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}