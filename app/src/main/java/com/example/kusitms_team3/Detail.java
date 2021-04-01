package com.example.kusitms_team3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    public TextView myName;
    public TextView myProject;
    public TextView myRole;
    public TextView myDuration;
    public TextView myOneLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

       String myProjects = intent.getStringExtra("myProject");
       String myNames = intent.getStringExtra("myName");
       String myDurations = intent.getStringExtra("myDuration");
       String myRoles = intent.getStringExtra("myRole");
        String myOneLines = intent.getStringExtra("myOneLine");


        myName = findViewById(R.id.textView8);
        myProject = findViewById(R.id.textView11);
        myRole = findViewById(R.id.textView15);
        myDuration = findViewById(R.id.textView17);
        myOneLine = findViewById(R.id.textView13);

        myName.setText(myNames);
        myProject.setText(myProjects);
        myRole.setText(myRoles);
        myDuration.setText(myDurations);
        myOneLine.setText(myOneLines);



    }
}