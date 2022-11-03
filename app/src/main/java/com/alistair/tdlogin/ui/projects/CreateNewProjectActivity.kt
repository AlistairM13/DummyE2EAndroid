package com.alistair.tdlogin.ui.projects

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.alistair.tdlogin.databinding.ActivityCreateNewProjectBinding
import com.alistair.tdlogin.ui.projects.treeinfo.AddTreeInfoActivity


private const val TAG = "newprojectcreation"
class CreateNewProjectActivity : AppCompatActivity() {
    companion object{
        const val INTENT_EXTRA_PROJECT_NAME = "name"
        const val INTENT_EXTRA_PROJECT_VISIBILITY = "visibility"
        const val INTENT_EXTRA_PROJECT_EDITABLE = "editable"
    }

    private lateinit var binding: ActivityCreateNewProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateNewProject.setOnClickListener {
            createNewProject()
            //todonext check if values are being entered
        }

    }

    private fun createNewProject() {
        val newProjectName = binding.etNewProjectName.text.toString()
        var (editableForAll, visibleForAll) = listOf(false, false)
        when (binding.visibilityRadioGroup.checkedRadioButtonId) {
            binding.radioVisToInv.id -> {}
            binding.radioVisToAll.id -> {
                visibleForAll = true
            }
            binding.radioVisAndEditToAll.id -> {
                visibleForAll = true
                editableForAll = true
            }
            else -> {}
        }

        val intent = Intent(this, AddTreeInfoActivity::class.java)
//        val intentBundle = Bundle()
//        intentBundle.putString(INTENT_EXTRA_PROJECT_NAME,newProjectName)
//        intentBundle.putBoolean(INTENT_EXTRA_PROJECT_EDITABLE,editableForAll)
//        intentBundle.putBoolean(INTENT_EXTRA_PROJECT_VISIBILITY,visibleForAll)
//        intent.putExtras(intentBundle)
        startActivity(intent)
    }

}