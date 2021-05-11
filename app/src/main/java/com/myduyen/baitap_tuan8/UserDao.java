package com.myduyen.baitap_tuan8;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE uid LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
   User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE uid LIKE :id ")
   User findById(int id);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update(entity = User.class)
    void update(User... user);

}
