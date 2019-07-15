package com.example.studentversion2application;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentversion2application.Adapters.CustomAdapter;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ListView lv;
    ImageView studImage;
    Uri studImageUri;
    EditText lastname, firstname;
    String selectedCourse;
    Spinner course;
    Button btnsave, btncancel;
    CustomAdapter adapter;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //
        studImage = (ImageView) findViewById(R.id.addstudentimage);
        lastname = (EditText) findViewById(R.id.editTextLastname);
        firstname = (EditText) findViewById(R.id.editTextFirstname);
        course = (Spinner) findViewById(R.id.spinnerCourse);
        btnsave = (Button) findViewById(R.id.btn_save);
        btncancel = (Button) findViewById(R.id.btn_cancel);

        studImage.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        course.setOnItemSelectedListener(this);
    }


    //on click listeners for the buttons and imageview
    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.addstudentimage:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
                break;
            case R.id.btn_save:
                String lname = lastname.getText().toString();
                String fname = firstname.getText().toString();
                String newCourse = course.getSelectedItem().toString();

                if(!studImage.equals(R.drawable.user) && !lastname.equals(" ") && !firstname.equals("") && !course.getSelectedItem().toString().trim().equals(0)){
                    Intent intent = new Intent(); //blind intent

                    intent.putExtra("image", this.studImageUri);
                    intent.putExtra("lastname", lname);
                    intent.putExtra("firstname", fname);
                    intent.putExtra("course", newCourse);

                    this.setResult(Activity.RESULT_OK, intent);
                    Toast.makeText(getApplicationContext(), "New student successfully added!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Error in adding a new student!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                studImage.setImageResource(R.drawable.user);
                lastname.setText("");
                firstname.setText("");
                course.setSelection(0);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != 0){
            if(data != null){
                studImageUri = data.getData();
                studImage.setImageURI(studImageUri);
            }
        }else {

        }
    }

    //on click listeners for the spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int sid = parent.getId();

        switch (sid){
            case R.id.spinnerCourse:
                selectedCourse = this.course.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
