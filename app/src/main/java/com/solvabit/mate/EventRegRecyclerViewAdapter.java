package com.solvabit.mate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRegRecyclerViewAdapter extends RecyclerView.Adapter<EventRegRecyclerViewHolder> {

    RegisterEvent registerEvent;
    ArrayList<Event_reg_card> eventsArrayList;
    int sum=0;

    public EventRegRecyclerViewAdapter(RegisterEvent registerEvent, ArrayList<Event_reg_card> eventsArrayList){
        this.registerEvent= registerEvent;
        this.eventsArrayList = eventsArrayList;
    }

    @NonNull
    @Override
    public EventRegRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context;
        LayoutInflater layoutInflater = LayoutInflater.from(registerEvent.getBaseContext());
        View view = layoutInflater.inflate(R.layout.card_elements, parent,false);

        return new EventRegRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRegRecyclerViewHolder holder, final int position) {
        holder.sub_event_name_text_view.setText(eventsArrayList.get(position).getSub_event_name());
        holder.prize_text_view.setText(eventsArrayList.get(position).getPrize());

        holder.checkBox_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventsArrayList.get(position).setSelected(isChecked);
                Toast.makeText(buttonView.getContext(), "Now Your Total Amount is: " + sumFunction(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int sumFunction(int p) {


            if(eventsArrayList.get(p).getSelected()){
                sum += Integer.parseInt(eventsArrayList.get(p).getPrize().trim());
        }else{
                sum -= Integer.parseInt(eventsArrayList.get(p).getPrize().trim());
            }

        return sum;
    }

    @Override
    public int getItemCount() {
        return eventsArrayList.size();
    }

    public int getSum(){
        return sum;
    }


}
