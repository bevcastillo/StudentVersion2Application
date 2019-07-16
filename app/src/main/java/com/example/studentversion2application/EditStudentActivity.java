package com.example.studentversion2application;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.studentversion2application.Model.Student;

import java.util.ArrayList;

public class EditStudentActivity extends AppCompatActivity {

    private Student student;
    ArrayList<Student> list;
    EditText edit_lname, edit_fname;
    String edit_selectedcourse;
    Spinner edit_course;
    ImageView edit_image;
    Uri edit_imageUri;
    Button edit_btnsave, edit_btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        ///
        edit_lname = (EditText) findViewById(R.id.edit_editTextLastname);
        edit_fname = (EditText) findViewById(R.id.edit_editTextFirstname);
        edit_course = (Spinner) findViewById(R.id.edit_spinnerCourse);
        edit_btnsave = (Button)findViewById(R.id.edit_btn_save);
        edit_btnsave = (Button) findViewById(R.id.edit_btn_cancel);
        edit_image = (ImageView) findViewById(R.id.editstudentimage);

        ///
    }
}
