package com.example.studentversion2application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentversion2application.Adapters.CustomAdapter;
import com.example.studentversion2application.Model.Student;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Student> studentArrayList = new ArrayList<>();
    ArrayList<Student> findlist = new ArrayList<>();

    CustomAdapter adapter;
    private Uri imageUri;

    ListView lv;
    AlertDialog.Builder show_builder;
    AlertDialog dialog;

    LinearLayout layout;
    ImageView imageView;
    TextView stud_lname, stud_fname, stud_course;

    AdapterView.AdapterContextMenuInfo info;

    //
    EditText txtsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.student_listview);
        txtsearch = (EditText) findViewById(R.id.textsearch);

        adapter = new CustomAdapter(this, studentArrayList);
        lv.setAdapter(adapter);
        final CustomAdapter mAdapter = new CustomAdapter(this, findlist);

        registerForContextMenu(lv);
        lv.setOnItemClickListener(this);

        //
        show_builder = new AlertDialog.Builder(this);

        txtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                findlist.clear();

                String s1 = s.toString();
                Pattern pattern = Pattern.compile(s1);
                    for (int i=0; i<studentArrayList.size(); i++){
                        String find = studentArrayList.get(i).getStudfname().toLowerCase();
                        Matcher matcher = pattern.matcher(find);
                        if(matcher.find()){
                            findlist.add(studentArrayList.get(i));
                            lv.setAdapter(mAdapter);
                            adapter.notifyDataSetChanged();
                        }//end if
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //for menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }else if(id == R.id.action_add){
            Intent add = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(add, 0);
        }
        return super.onOptionsItemSelected(item);
    }
    //inflate the menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //handles the onclick listener for the listview
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            Bundle b = data.getExtras();
            imageUri = b.getParcelable("image");
            String lastname = b.getString("lastname");
            String firstname = b.getString("firstname");
            String course = b.getString("course");

            Student student = new Student(imageUri, lastname, firstname, course);
            //
            switch(requestCode){
                case 0: //request for adding student information
                    studentArrayList.add(student);
                    lv.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), "New student successfully added!", Toast.LENGTH_SHORT).show();
                    break;
                case 1: //request for editing student information
                    studentArrayList.set(info.position, student);
                    Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show();
                    break;
            }
            adapter.notifyDataSetChanged();

        }else{

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.getMenuInflater().inflate(R.menu.contextmenu, menu);
        info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(studentArrayList.get(info.position).getStudlname()+","
                            +studentArrayList.get(info.position).getStudfname());
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.show: //display an alert dialog box
                this.imageView = new ImageView(this);
                this.stud_lname = new TextView(this);
                this.stud_fname = new TextView(this);
                this.stud_course = new TextView(this);

                this.imageView.setImageURI(studentArrayList.get(info.position).getUriImage());
                this.show_builder.setTitle(studentArrayList.get(info.position).getStudlname()+", "
                        +studentArrayList.get(info.position).getStudfname());

                this.layout = new LinearLayout(this);
                this.layout.setOrientation(LinearLayout.VERTICAL);
                this.layout.addView(imageView);
                this.layout.addView(stud_lname);
                this.layout.addView(stud_fname);
                this.layout.addView(stud_course);

                this.show_builder.setView(layout);
                this.show_builder.setNeutralButton("OKAY", null);

                this.dialog = this.show_builder.create();
                this.dialog.show();
                break;
            case R.id.edit: //edit student information by calling the AddStudentActivity
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                Student student = studentArrayList.get(info.position);
                intent.putExtra("image", student.getUriImage());
                intent.putExtra("lastname", student.getStudlname());
                intent.putExtra("firstname", student.getStudfname());
                intent.putExtra("course", student.getStudcourse());
                this.startActivityForResult(intent, 1);
                break;
            case R.id.delete: //delete student information
                this.studentArrayList.remove(info.position);
                this.adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
                Toast.makeText(this, "Student deleted!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }



}
