package com.example.midexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<StudentModel> studentList;
    private Context context;
    private DBHelper dbHelper;

    public StudentAdapter(List<StudentModel> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModel student = studentList.get(position);

        holder.etName.setText(student.getName());
        holder.etStudentId.setText(student.getStudentId());
        holder.cbEnrolled.setChecked(student.isEnrolled());

        // EDIT DATA
        holder.btnEdit.setOnClickListener(v -> {
            String updatedName = holder.etName.getText().toString().trim();
            String updatedStudentId = holder.etStudentId.getText().toString().trim();
            boolean updatedEnrolled = holder.cbEnrolled.isChecked();

            student.setName(updatedName);
            student.setStudentId(updatedStudentId);
            student.setEnrolled(updatedEnrolled);

            dbHelper.updateStudent(student);
            Toast.makeText(context, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        // DELETE DATA
        holder.btnDelete.setOnClickListener(v -> {
            dbHelper.deleteStudent(student.getId());
            studentList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, studentList.size());
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText etName, etStudentId;
        CheckBox cbEnrolled;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.etItemName);
            etStudentId = itemView.findViewById(R.id.etItemStudentId);
            cbEnrolled = itemView.findViewById(R.id.cbItemEnrolled);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}