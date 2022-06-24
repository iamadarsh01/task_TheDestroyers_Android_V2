package com.example.task_thedestroyers_android_v2.Adapter;



import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_thedestroyers_android_v2.utils.databaseHelper;


import com.example.task_thedestroyers_android_v2.MainActivity;
import com.example.task_thedestroyers_android_v2.R;
import com.example.task_thedestroyers_android_v2.Model.toDoModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<toDoModel> mList;
    private MainActivity activity;
    private databaseHelper myDB;

    public ToDoAdapter(databaseHelper myDB, MainActivity activity ){
        this.activity = activity;
        this.myDB = myDB;
    }






    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(V);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final toDoModel item = mList.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    myDB.updateStatus(item.getId(), 1);
                }
                else myDB.updateStatus(item.getId(), 0);

            }
        });

    }

    public boolean toBoolean(int num){
        return  num != 0;
    }

    public Context getContext(){
        return activity;
    }

    public void setTasks(List<toDoModel>mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public  void deleteTask(int position){
        toDoModel item = mList.get(position);
        myDB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        toDoModel item  = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        //***************


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox mCheckBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.taskCheckBox);
        }
    }
}
