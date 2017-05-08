package com.github.mxmlc.alura.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mxmlc.alura.agenda.dao.StudentDAO;
import com.github.mxmlc.alura.agenda.model.Student;

import java.util.List;

import commxmlc.github.agendaalura.R;

public class ListStudentsActivity extends AppCompatActivity {

    private ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        addAdctions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchStudents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) studentsList.getItemAtPosition(info.position);

                StudentDAO dao = new StudentDAO(ListStudentsActivity.this);
                dao.remove(student);
                dao.close();

                fetchStudents();
                return false;
            }
        });
    }

    private void fetchStudents() {
        StudentDAO dao = new StudentDAO(this);
        List<Student> students = dao.fetchAll();
        dao.close();
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        studentsList.setAdapter(adapter);
    }

    private void addAdctions() {
        studentsList = (ListView) findViewById(R.id.students_list);

        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View item, int position, long id) {
                Student student = (Student) studentsList.getItemAtPosition(position);
                Intent intentGoToEdit = new Intent(ListStudentsActivity.this, FormActivity.class);
                intentGoToEdit.putExtra(Student.TABLE_NAME, student);
                startActivity(intentGoToEdit);
             }
        });

        // to test long click item, uncomment below
        /*
        studentsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListStudentsActivity.this, "Long click", Toast.LENGTH_SHORT).show();
                // if true, the event will not be propagated so the context menu will not be displayed
                return true;
            }
        });
        */

        Button btAdd = (Button) findViewById(R.id.add_student);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToForm = new Intent(ListStudentsActivity.this, FormActivity.class);
                startActivity(intentGoToForm);
            }
        });

        registerForContextMenu(studentsList);
    }

}
