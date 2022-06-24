package com.example.task_thedestroyers_android_v2;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.task_thedestroyers_android_v2.Model.toDoModel;

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

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }



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

        Bundle bundle = getArguments();

        if (bundle != null){
            isUpdate = true;

            String task = bundle.getString("task");

            mEditText.setText(task);

            if (task.length()>0){
                mSaveButton.setEnabled(false);
            }
        }

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().equals("")){
                        mSaveButton.setEnabled(false);
                        mSaveButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    }
                    else {
                        mSaveButton.setEnabled(true);
                        mSaveButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        boolean finalIsUpdate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEditText.getText().toString();

                if (finalIsUpdate){
                    myDb.updateTask(bundle.getInt("id"), text);
                }
                else {
                    toDoModel item = new toDoModel();
                    item.setTask(text);
                    item.setStatus(0);

                    myDb.insertTask(item);



                }
                dismiss();
                //To dismiss current fragment


            }
        });






    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
    }
}
