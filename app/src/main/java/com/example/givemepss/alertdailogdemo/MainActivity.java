package com.example.givemepss.alertdailogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button alertDialog1;
    private Button alertDialog2;
    private Button alertDialog3;
    private List<String> lunch;
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.alertdialog1:
                    setAlertDialog1Event();
                    break;
                case R.id.alertdialog2:
                    setAlertDialog2Event();
                    break;
                case R.id.alertdialog3:
                    setAlertDialog3Event();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lunch = new ArrayList<>();
        lunch.add(getString(R.string.lunch1));
        lunch.add(getString(R.string.lunch2));
        lunch.add(getString(R.string.lunch3));
        lunch.add(getString(R.string.lunch4));
        lunch.add(getString(R.string.lunch5));
        lunch.add(getString(R.string.lunch6));
        getButtonView();
        setButtonEvent();
    }

    public void getButtonView(){
        alertDialog1 = (Button)findViewById(R.id.alertdialog1);
        alertDialog2 = (Button)findViewById(R.id.alertdialog2);
        alertDialog3 = (Button)findViewById(R.id.alertdialog3);
    }

    public void setButtonEvent(){
        alertDialog1.setOnClickListener(buttonListener);
        alertDialog2.setOnClickListener(buttonListener);
        alertDialog3.setOnClickListener(buttonListener);
    }

    private void setAlertDialog1Event(){
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

    private void setAlertDialog3Event() {
        final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, null);
        new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.input_ur_name)
            .setView(item)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText editText = (EditText) item.findViewById(R.id.edittext);
                    Toast.makeText(getApplicationContext(), getString(R.string.hi) + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            })
            .show();
    }

    private void setAlertDialog2Event() {
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
}
