package com.example.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // Database Version
    private static final int DATABASE_VERSION = 9;
    // Database Name
    private static final String DATABASE_NAME = "Manager";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE Users( UserID INTEGER PRIMARY KEY, UserLogin TEXT,UserPassword TEXT," + " UserFirstName TEXT,UserLastName TEXT, UserEmail TEXT,UserBirthday TEXT, UserGender TEXT,UserPhoneNumber TEXT,UserQuiz TEXT,UserBio TEXT)";
        db.execSQL(script);
        script = "CREATE TABLE Quiz( QuizID INTEGER PRIMARY KEY, QuizText TEXT)";
        db.execSQL(script);
        fillDefaultData(db);
    }

    private void fillDefaultData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("UserLogin", "Admin");
        values.put("UserPassword", "password");
        // Inserting Row
        db.insert("Users", null, values);
        String[] questions = new String[]{"I am full of energy", "I can get blue/depressed", "I am quiet", "I am compassionate and have a soft heart", "I can be rude to others", "I am fascinated by art and literature", "I have few artistic interests", "I prefer to have others take charge", "I am dominate and act as a leader", "I am reliable and can always be counted on", "I am original and come up with new ideas"};
        for (String s : questions) {
            ContentValues values0 = new ContentValues();
            values0.put("QuizText", s);
            db.insert("Quiz", null, values0);
        }
        // Closing database connection
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Quiz");
        // Create tables again
        onCreate(db);
    }

    public boolean checkUser(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT UserID FROM  Users WHERE (UserLogin = '" + login + "') AND (UserPassword = '" + password + "')";
        Cursor cursor = db.rawQuery(sql, null);
        boolean res = cursor != null && cursor.getCount() > 0;
        cursor.close();
        return res;
    }

    public void register(String firstName, String lastName, String email, String password, String birthday, String gender, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserLogin", email);
        values.put("UserPassword", password);
        values.put("UserFirstName", firstName);
        values.put("UserLastName", lastName);
        values.put("UserEmail", email);
        values.put("UserBirthday", birthday);
        values.put("UserGender", gender);
        values.put("UserPhoneNumber", phoneNumber);
        // Inserting Row
        db.insert("Users", null, values);
    }

    public ArrayList<String> getQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Quiz", null);
        if (cursor.moveToFirst()) {
            list.add(cursor.getString(cursor.getColumnIndex("QuizText")));
            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex("QuizText")));
            }
        }
        cursor.close();
        return list;
    }

    public void updateQuizResults(String userLogin, String quizResults) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserQuiz", quizResults);
        String[] args = new String[]{userLogin,};
        db.update("Users", values, "UserLogin=?", args);
    }

    public String[] getUserData(String userLogin) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Users WHERE UserLogin='" + userLogin+"'", null);
        cursor.moveToFirst();
        String[] data = new String[4];
        data[0] = cursor.getString(cursor.getColumnIndex("UserFirstName"));
        data[1] = cursor.getString(cursor.getColumnIndex("UserLastName"));
        data[2] = cursor.getString(cursor.getColumnIndex("UserBirthday"));
        data[3] = cursor.getString(cursor.getColumnIndex("UserBio"));
        cursor.close();
        return data;
    }

    public void updateBio(String userLogin, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserBio", bio);
        String[] args = new String[]{userLogin};
        db.update("Users", values, "UserLogin=?", args);
    }

    public String getQuiz(String userLogin) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Users WHERE UserLogin='" + userLogin+"'", null);

        cursor.moveToFirst();
        String data = cursor.getString(cursor.getColumnIndex("UserQuiz"));
        cursor.close();
        return data;
    }
}



