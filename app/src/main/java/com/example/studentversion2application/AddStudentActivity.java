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
    private int pos;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //
        String [] courselist = this.getResources().getStringArray(R.array.course_list);

        //
        studImage = (ImageView) findViewById(R.id.addstudentimage);
        lastname = (EditText) findViewById(R.id.editTextLastname);
        firstname = (EditText) findViewById(R.id.editTextFirstname);
        course = (Spinner) findViewById(R.id.spinnerCourse);
        btnsave = (Button) findViewById(R.id.btn_save);
        btncancel = (Button) findViewById(R.id.btn_cancel);

        //adding an event listeners to the elements
        studImage.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        course.setOnItemSelectedListener(this);

        //checking if there is an edit action invoked
        Bundle b = this.getIntent().getExtras();
        if(b != null){
            Uri image = b.getParcelable("image");
            String lastname = b.getString("lastname");
            String firstname = b.getString("firstname");
            String newcourse = b.getString("course");

                for(int i=0; i< courselist.length; i++)
                    if (courselist[i].equals(newcourse)){
                        pos=i;
                        break;
                    }
                //
                    this.studImage.setImageURI(image);
                    this.lastname.setText(lastname);
                    this.firstname.setText(firstname);
                    this.course.setSelection(pos);
        }
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
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
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
