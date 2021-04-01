package com.example.kusitms_team3.MainPagePackage;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kusitms_team3.CustomAdapter;
import com.example.kusitms_team3.Detail;
import com.example.kusitms_team3.R;
import com.example.kusitms_team3.model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainPage_Fragment4 extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManger;
    private ArrayList<model> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Context context;
    private String DestinationUid;
    //=================================gps

    private ActivityOptions activityOptions = null;
    private String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.fragment_main_page_4, container, false);


        context = rootview.getContext();
        recyclerView = rootview.findViewById(R.id.recyclerView); //아디 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화
        layoutManger = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManger);
        arrayList = new ArrayList<>(); //User 객체를 담을 어레이 리스트 (어댑터 쪽으로)
        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동


        databaseReference = database.getReference("projects"); //파이어 베이스에서 user
        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                                        arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 데이터 list를 추출해냄
                                                            model m = snapshot.getValue(model.class); //만들어뒀던 User 객체에 데이터를 담는다

                                                            if (!myUid.equals(m.myUid)) {
                                                                continue;
                                                            }
                                                            arrayList.add(m); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                                        }
                                                        adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
                                                    }


                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    }
                                                });


        adapter = new CustomAdapter(arrayList, context, new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Intent intent = new Intent(view.getContext(), Detail.class);
                final Bundle salesInfoBundle = new Bundle();
                final String destinationUid = arrayList.get(position).key;
                DestinationUid = destinationUid;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 데이터 list를 추출해냄
                            String key = snapshot.child("key").getValue(String.class); //uid

                            if (key.equals(destinationUid)) {

                                String myProject = snapshot.child("myProject").getValue(String.class); // address
                                String myName = snapshot.child("myName").getValue(String.class); // username
                                String myDuration = snapshot.child("myDuration").getValue(String.class); // address
                                String myRole = snapshot.child("myRole").getValue(String.class); // username
                                String myOneLine = snapshot.child("myOneLine").getValue(String.class);

                                salesInfoBundle.putString("myProject", myProject);
                                salesInfoBundle.putString("myName", myName);
                                salesInfoBundle.putString("myDuration", myDuration);
                                salesInfoBundle.putString("myRole", myRole);
                                salesInfoBundle.putString("myOneLine", myOneLine);

                                intent.putExtras(salesInfoBundle);

                                startActivity(intent);


                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        return rootview;
    }
}