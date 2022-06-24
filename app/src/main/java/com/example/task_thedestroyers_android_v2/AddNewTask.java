package com.example.task_thedestroyers_android_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.task_thedestroyers_android_v2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.example.task_thedestroyers_android_v2.utils.databaseHelper;

public class AddNewTask extends BottomSheetDialogFragment {

    private  static  final  String TAG = "com.example.task_thedestroyers_android_v2.AddNewTask";

    //widgets
    private EditText mEditText;
    private Button mSaveButton;

    private databaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View v = inflater.inflate(R.layout.add_new_task, container, false);
return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.addTaskEditText);
        mSaveButton = view.findViewById(R.id.buttonSave);

        myDb = new databaseHelper(getActivity());

        boolean isUpdate = false;






    }
}
