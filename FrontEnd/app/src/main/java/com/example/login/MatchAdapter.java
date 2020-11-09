package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Match.MatchUser;

import java.util.List;

public class MatchAdapter extends  RecyclerView.Adapter<MatchAdapter.ViewHolder>{


    private Context context;
    private List<MatchUser> list;

    public MatchAdapter (Context context, List<MatchUser> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_match, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchUser user = list.get(position);
        holder.textName.setText(user.getFirstName());
        holder.textEmail.setText(user.getEmailaddress());
        holder.textGender.setText(user.getGender());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textEmail, textGender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.first_name);
            textEmail = itemView.findViewById(R.id.emailaddress);
            textGender = itemView.findViewById(R.id.gender);

        }
    }
}
