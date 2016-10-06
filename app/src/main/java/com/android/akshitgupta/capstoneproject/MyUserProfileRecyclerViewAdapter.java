package com.android.akshitgupta.capstoneproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.akshitgupta.capstoneproject.data.UserContract;
import com.android.akshitgupta.capstoneproject.object.UserProfile;
import com.android.akshitgupta.capstoneproject.object.UserProfile.User;

import java.util.List;

public class MyUserProfileRecyclerViewAdapter extends RecyclerView.Adapter<MyUserProfileRecyclerViewAdapter.MyViewHolder> {

    private List<User> userList;
    private Context context;
    private MyUserProfileRecyclerViewAdapter adapter;


    public MyUserProfileRecyclerViewAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
        this.adapter = this;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_userprofile, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final UserProfile.User userProfile = userList.get(position);
        holder.nameView.setText(userProfile.getUserName());
        holder.cityView.setText(userProfile.getCityName());
        holder.dobView.setText(userProfile.getDobDate());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userList.remove(position);
                context.getContentResolver().delete(UserContract.UserEntry.CONTENT_URI, UserContract.UserEntry._ID + "=?", new String[]{userProfile.getId().toString()});
                Toast.makeText(view.getContext(), "Successfully deleted the item", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, AddUserActivity.class);
                intent.putExtra("userProfile",userProfile   );
                context.startActivity(intent);
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView, cityView, dobView;
        public ImageButton deleteButton, editButton;
        public Button markDefaultButton;

        public MyViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_display);
            dobView = (TextView) view.findViewById(R.id.dob_display);
            cityView = (TextView) view.findViewById(R.id.city_display);
            deleteButton = (ImageButton) view.findViewById(R.id.delete_button);
            editButton = (ImageButton) view.findViewById(R.id.edit_button);
        }
    }
}