package com.example.studentversion2application.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentversion2application.Model.Student;
import com.example.studentversion2application.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    //data container
    ArrayList<Student> list;
    LayoutInflater inflater;

    //contructor


    public CustomAdapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_layout, parent, false);
            holder.iv = (ImageView) convertView.findViewById(R.id.imageView);
            holder.lname = (TextView) convertView.findViewById(R.id.textLastname);
            holder.fname= (TextView) convertView.findViewById(R.id.textFirstname);
            holder.course = (TextView) convertView.findViewById(R.id.textCourse);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //inflate
        holder.iv.setImageURI(list.get(position).getUriImage());
        holder.lname.setText(list.get(position).getStudlname());
        holder.fname.setText(list.get(position).getStudfname());
        holder.course.setText(list.get(position).getStudcourse());

        return convertView;
    }

    //creating a static class
    static class ViewHolder{
        ImageView iv;
        TextView lname, fname,course;
    }
}
