package com.akoufatzis.weatherappclean

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.akoufatzis.weatherappclean.databinding.ActivityMainBinding
import com.akoufatzis.weatherappclean.search.mvp.view.MvpSearchActivity
import com.akoufatzis.weatherappclean.search.mvvm.view.MvvmSearchActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding){
            bMvp.setOnClickListener(this@MainActivity)
            bMvvm.setOnClickListener(this@MainActivity)
            bMvvmDatabinding.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bMvp -> startActivity(Intent(this, MvpSearchActivity::class.java))
            R.id.bMvvm -> startActivity(Intent(this, MvvmSearchActivity::class.java))
            R.id.bMvvmDatabinding -> Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }
}

