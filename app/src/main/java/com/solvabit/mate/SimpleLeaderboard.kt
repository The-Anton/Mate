package com.solvabit.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_leaderboard.*

import kotlinx.android.synthetic.main.activity_leaderboard.firstTeam_Score
import kotlinx.android.synthetic.main.activity_leaderboard.firstTeam_name
import kotlinx.android.synthetic.main.activity_leaderboard.fourthTeam_Score
import kotlinx.android.synthetic.main.activity_leaderboard.fourthTeam_name
import kotlinx.android.synthetic.main.activity_leaderboard.secondTeam_Score
import kotlinx.android.synthetic.main.activity_leaderboard.secondTeam_name

import kotlinx.android.synthetic.main.activity_leaderboard.thirdTeam_Score
import kotlinx.android.synthetic.main.activity_leaderboard.thirdTeam_name
import kotlinx.android.synthetic.main.activity_simple_leaderboard.*

class SimpleLeaderboard : BaseActivity() {


    val mateDatabaseRef = FirebaseFirestore.getInstance()
    val leaderboardDatabase = mateDatabaseRef.collection("Army Institute Of Technology").document("Leaderboard")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_leaderboard)



        fab_forAboutUs.setOnClickListener{
            val intent = Intent(this, AboutUs::class.java)
            startActivity(intent)        }
        refresh()


    }


    override fun onStart() {
        super.onStart()
        leaderboardDatabase.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("---->", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {

                firstTeam_name.text=snapshot.getString("Team A.Name")
                secondTeam_name.text=snapshot.getString("Team B.Name")
                thirdTeam_name.text=snapshot.getString("Team C.Name")
                fourthTeam_name.text=snapshot.getString("Team D.Name")




                firstTeam_Score.text= snapshot.getLong("Team A.Score").toString()
                secondTeam_Score.text= snapshot.getLong("Team B.Score").toString()
                thirdTeam_Score.text= snapshot.getLong("Team C.Score").toString()
                fourthTeam_Score.text= snapshot.getLong("Team D.Score").toString()

                  /*secondTeam_Score.text=snapshot.getString("Team B.Score")
                  thirdTeam_Score.text=snapshot.getString("Team C.Score")
                  fourthTeam_Score.text=snapshot.getString("Team D.Score")*/
                Log.d("---->", "Current data: ${snapshot.data}")
            } else {
                Log.d("---->", "Current data: null")
            }
        }


    }




    fun refresh(){

        leaderboardDatabase.get().addOnSuccessListener {
            firstTeam_name.text= it.get("Team A.Name").toString()
            secondTeam_name.text= it.get("Team B.Name").toString()
            thirdTeam_name.text= it.get("Team C.Name").toString()
            fourthTeam_name.text= it.get("Team D.Name").toString()



            firstTeam_Score.text= it.get("Team A.Score").toString()
            secondTeam_Score.text= it.get("Team B.Score").toString()
            thirdTeam_Score.text= it.get("Team C.Score").toString()
            fourthTeam_Score.text= it.get("Team D.Score").toString()

        }

    }
}
