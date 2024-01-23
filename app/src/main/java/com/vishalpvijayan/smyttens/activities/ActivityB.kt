package com.vishalpvijayan.smyttens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishalpvijayan.smyttens.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("activityName")
        binding.textViewResponse.text = "Clicked: $name"
    }
}