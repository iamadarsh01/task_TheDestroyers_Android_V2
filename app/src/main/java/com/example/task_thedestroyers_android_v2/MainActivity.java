package com.example.task_thedestroyers_android_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.task_thedestroyers_android_v2.Adapter.ToDoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.task_thedestroyers_android_v2.utils.databaseHelper;
import com.example.task_thedestroyers_android_v2.Model.toDoModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements onDialogCloseListener {

    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private databaseHelper myDB;

    private List<toDoModel> mList;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.RecyclerView);
        fab = findViewById(R.id.fab);
        myDB = new databaseHelper(MainActivity.this);

        mList =  new ArrayList<>();
        adapter = new ToDoAdapter(myDB, MainActivity.this);

        mList = myDB.getAllTasks();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);


        Collections.reverse(mList);
        adapter.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);

            }
        });




    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();

        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();


    }
}