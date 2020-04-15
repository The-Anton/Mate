package com.solvabit.mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterEvent extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Event_reg_card> event_reg_cardsArrayList;
    EventRegRecyclerViewAdapter adapter;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerevent);






        event_reg_cardsArrayList = new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        //  uploadDataToFirebase();
        loadDataFromFirebase();
    }

    private void uploadDataToFirebase() {

        Map<String, Object> Names = new HashMap<>();
        Map<String, Object> Name = new HashMap<>();
        Map<String, Object> t = new HashMap<>();

        Names.put("Names",Name);
        Name.put("1",t);
        Name.put("0",1);
        t.put("Name","Basketball");
        t.put("Fees","100");


        db.document("/Army Institute Of Technology/Events/PACE/Event Details")
                .set(Names)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Msg", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Msg", "Error writing document", e);
                    }
                });
    }

    public void loadDataFromFirebase() {
        if (event_reg_cardsArrayList.size() > 0) {
            event_reg_cardsArrayList.clear();
        }

        db.document("/Army Institute Of Technology/Events/PACE/Event Details")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   DocumentSnapshot document = task.getResult();
                                                   if (document.exists()) {
                                                       long size = document.getLong("Names.0");
                                                       int i = 1;
                                                       while (size > 0) {
                                                           String temp = "Names." + i + ".Name";
                                                           System.out.println(temp + "    ");
                                                           String temp2 = "Names." + i + ".Fees";
                                                           System.out.println(temp2 + "    ");


                                                           String Name = document.getString(temp);
                                                           String Fees = document.getString(temp2);
                                                           System.out.println("//" + Name + "\\" + "    ");
                                                           System.out.println("//" + Fees + "\\" + "    ");

                                                           Event_reg_card event_reg_card = new Event_reg_card(Name, Fees);
                                                           event_reg_cardsArrayList.add(event_reg_card);
                                                           i++;
                                                           size--;
                                                       }
                                                   } else {
                                                       Log.d("Error", "No such document");
                                                   }
                                               } else {
                                                   Log.d("Error", "get failed with ", task.getException());
                                               }
                                               adapter = new EventRegRecyclerViewAdapter(RegisterEvent.this, event_reg_cardsArrayList);
                                               mRecyclerView.setAdapter(adapter);
                                           }

                                       }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterEvent.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void setUpFirebase() {
        db =FirebaseFirestore.getInstance();
    }


    private void setUpRecyclerView() {
        mRecyclerView =findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
