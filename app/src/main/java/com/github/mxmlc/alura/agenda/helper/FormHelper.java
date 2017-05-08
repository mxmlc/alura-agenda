package com.github.mxmlc.alura.agenda.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import com.github.mxmlc.alura.agenda.FormActivity;
import commxmlc.github.agendaalura.R;
import com.github.mxmlc.alura.agenda.model.Student;

public class FormHelper {

    private EditText name;
    private EditText address;
    private EditText telephone;
    private EditText site;
    private EditText email;
    private RatingBar grade;

    private Student student;

    public FormHelper(FormActivity activity) {
        name = (EditText) activity.findViewById(R.id.form_name);
        address = (EditText) activity.findViewById(R.id.form_address);
        telephone = (EditText) activity.findViewById(R.id.form_telephone);
        site = (EditText) activity.findViewById(R.id.form_site);
        email = (EditText) activity.findViewById(R.id.form_email);
        grade = (RatingBar) activity.findViewById(R.id.form_grade);
        student = new Student();
    }

    public Student getStudent() {
        student.setName(name.getText().toString());
        student.setAddress(address.getText().toString());
        student.setTelephone(telephone.getText().toString());
        student.setSite(site.getText().toString());
        student.setEmail(email.getText().toString());
        student.setGrade(Double.valueOf(grade.getProgress()));

        return student;
    }

    public void fillForm(Student student) {
        name.setText(student.getName());
        address.setText(student.getAddress());
        telephone.setText(student.getTelephone());
        site.setText(student.getSite());
        email.setText(student.getEmail());
        grade.setProgress(student.getGrade().intValue());
        this.student = student;
    }

}
