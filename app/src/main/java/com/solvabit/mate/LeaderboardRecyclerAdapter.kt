package com.solvabit.mate


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val dataList: ArrayList<Team>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val viewHol =LayoutInflater.from(parent.context).inflate(R.layout.scoreboard_recycler_view_item,parent, false)
        return ViewHolder(viewHol)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val dataset: Team = dataList[position]

        holder.teamName.text= dataset.TeamName
        holder.teamScore.text = dataset.TeamScore

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val teamName = itemView.findViewById(R.id.team_name)as TextView
        val teamScore= itemView.findViewById(R.id.team_score)as TextView
    }
}