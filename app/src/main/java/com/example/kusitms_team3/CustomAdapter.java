package com.example.kusitms_team3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;
    private ArrayList<model> arrayList; //아까 만든 class에서의 User
    private Context context;

    public CustomAdapter(ArrayList<model> arrayList, Context context, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_portfolio, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    } //adapter에 연결이 되고난 후 viewholder를 최초로 만들어 낸다.

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.CustomViewHolder holder, final int position) {

        holder.projectName.setText(arrayList.get(position).myName);
        holder.percent.setText(arrayList.get(position).myPercent);
        holder.duration.setText(arrayList.get(position).myDuration);
        holder.oneline.setText(arrayList.get(position).myOneLine);
        holder.oneline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder { //listview에서 만든 것들을 여기로 불러 놓을 거임


        TextView projectName; //프로젝트 이름
        TextView percent; //프로젝트 기여도
        TextView duration; //프로젝트 기간
        TextView oneline; //한줄평


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView); //view 에서 상속을 받았기 때문에 itemView에서 찾아야한다
            this.projectName = itemView.findViewById(R.id.textView18);
            this.percent = itemView.findViewById(R.id.textView19);
            this.duration = itemView.findViewById(R.id.textView20);
            this.oneline = itemView.findViewById(R.id.textView21);

        }
    }
}