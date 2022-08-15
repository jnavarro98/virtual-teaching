package com.virtualteaching.studentsmagnet.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.virtualteaching.studentsmagnet.R
import com.virtualteaching.studentsmagnet.components.adapters.IconButtonAdapter
import com.virtualteaching.studentsmagnet.databinding.ActivityWelcomeBinding
import com.virtualteaching.studentsmagnet.model.IconButton
import com.virtualteaching.studentsmagnet.utils.launchUri


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

        initUi()
    }

    private fun initUi() {
        val iconButtonAdapter =
            IconButtonAdapter { Intent().launchUri(it.intentUri, this) }
        binding.rvIconButtons.layoutManager = layoutManager
        binding.rvIconButtons.adapter = iconButtonAdapter
        val divider = DividerItemDecoration(this, layoutManager.orientation)
        getDrawable(R.drawable.divider_rv)?.let { divider.setDrawable(it) }
        binding.rvIconButtons.addItemDecoration(divider)
        welcomeActivityViewModel.iconButtonLiveData.observe(this) {
            it?.let {
                iconButtonAdapter.submitList(it as MutableList<IconButton>)
            }
        }
    }
}