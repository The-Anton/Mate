package com.solvabit.mate;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventRegRecyclerViewHolder extends RecyclerView.ViewHolder {


    public   TextView sub_event_name_text_view, prize_text_view;
    public   CheckBox checkBox_bt;


    public EventRegRecyclerViewHolder(View itemView) {
        super(itemView);

        sub_event_name_text_view= itemView.findViewById(R.id.sub_event_name_txt);
        prize_text_view = itemView.findViewById(R.id.prize_txt);
        checkBox_bt = itemView.findViewById(R.id.radio_bt_);

        /*checkBox_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                try
                {
                    // the String to int conversion happens here
                    int prize =Integer.parseInt(prize_text_view.getText().toString().trim());
                    // print out the value after the conversion
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                }


            }
        });*/
    }


}
