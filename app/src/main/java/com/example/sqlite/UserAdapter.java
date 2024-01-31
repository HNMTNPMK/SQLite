package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.giaodien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.idTextView.setText("ID: " + user.getId());
        holder.emailTextView.setText("Email: " + user.getEmail());
        holder.passwordTextView.setText("Password: " + user.getPassword());
        holder.roleTextView.setText("Role: " + user.getRole());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView emailTextView;
        public TextView passwordTextView;
        public TextView roleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            passwordTextView = itemView.findViewById(R.id.passwordTextView);
            roleTextView = itemView.findViewById(R.id.roleTextView);
        }
    }
}