package com.example.kusitms_team3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class portfolio extends AppCompatActivity {
    private String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public static ArrayList nameList = new ArrayList(); // 사람들의 이름을 담은 list
    public static ArrayList percentList = new ArrayList();
    public static String myPercent;

    private EditText editText_name;
    private EditText editText_project_name;
    private EditText editText_role;
    private EditText editText_duration;
    private EditText editText_oneLine;
    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        Bundle bundle = getIntent().getExtras();

        nameList = bundle.getStringArrayList("namearray");
        percentList = bundle.getStringArrayList("percentarray");

        editText_name = findViewById(R.id.textView8);
        editText_project_name = findViewById(R.id.textView11);
        editText_role = findViewById(R.id.textView15);
        editText_duration = findViewById(R.id.textView17);
        editText_oneLine = findViewById(R.id.textView13);



        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPercent =  findMyName();
                if (myPercent  == null ) {

                } else {

                    model m = new model();
                    m.myPercent = myPercent;
                    m.myName = editText_name.getText().toString();
                    m.myProject = editText_project_name.getText().toString();
                    m.myRole = editText_role.getText().toString();
                    m.myDuration = editText_duration.getText().toString();
                    m.myOneLine = editText_oneLine.getText().toString();
                    m.myUid = myUid;
                    m.key = myUid + editText_oneLine.getText().toString();

                    FirebaseDatabase.getInstance().getReference().child("projects")
                            .child(myUid).setValue(m);
                    finish();
                }
            }


        });

    }

    private String findMyName() {
        String to = null;
        for(int i=0 ; i<nameList.size() ; i++) {
                if(nameList.get(i).equals(editText_name.getText().toString())) {
                    int result = (int) percentList.get(i);
                     to = Integer.toString(result);
                }
            }

        return to;
    }

}