package com.solvabit.mate

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_leaderboard.*


class standing : AppCompatActivity() {

    val mDocRef = FirebaseFirestore.getInstance()
    val database= mDocRef.document("/Army Institute Of Technology/Leaderboard")

    var  firstTeam_name_item:String = "Team A"
    var  secondTeam_name_item:String = "Team B"
    var  thirdTeam_name_item:String = "Team C"
    var  fourthTeam_name_item:String = "Team D"

    var  firstTeam_Score_item:String = "00"
    var  secondTeam_Score_item:String = "00"
    var  thirdTeam_Score_item:String = "00"
    var  fourthTeam_Score_item:String = "00"



    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standing)
        fetchTeam()





        val recyclerView = findViewById<RecyclerView>(R.id.rank_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val data =ArrayList<Team>()

        data.add(Team(firstTeam_name_item,firstTeam_Score_item))
        data.add(Team(secondTeam_name_item,secondTeam_Score_item))
        data.add(Team(thirdTeam_name_item,thirdTeam_Score_item))
        data.add(Team(fourthTeam_name_item,fourthTeam_Score_item))

        val adapter = CustomAdapter(data)

        recyclerView.adapter =adapter




    }




    override fun onStart() {
        super.onStart()

        database.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("---->", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                firstTeam_name_item = snapshot.getString("Team A.Name").toString()
                secondTeam_name_item = snapshot.getString("Team B.Name").toString()
                thirdTeam_name_item = snapshot.getString("Team C.Name").toString()
                fourthTeam_name_item = snapshot.getString("Team D.Name").toString()




                firstTeam_Score_item = snapshot.getLong("Team A.Score").toString()
                secondTeam_Score_item = snapshot.getLong("Team B.Score").toString()
                thirdTeam_Score_item = snapshot.getLong("Team C.Score").toString()
                fourthTeam_Score_item = snapshot.getLong("Team D.Score").toString()

                Log.d("---->", "Current data: ${snapshot.data}")
            } else {
                Log.d("---->", "Current data: null")
            }
        }
    }


    override fun onPause() {
        super.onPause()
        mDocRef.disableNetwork()
    }



    private fun fetchTeam(){



        database.get().addOnSuccessListener {
            firstTeam_name_item= it.get("Team A.Name").toString()
            secondTeam_name_item= it.get("Team B.Name").toString()
            thirdTeam_name_item= it.get("Team C.Name").toString()
            fourthTeam_name_item= it.get("Team D.Name").toString()



            firstTeam_Score_item= it.get("Team A.Score").toString()
            secondTeam_Score_item= it.get("Team B.Score").toString()
            thirdTeam_Score_item= it.get("Team C.Score").toString()
            fourthTeam_Score_item= it.get("Team D.Score").toString()

        }





    }
}
