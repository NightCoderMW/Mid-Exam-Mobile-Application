package com.example.midexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "student_db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "students";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_ENROLLED = "is_enrolled";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_STUDENT_ID + " TEXT, "
                + COL_ENROLLED + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // ADD
    public boolean addStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, student.getName());
        cv.put(COL_STUDENT_ID, student.getStudentId());
        cv.put(COL_ENROLLED, student.isEnrolled() ? 1 : 0);

        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    // VIEW
    public List<StudentModel> getAllStudents() {
        List<StudentModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String studentId = cursor.getString(2);
                boolean isEnrolled = cursor.getInt(3) == 1;

                list.add(new StudentModel(id, name, studentId, isEnrolled));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // EDIT / UPDATE
    public void updateStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, student.getName());
        cv.put(COL_STUDENT_ID, student.getStudentId());
        cv.put(COL_ENROLLED, student.isEnrolled() ? 1 : 0);

        db.update(TABLE_NAME, cv, COL_ID + " = ?", new String[]{String.valueOf(student.getId())});
    }

    // DELETE
    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }
}