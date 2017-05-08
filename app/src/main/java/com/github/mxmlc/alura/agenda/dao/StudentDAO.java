package com.github.mxmlc.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.github.mxmlc.alura.agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends SQLiteOpenHelper {

    public StudentDAO(Context context) {
        super(context, "students.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student (id INTEGER PRIMARY KEY, name TEXT NOT NULL, address TEXT, telephone TEXT, site TEXT, email TEXT, grade REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS student;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void persist(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = getStudentData(student);
        db.insert(Student.TABLE_NAME, null, cv);
    }

    public List<Student> fetchAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM student", null);
        List<Student> students = new ArrayList<Student>();
        while (c.moveToNext()) {
            Student student = new Student();
            student.setId(c.getLong(c.getColumnIndex(Student.ID_COLUMN)));
            student.setName(c.getString(c.getColumnIndex(Student.NAME_COLUMN)));
            student.setAddress(c.getString(c.getColumnIndex(Student.ADDRESS_COLUMN)));
            student.setTelephone(c.getString(c.getColumnIndex(Student.TELEPHONE_COLUMN)));
            student.setSite(c.getString(c.getColumnIndex(Student.SITE_COLUMN)));
            student.setEmail(c.getString(c.getColumnIndex(Student.EMAIL_COLUMN)));
            student.setGrade(c.getDouble(c.getColumnIndex(Student.GRADE_COLUMN)));
            students.add(student);
        }
        c.close();
        return students;
    }

    public void remove(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = { student.getId().toString() };
        db.delete(Student.TABLE_NAME, "id = ?", params);
    }

    public void update(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = getStudentData(student);
        String[] params = { student.getId().toString() };
        db.update(Student.TABLE_NAME, cv, "id = ?", params);
    }

    @NonNull
    private ContentValues getStudentData(Student student) {
        ContentValues cv = new ContentValues();
        cv.put(Student.NAME_COLUMN, student.getName());
        cv.put(Student.ADDRESS_COLUMN, student.getAddress());
        cv.put(Student.TELEPHONE_COLUMN, student.getTelephone());
        cv.put(Student.SITE_COLUMN, student.getSite());
        cv.put(Student.EMAIL_COLUMN, student.getEmail());
        cv.put(Student.GRADE_COLUMN, student.getGrade());
        return cv;
    }
}
