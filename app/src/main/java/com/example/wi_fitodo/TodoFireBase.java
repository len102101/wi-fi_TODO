package com.example.wi_fitodo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodoFireBase{
    public DatabaseReference reference;

    public String title;
    public String context;
    public String date;
    public boolean check;

    public TodoFireBase(String title, String context, String date, boolean check){
        this.title = title;
        this.context = context;
        this.date = date;
        this.check = check;
    }

    public void insertData(String ssid){
        TodoFireBase todoData = new TodoFireBase(title, context, date, check);
        reference = FirebaseDatabase.getInstance().getReference();
        String key = reference.child("todos").push().getKey();
        reference.child("todos").child(ssid).child(key).setValue(todoData);
    }
}
