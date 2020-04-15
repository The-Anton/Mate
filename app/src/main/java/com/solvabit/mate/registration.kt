package com.solvabit.mate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.email_register
import kotlinx.android.synthetic.main.activity_registration.*


class registration : BaseActivity(), AdapterView.OnItemClickListener {
    val mDatabaseRef = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val spinnerState: Spinner = findViewById(R.id.state)


        ArrayAdapter.createFromResource(this, R.array.States, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerState.adapter = adapter
            }

        val spinnerCollege: Spinner = findViewById(R.id.college)

        ArrayAdapter.createFromResource(
            this,
            R.array.Maharastra,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCollege.adapter = adapter
            }

        val auth = FirebaseAuth.getInstance()
        registerbtn_register.setOnClickListener {
            performRegister()

        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun performRegister() {

        val email = email_register.text.toString()
        val password = password_register.text.toString()
        val cnfPassword = cnf_password_register.text.toString()

        if (password == cnfPassword) {
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show()
                return
            }



            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Please Try again !!!! or User already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@addOnCompleteListener
                    } else {
                        Log.d(
                            "Main",
                            "Account created successfully with uid : $(it.result.user.uid)"
                        )

                        val auth = FirebaseAuth.getInstance()
                        val user = auth.currentUser

                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("Main", "Email sent.")

                                }
                            }
                       Toast.makeText(
                            this,
                            "Successfully registered. Check your email for verification",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, login::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)



                        saveUserToFirebaseDatabase()


                    }


                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to create account : $(it.message)")
                }
        } else {
            Toast.makeText(
                this,
                "Password didn't matched ",
                Toast.LENGTH_SHORT
            ).show()

        }
    }


    private fun saveUserToFirebaseDatabase() {

        val uid= fetchUserUid()
        val dbRef = mDatabaseRef.collection("user").document(uid.toString())
        val firstname = firstname_register.text.toString()
        val lastname = lastname_register.text.toString()
        val phonenumber = phone_register.text.toString()

        dbRef.update("firstName", firstname)
        dbRef.update("lastName", lastname)
        dbRef.update("phone", phonenumber)
        dbRef.update("role", "Student")
        dbRef.update("college", "Army Institute Of Technology")




    }



    private fun fetchUserUid(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid
    }

}




