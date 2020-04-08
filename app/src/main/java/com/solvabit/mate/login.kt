package com.solvabit.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : BaseActivity() {

    var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<View>(R.id.registerbtn_login) as Button

        loginBtn.setOnClickListener(View.OnClickListener { view ->
            logi()
            //startActivity(Intent(this, Leaderboard::class.java))
        })

        textviewlink.setOnClickListener {
            val intent = Intent(this, SimpleLeaderboard::class.java)
            startActivity(intent)
        }
    }

    public fun logi () {
        val emailTxt = findViewById<View>(R.id.email_register) as EditText
        var email = emailTxt.text.toString()
        val passwordTxt = findViewById<View>(R.id.cnf_password_register) as EditText
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->

                val auth=FirebaseAuth.getInstance()
                val user=auth.currentUser
                if(user != null)
                {
                    if (user.isEmailVerified) {

                        val intent = Intent(this, Leaderboard::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_LONG).show()

                    }
                    else {



                        // No user is signed in

                        Toast.makeText(
                            this,
                            "Please verify your email before login.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }



                else {
                    Toast.makeText(this, "You are not registered.", Toast.LENGTH_SHORT).show()
                }
            })

        }else {
            Toast.makeText(this, "Please fill up the Credentials", Toast.LENGTH_SHORT).show()
        }



    }
}
