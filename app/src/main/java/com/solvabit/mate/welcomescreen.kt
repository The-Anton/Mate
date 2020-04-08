package com.solvabit.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcomescreen.*

class welcomescreen : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcomescreen)
        loginbutton.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        registerbutton.setOnClickListener {
            val intent1 = Intent(this,registration::class.java)
            startActivity(intent1)
        }


        }
    }
