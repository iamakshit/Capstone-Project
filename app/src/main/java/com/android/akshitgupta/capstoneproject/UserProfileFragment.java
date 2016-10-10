package com.android.akshitgupta.capstoneproject;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.akshitgupta.capstoneproject.adapter.MyUserProfileRecyclerViewAdapter;
import com.android.akshitgupta.capstoneproject.data.UserContract;
import com.android.akshitgupta.capstoneproject.object.User;

import java.util.ArrayList;
import java.util.List;

public class UserProfileFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    //  public static String TAG = UserProfileFragment.class.getSimpleName();

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    MyUserProfileRecyclerViewAdapter adapter;
    private static final int LOADER_ID = 0x01;
    List<User> userList = new ArrayList<>();
    RecyclerView recyclerView;

    public UserProfileFragment() {
    }

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
        //Log.i(TAG,"Inside onCreate method");
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  Log.i(TAG,"Inside onCreateView");
        View view = inflater.inflate(R.layout.fragment_userprofile_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            //  Log.i("UserProfileFragment","Inside recylerview");
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(adapter);

        }
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), UserContract.UserEntry.CONTENT_URI,
                new String[]{UserContract.UserEntry._ID, UserContract.UserEntry.COLUMN_USER_NAME, UserContract.UserEntry.COLUMN_USER_GENDER,
                        UserContract.UserEntry.COLUMN_USER_DOB_DATE, UserContract.UserEntry.COLUMN_USER_DOB_TIME, UserContract.UserEntry.COLUMN_CITY_NAME
                }, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor userCursor) {
        if (userCursor.moveToFirst()) {
            do {
                User user = new User();
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
            } while (userCursor.moveToNext());
        }
        userCursor.close();
        adapter = new MyUserProfileRecyclerViewAdapter(userList, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        this.userList = new ArrayList<>();

    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(User item);
    }
}
