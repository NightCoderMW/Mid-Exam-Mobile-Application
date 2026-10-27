package com.example.midexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etStudentId;
    private CheckBox cbEnrolled;
    private Button btnAdd, btnView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etStudentId = findViewById(R.id.etStudentId);
        cbEnrolled = findViewById(R.id.cbEnrolled);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String studentId = etStudentId.getText().toString().trim();
            boolean isEnrolled = cbEnrolled.isChecked();

            if (name.isEmpty() || studentId.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            StudentModel student = new StudentModel(name, studentId, isEnrolled);
            boolean isInserted = dbHelper.addStudent(student);

            if (isInserted) {
                Toast.makeText(MainActivity.this, "Student Added Successfully!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etStudentId.setText("");
                cbEnrolled.setChecked(false);
            } else {
                Toast.makeText(MainActivity.this, "Failed to Add Student", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewStudentActivity.class);
            startActivity(intent);
        });
    }
}