package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList contactName,Phoneno,Email;

    CustomAdapter(Context context,
                  ArrayList contactName,
                  ArrayList Phoneno,
                  ArrayList Email){
        this.context = context;
        this.contactName = contactName;
        this.Phoneno = Phoneno;
        this.Email = Email;
    }
    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.my_row,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {
    holder.contactname_txt.setText(String.valueOf(contactName.get(position)));
    holder.phone_txt.setText(String.valueOf(Phoneno.get(position)));
    holder.email_txt.setText(String.valueOf(Email.get(position)));
    }

    @Override
    public int getItemCount() {
        return contactName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contactname_txt , phone_txt,email_txt;
        public MyViewHolder( View itemView) {
            super(itemView);
            contactname_txt =itemView.findViewById(R.id.contactname_txt);
            phone_txt =itemView.findViewById(R.id.phone_txt);
            email_txt =itemView.findViewById(R.id.email_txt);
        }
    }
}
