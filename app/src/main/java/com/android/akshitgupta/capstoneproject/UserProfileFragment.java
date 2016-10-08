package com.android.akshitgupta.capstoneproject;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.data.UserContract;
import com.android.akshitgupta.capstoneproject.object.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class UserProfileFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    MyUserProfileRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserProfileFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserProfileFragment newInstance(int columnCount) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("UserProfileFragment","Inside onCreate method");

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("UserProfileFragment","Inside onCreateView");
        View view = inflater.inflate(R.layout.fragment_userprofile_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Log.i("UserProfileFragment","Inside recylerview");
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            Cursor userCursor = getContext().getContentResolver().query(
                    UserContract.UserEntry.CONTENT_URI,
                    new String[]{UserContract.UserEntry._ID, UserContract.UserEntry.COLUMN_USER_NAME, UserContract.UserEntry.COLUMN_USER_GENDER,
                            UserContract.UserEntry.COLUMN_USER_DOB_DATE, UserContract.UserEntry.COLUMN_USER_DOB_TIME, UserContract.UserEntry.COLUMN_CITY_NAME
                    }, null, null, null);

            Log.i("UserProfileFragment","userCursor  = "+userCursor.getCount());
            List<UserProfile.User> userList= new ArrayList<>();
            if (userCursor.moveToFirst()){
                do{
                    UserProfile.User user = new UserProfile.User();
                    String userName = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_NAME));
                    String userGender = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_GENDER));
                    String userDobDate = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_DOB_DATE));
                    String userDobTime = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_DOB_TIME));
                    String userCity = userCursor.getString(userCursor.getColumnIndex(UserContract.UserEntry.COLUMN_CITY_NAME));
                    Integer id = userCursor.getInt(userCursor.getColumnIndex(UserContract.UserEntry._ID));

                    user.setUserName(userName);
                    user.setUserGender(userGender);
                    user.setDobDate(userDobDate);
                    user.setDobTIme(userDobTime);
                    user.setCityName(userCity);
                    user.setId(id);
                   // Log.i("UserProfileFragment","User added ==>"+user.toString());
                    userList.add(user);
                    // do what ever you want here
                }while(userCursor.moveToNext());
            }
            userCursor.close();
            adapter = new MyUserProfileRecyclerViewAdapter(userList,getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

        }
        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(UserProfile.User item);
    }
}
