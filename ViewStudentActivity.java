package com.example.midexam;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_student);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        List<StudentModel> studentList = dbHelper.getAllStudents();

        if (studentList.size() > 0) {
            StudentAdapter adapter = new StudentAdapter(studentList, this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No Student Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}