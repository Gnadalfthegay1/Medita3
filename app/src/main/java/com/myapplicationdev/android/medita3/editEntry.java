package com.myapplicationdev.android.medita3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.myapplicationdev.android.medita3.ui.home.HomeFragment;

public class editEntry extends AppCompatActivity {
    TextView tvDate;
    EditText etEntry;
    Button btDel, btEdit;
    dbJournals db;
    int id;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        setTitle("Edit Entry");
        tvDate = findViewById(R.id.date);
        etEntry = findViewById(R.id.entry);
        btDel = findViewById(R.id.delete);
        btEdit = findViewById(R.id.editButton);
        db = new dbJournals(getApplicationContext());
        in = getIntent();
        tvDate.setText(in.getStringExtra("date"));
        etEntry.setText(in.getStringExtra("entry"));
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final HomeFragment myFragment = new HomeFragment();
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteHandler(tvDate.getText().toString());
                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateHandler(tvDate.getText().toString(), etEntry.getText().toString());
                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
