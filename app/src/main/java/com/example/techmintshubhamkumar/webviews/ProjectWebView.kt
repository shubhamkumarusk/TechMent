package com.example.techmintshubhamkumar.webviews

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.techmintshubhamkumar.R
import com.example.techmintshubhamkumar.databinding.ActivityProjectWebViewBinding

class ProjectWebView : AppCompatActivity() {
    private lateinit var binding:ActivityProjectWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProjectWebViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val url = intent.getStringExtra("url")
        binding.projectWebView.loadUrl(url!!)


    }
}