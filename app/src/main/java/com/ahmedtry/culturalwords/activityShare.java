package com.ahmedtry.culturalwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class activityShare extends AppCompatActivity {
    private String mText;
    public EditText mEditTextTitle;
    private ImageView imageView;
    private int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        imageId = getIntent().getIntExtra("image_id", 0);
        mEditTextTitle = findViewById(R.id.edit_text_share_title);
        imageView = findViewById(R.id.image_share_question);
        imageView.setImageResource(imageId);

    }

    public void onShareQuizonClikd(View view) {
        String questionTitle = mEditTextTitle.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, questionTitle + "\n" + mText );
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }
}