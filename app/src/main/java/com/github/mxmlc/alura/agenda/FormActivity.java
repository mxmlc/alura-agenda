package com.github.mxmlc.alura.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import commxmlc.github.agendaalura.R;

import com.github.mxmlc.alura.agenda.dao.StudentDAO;
import com.github.mxmlc.alura.agenda.helper.FormHelper;
import com.github.mxmlc.alura.agenda.model.Student;

public class FormActivity extends AppCompatActivity {

    private FormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helper = new FormHelper(this);

        // return the intent used to open the form
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra(Student.TABLE_NAME);
        if (student != null) {
            helper.fillForm(student);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_confirm:
                Student student = helper.getStudent();
                StudentDAO dao = new StudentDAO(this);
                if (student.getId() == null) {
                    dao.persist(student);
                } else {
                    dao.update(student);
                }
                dao.close();
                Toast.makeText(FormActivity.this, "Student " + student.getName() + " registered with success!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
