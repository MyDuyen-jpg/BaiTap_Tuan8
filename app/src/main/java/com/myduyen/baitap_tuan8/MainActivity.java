package com.myduyen.baitap_tuan8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListner{

    RecyclerView rcv_name;
    CustomAdapterRecycler adt;
    List<User> mNames;
    Button btnSave,btnAdd;
    EditText tvSearch;
    AppDatabase db;
    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNames = new ArrayList<>();
        rcv_name = findViewById(R.id.rcv_name);
        tvSearch = findViewById(R.id.tvSearch);
        btnAdd = findViewById(R.id.btnAdd);


        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();
        userDao = db.userDao();
        mNames= userDao.getAll();


        adt = new CustomAdapterRecycler(mNames,this);
        rcv_name.setHasFixedSize(true);
        rcv_name.setAdapter(adt);
        rcv_name.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                String name=tvSearch.getText().toString();
                for (User user:userDao.getAll()){
                    i++;
                }
                userDao.insertAll(new User(i++,name,""));
                List<User> users =  userDao.getAll();
                adt.resetList(users);
                tvSearch.setText("");
            }
        });

    }

    @Override
    public void itemClicklistener(User user) {
        tvSearch.setText(user.getFirstName()+"");


    }

    @Override
    public void buttonxoaClick(User user) {
        userDao.delete(user);
        List<User> users =  userDao.getAll();
        adt.resetList(users);
        tvSearch.setText("");

    }

    @Override
    public void buttonsuaClick(User user) {
        String name = tvSearch.getText().toString();
        user.setFirstName(name);
        userDao.update(user);
        List<User> users =  userDao.getAll();
        adt.resetList(users);
        tvSearch.setText("");







    }
}