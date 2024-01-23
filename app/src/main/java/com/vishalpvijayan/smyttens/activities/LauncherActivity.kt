package com.vishalpvijayan.smyttens.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.viewModels.LauncherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    private lateinit var launcherViewModel: LauncherViewModel
    private lateinit var tvSplash: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        launcherViewModel = ViewModelProvider(this).get(LauncherViewModel::class.java)
        tvSplash = findViewById(R.id.tvSplash) ?: TextView(this)

        launcherViewModel.animationFinished.observe(this) { animationFinished ->
            if (animationFinished) {
                val intent = Intent(this@LauncherActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Initialiation from the VM
        launcherViewModel.initializeSplashScreen(Handler())
    }
}

