package com.solvabit.mate

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import io.github.rosariopfernandes.firecoil.load
import java.io.File


class userProfile : AppCompatActivity() {

    val mDatabaseRef = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        val uid = fetchUserUid()
        val dbRef = mDatabaseRef.collection("user").document(uid.toString())

        val personalinfo = findViewById<LinearLayout>(R.id.personalinfo)
       val experience = findViewById<LinearLayout>(R.id.experience)
       val review = findViewById<LinearLayout>(R.id.review)
       val personalinfobtn = findViewById<TextView>(R.id.personalinfobtn)
       val experiencebtn = findViewById<TextView>(R.id.experiencebtn)
       val reviewbtn = findViewById<TextView>(R.id.reviewbtn)

        val username = findViewById<TextView>(R.id.username_text)
        val role = findViewById<TextView>(R.id.role_txt)
        val discription = findViewById<TextView>(R.id.about_txt)
        val phoneNo = findViewById<TextView>(R.id.phone_txt)
        val email = findViewById<TextView>(R.id.email_txt)
        val college = findViewById<TextView>(R.id.college_txt)

        dbRef.get().addOnSuccessListener {
            username.text = it.get("firstName").toString()
            phoneNo.text = it.get("phone").toString()
            email.text =it.get("email").toString()
            discription.text =it.get("about").toString()
            college.text =it.get("college").toString()
            role.text =it.get("role").toString()

        }

        dbRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("---->", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                username.text = snapshot.getString("firstName")
                phoneNo.text = snapshot.getString("phone")
                email.text =snapshot.getString("email")
                discription.text =snapshot.getString("about")
                college.text =snapshot.getString("college")
                role.text =snapshot.getString("role")
                Log.d("---->", "Current data: ${snapshot.data}")
            } else {
                Log.d("---->", "Current data: null")
            }
        }


       val imageView= findViewById<ImageView>(R.id.profile_imageView)
        val  storageRef = FirebaseStorage.getInstance().reference
        val pathRef =storageRef.child("user/shekhar_200x200.jpg")
        imageView.load(pathRef) {
            transformations(CircleCropTransformation())
        }



        /*making personal info visible*/
        /*making personal info visible*/



        personalinfo.setVisibility(View.VISIBLE)
        experience.setVisibility(View.GONE)
        review.setVisibility(View.GONE)


        personalinfobtn.setOnClickListener(View.OnClickListener {
            personalinfo.setVisibility(View.VISIBLE)
            experience.setVisibility(View.GONE)
            review.setVisibility(View.GONE)
            personalinfobtn.setTextColor(resources.getColor(R.color.blue))
            experiencebtn.setTextColor(resources.getColor(R.color.grey))
            reviewbtn.setTextColor(resources.getColor(R.color.grey))
        })

        experiencebtn.setOnClickListener(View.OnClickListener {
            personalinfo.setVisibility(View.GONE)
            experience.setVisibility(View.VISIBLE)
            review.setVisibility(View.GONE)
            personalinfobtn.setTextColor(resources.getColor(R.color.grey))
            experiencebtn.setTextColor(resources.getColor(R.color.blue))
            reviewbtn.setTextColor(resources.getColor(R.color.grey))
        })

        reviewbtn.setOnClickListener(View.OnClickListener {
            personalinfo.setVisibility(View.GONE)
            experience.setVisibility(View.GONE)
            review.setVisibility(View.VISIBLE)
            personalinfobtn.setTextColor(resources.getColor(R.color.grey))
            experiencebtn.setTextColor(resources.getColor(R.color.grey))
            reviewbtn.setTextColor(resources.getColor(R.color.blue))
        })
    }

    private fun fetchUserUid(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid
    }




}
