package com.example.papbb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class dataDiri extends AppCompatActivity {

    private RadioGroup list_opsi;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_datadiri);
            list_opsi = findViewById(R.id.opsi);
            list_opsi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int id) {
                    switch (id){
                        case R.id.laki:
                            Toast.makeText(getApplication(), "Laki-Laki", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.perempuan:
                            Toast.makeText(getApplication(), "Perempuan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

    }
}