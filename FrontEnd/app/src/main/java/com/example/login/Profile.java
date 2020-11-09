package com.example.login;
//import android.support.v7.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    private static String URL_REGIST = "https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io";
    private static int RESULT_LOAD_IMG = 1;
    private static final int PICK_FROM_GALLERY = 10;
    ImageView photo;

    /**
     * On create function for the profile page.
     *
     * @param savedInstanceState parameter- saved for the instance state.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        photo = findViewById(R.id.imageView);
        final TextView bio = findViewById(R.id.User_bio);
        final EditText edittext = findViewById(R.id.addBio);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    final String newBio = String.valueOf(edittext.getText());
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    Map<String, String> params = new HashMap<>();
                    params.put("bio", newBio);
                    JSONObject parameters = new JSONObject(params);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_REGIST, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Response", "" + response);
                            bio.setText(newBio);
                            edittext.setText("");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                    return true;
                }
                return false;
            }
        });
        final TextView score = findViewById(R.id.quizScore);
        int scoreI = getIntent().getExtras().getInt("quizScore", 0);
        score.setText("ID: 452643");
    }

    public void onButtonClick(View view) {
    }

    /**
     * It goes to the settings page.
     *
     * @param view parameter - view.
     */
    public void goToSettings(View view) {
        Intent intent = new Intent(Profile.this, Settings.class);
        startActivity(intent);
    }

    /**
     * Loads the image to the profile page.
     *
     * @param view parameter - view.
     */
    public void loadImage(View view) {
        try {
            if (ActivityCompat.checkSelfPermission(Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the image in the drawable section.
     *
     * @param requestCode parameter - requestCode.
     * @param resultCode  parameter - resultCode.
     * @param data        parameter - data.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Drawable d = new BitmapDrawable(getResources(), BitmapFactory.decodeFile(imgDecodableString));
                photo.setImageDrawable(d);
            }
        } catch (Exception e) {

        }
    }

    /**
     * Void methods for granting request to upload the images.
     *
     * @param requestCode  parameter - requestcode.
     * @param permissions  parameter - permissions.
     * @param grantResults parameter - grantResults.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
