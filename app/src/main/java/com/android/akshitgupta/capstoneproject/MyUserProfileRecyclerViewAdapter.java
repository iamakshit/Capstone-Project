package com.android.akshitgupta.capstoneproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.akshitgupta.capstoneproject.object.UserProfile;
import com.android.akshitgupta.capstoneproject.object.UserProfile.User;

import java.util.List;

public class MyUserProfileRecyclerViewAdapter extends RecyclerView.Adapter<MyUserProfileRecyclerViewAdapter.MyViewHolder> {

    private List<User> userList;

    public MyUserProfileRecyclerViewAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_userprofile, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserProfile.User userProfile = userList.get(position);
        holder.nameView.setText(userProfile.getUserName());
        holder.cityView.setText(userProfile.getCityName());
        holder.dobView.setText(userProfile.getDobDate());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView, cityView, dobView;

        public MyViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_display);
            // nameView.setText(mItem.getUserName());
            dobView = (TextView) view.findViewById(R.id.dob_display);
            // dobView.setText(mItem.getDobDate());
            cityView = (TextView) view.findViewById(R.id.city_display);
            // cityView.setText(mItem.getCityName());
        }
    }
}