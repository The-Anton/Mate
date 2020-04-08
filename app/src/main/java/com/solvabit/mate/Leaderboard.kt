package com.solvabit.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.activity_welcomescreen.*

class Leaderboard : BaseActivity() {


    val mateDatabaseRef = FirebaseFirestore.getInstance()
    val leaderboardDatabase =
        mateDatabaseRef.collection("Army Institute Of Technology").document("Leaderboard")
    var firstTeamName: String = ""
    var secondTeamName: String = ""
    var thirdTeamName: String = ""
    var fourthTeamName: String = ""

    var firstTeamScore: Int = 0
    var secondTeamScore: Int = 0
    var thirdTeamScore: Int = 0
    var fourthTeamScore: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)



        signout.setOnClickListener { FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }




        refresh()




        firstTeam_NameSetButton.setOnClickListener {
            firstTeamName = firstTeam_reqName.text.toString()
            leaderboardDatabase.update("Team A.Name", firstTeamName)
        }
        firstTeam_updateButton.setOnClickListener {
            var value = firstTeam_updatedData.getText().toString()
            firstTeamScore = Integer.parseInt(value)
            leaderboardDatabase.update("Team A.Score", firstTeamScore)
        }







       // mateDatabaseRef.addValueEventListener(postListner)

        /*{
            firstTeam_name.text = it.get("Team A.Name").toString()
        }*/









        secondTeam_NameSetButton.setOnClickListener {
            secondTeamName = secondTeam_reqName.text.toString()
            leaderboardDatabase.update("Team B.Name", secondTeamName)
        }
        secondTeam_updateButton.setOnClickListener {
            var value = secondTeam_updatedData.getText().toString()
            secondTeamScore = Integer.parseInt(value)
            leaderboardDatabase.update("Team B.Score", secondTeamScore)
        }




        thirdTeam_NameSetButton.setOnClickListener {
            thirdTeamName = thirdTeam_reqName.text.toString()
            leaderboardDatabase.update("Team C.Name", thirdTeamName)
        }
        thirdTeam_updateButton.setOnClickListener {
            var value = thirdTeam_updatedData.getText().toString()
            thirdTeamScore = Integer.parseInt(value)
            leaderboardDatabase.update("Team C.Score", thirdTeamScore)
        }





        fourthTeam_NameSetButton.setOnClickListener {
            fourthTeamName = fourthTeam_reqName.text.toString()
            leaderboardDatabase.update("Team D.Name", fourthTeamName)
        }
        fourthTeam_updateButton.setOnClickListener {
            var value = fourthTeam_updatedData.getText().toString()
            fourthTeamScore = Integer.parseInt(value)
            leaderboardDatabase.update("Team D.Score", fourthTeamScore)
        }





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



