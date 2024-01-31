package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "qluser";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_EMAIL, KEY_PASSWORD, KEY_ROLE);
        db.execSQL(create_users_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_users_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_users_table);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_ROLE, user.getRole());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public User getUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[]{String.valueOf(userId)},
                null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getInt(0), cursor.getString(1),
                cursor.getInt(2), cursor.getString(3));
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = String.format("SELECT * FROM  %s", TABLE_NAME );
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = new User(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3));
            userList.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        if (userList.size()==0){
            userList.add(new User(1,"mail@gmail.com",234567867,"admin"));
        }
        return userList;
    }
}