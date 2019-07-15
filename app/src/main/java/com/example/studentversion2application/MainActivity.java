package com.example.studentversion2application;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studentversion2application.Adapters.CustomAdapter;
import com.example.studentversion2application.Model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Student> studentArrayList = new ArrayList<>();
    CustomAdapter adapter;
    private Uri imageUri;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.student_listview);

        adapter = new CustomAdapter(this, studentArrayList);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
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
//            startActivity(add);
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
            studentArrayList.add(student);
            adapter.notifyDataSetChanged();
        }else{

        }
    }
}
