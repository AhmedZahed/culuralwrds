package com.ahmedtry.culturalwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class activityAnswer extends AppCompatActivity {
    private TextView mAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mAnswers = findViewById(R.id.text_view_answer);
        String detils = getIntent().getStringExtra("answer text extra");
        if(detils != null ){
            mAnswers.setText(detils);
        }

    }
    public void onClickReturn(View view){
        finish();
    }//دالة الرجوع
}