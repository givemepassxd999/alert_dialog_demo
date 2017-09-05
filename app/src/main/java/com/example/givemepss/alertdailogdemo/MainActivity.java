package com.example.givemepss.alertdailogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button normalDialog;
    private Button listDialog;
    private Button singleDialog;
    private Button multiDialog;
    private Button customDialog;
    private List<String> lunch;
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.normal_dialog:
                    normalDialogEvent();
                    break;
                case R.id.list_dialog:
                    listDialogEvent();
                    break;
                case R.id.single_choice:
                    singleDialogEvent();
                    break;
                case R.id.multi_check:
                    multiDialogEvent();
                    break;
                case R.id.custom_dialog:
                    customDialogEvent();
                    break;
            }
        }
    };
    private int singleChoiceIndex;
    private int checkedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        lunch = new ArrayList<>();
        lunch.add(getString(R.string.lunch1));
        lunch.add(getString(R.string.lunch2));
        lunch.add(getString(R.string.lunch3));
        lunch.add(getString(R.string.lunch4));
        lunch.add(getString(R.string.lunch5));
        lunch.add(getString(R.string.lunch6));
    }

    public void initView(){
        normalDialog = (Button) findViewById(R.id.normal_dialog);
        listDialog = (Button) findViewById(R.id.list_dialog);
        singleDialog = (Button) findViewById(R.id.single_choice);
        multiDialog = (Button) findViewById(R.id.multi_check);
        customDialog = (Button) findViewById(R.id.custom_dialog);
        setButtonEvent();
    }

    public void setButtonEvent(){
        normalDialog.setOnClickListener(buttonListener);
        listDialog.setOnClickListener(buttonListener);
        singleDialog.setOnClickListener(buttonListener);
        multiDialog.setOnClickListener(buttonListener);
        customDialog.setOnClickListener(buttonListener);
    }

    private void normalDialogEvent(){
        new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.lunch_time)
            .setMessage(R.string.want_to_eat)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), R.string.gogo, Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton(R.string.wait_a_minute, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), R.string.i_am_hungry, Toast.LENGTH_SHORT).show();
                }
            })
            .setNeutralButton(R.string.not_hungry, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), R.string.diet, Toast.LENGTH_SHORT).show();
                }
            })
            .show();
    }

    private void customDialogEvent() {
        final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, null);
        new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.input_ur_name)
            .setView(item)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText editText = (EditText) item.findViewById(R.id.edit_text);
                    String name = editText.getText().toString();
                    if(TextUtils.isEmpty(name)){
                        Toast.makeText(getApplicationContext(), R.string.input_ur_name, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.hi) + name, Toast.LENGTH_SHORT).show();
                    }
                }
            })
            .show();
    }

    private void listDialogEvent() {
        new AlertDialog.Builder(MainActivity.this)
            .setItems(lunch.toArray(new String[lunch.size()]), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = lunch.get(which);
                    Toast.makeText(getApplicationContext(), getString(R.string.u_eat) + name, Toast.LENGTH_SHORT).show();
                }
            })
            .show();
    }

    private void singleDialogEvent(){
        new AlertDialog.Builder(MainActivity.this)
            .setSingleChoiceItems(lunch.toArray(new String[lunch.size()]), singleChoiceIndex,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        singleChoiceIndex = which;
                    }
                })
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "你選擇的是"+lunch.get(singleChoiceIndex), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            })
            .show();
    }

    private void multiDialogEvent(){
        final List<Boolean> checkedStatusList = new ArrayList<>();
        for(String s : lunch){
            checkedStatusList.add(false);
        }
        new AlertDialog.Builder(MainActivity.this)
            .setMultiChoiceItems(lunch.toArray(new String[lunch.size()]), new boolean[lunch.size()],
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedStatusList.set(which, isChecked);
                    }
                })
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    StringBuilder sb = new StringBuilder();
                    boolean isEmpty = true;
                    for(int i = 0; i < checkedStatusList.size(); i++){
                        if(checkedStatusList.get(i)){
                            sb.append(lunch.get(i));
                            sb.append(" ");
                            isEmpty = false;
                        }
                    }
                    if(!isEmpty){
                        Toast.makeText(MainActivity.this, "你選擇的是"+sb.toString(), Toast.LENGTH_SHORT).show();
                        for(String s : lunch){
                            checkedStatusList.add(false);
                        }
                    } else{
                        Toast.makeText(MainActivity.this, "請勾選項目", Toast.LENGTH_SHORT).show();
                    }

                }
            })
            .show();
    }
}
