package com.binodcoder.sharedpreferencesdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.service.autofill.Validators.or;

public class MainActivity extends AppCompatActivity {
    TextView defaultLanguage;
    Button language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultLanguage=findViewById(R.id.tv_language);
        language=findViewById(R.id.btn_language);

        SharedPreferences sharedPreferences=this.getSharedPreferences("com.binodcoder.sharedpreferencesdemo", Context.MODE_PRIVATE);
        String chosenLanguage=sharedPreferences.getString("language", "");
        if(chosenLanguage==""){
           showAlert(sharedPreferences);
          }else{
            defaultLanguage.setText(chosenLanguage);
        }

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(sharedPreferences);
            }
        });
     }

    public void showAlert(SharedPreferences sharedPreferences){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Language")
                .setMessage("Choose your default language")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("language", "English").apply();
                        defaultLanguage.setText("English");
                       }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("language", "Spanish").apply();
                        defaultLanguage.setText("Spanish");
                      }
                })
                .show();
    }
}