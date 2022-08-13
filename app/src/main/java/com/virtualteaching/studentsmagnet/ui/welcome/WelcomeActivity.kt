package com.virtualteaching.studentsmagnet.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.virtualteaching.studentsmagnet.R
import com.virtualteaching.studentsmagnet.components.adapters.IconButtonAdapter
import com.virtualteaching.studentsmagnet.databinding.ActivityWelcomeBinding
import com.virtualteaching.studentsmagnet.model.IconButton

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWelcomeBinding
    private val welcomeActivityViewModel by viewModels<WelcomeViewModel> {
        WelcomeViewModelFactory(this)
    }
    private val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val iconButtonAdapter = IconButtonAdapter { /*TODO: Call URI launcher here with it.intentUri*/ }
        binding.rvIconButtons.layoutManager = layoutManager
        binding.rvIconButtons.adapter = iconButtonAdapter
        binding.rvIconButtons.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        welcomeActivityViewModel.iconButtonLiveData.observe(this) {
            it?.let {
                iconButtonAdapter.submitList(it as MutableList<IconButton>)
            }
        }

    }
}