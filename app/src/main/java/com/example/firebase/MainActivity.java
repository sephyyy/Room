package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    TextView tvRead;
    EditText etName;
    Button showBtn;
    Button deleteBtn;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.addBtn);
        tvRead = findViewById(R.id.readTV);
        etName = findViewById(R.id.etName);
        showBtn = findViewById(R.id.showBtn);
        deleteBtn = findViewById(R.id.deleteBtn);


        db = AppDatabase.getInstance(this);

        addBtn.setOnClickListener(v->{
            db.userDao().insert(new User(etName.getText().toString()));
        });

        showBtn.setOnClickListener(v->{
            List<User> g = db.userDao().getAll();
            String concat = "";
            for(int i = 0; i<g.size(); i++){
                concat += g.get(i).firstName + " ";
            }
            tvRead.setText(concat);
        });

        deleteBtn.setOnClickListener(v->{
            db.userDao().delet(new User(etName.getText().toString()));
            Log.d("TAG123av", "onCreate: "+etName.getText().toString());
        });

    }

}