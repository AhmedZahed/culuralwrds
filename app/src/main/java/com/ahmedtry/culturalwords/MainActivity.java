package com.ahmedtry.culturalwords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    private ImageView btn_lng_ImageView;
    private ImageView imageViewQuiz;
    private String mTheDatail;
    private String mDescription;
    private int mCorentIndex = -1;
    private String[] Answers;
    private String[] Answer_description;

    int[] Pics = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11,
            R.drawable.icon_12,
            R.drawable.icon_13
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app_lang", "");
        if (!appLang.equals(""))
            LocaleHelper.setLocale(this, appLang); /* اكواد حفظ اللغة */

        imageViewQuiz = findViewById(R.id.image_view_question);
        btn_lng_ImageView = findViewById(R.id.button_change_language);
        Answers = getResources().getStringArray(R.array.answers);
        Answer_description = getResources().getStringArray(R.array.answer_description);

        showImage(null); /* القيمة المبدئية للصورة */

        btn_lng_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn_lng_ImageView);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menuChangeLangoge)
                            showLanguageDialog();
                        return true;
                    }
                });

                popupMenu.show();
            }
        }); /* دالة تغير اللغة */


    }

    private void showLanguageDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setItems(R.array.Languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;

                        }/*  تعين اللغة */
                        savelanguage(language);
                        LocaleHelper.setLocale(MainActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent); /* اكواد مساعدة لتغير اللغة */

                    }
                }).create();
        alertDialog.show();
    }/* دالة تعين اللغة */

    private void savelanguage(String lang) {
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang", lang);
        editor.apply();
    }/* دالة حفظ اللغة */


    public void showImage(View view) {
        mCorentIndex++;
        mDescription = Answer_description[mCorentIndex];
        mTheDatail = Answers[mCorentIndex];
        Drawable imageDrawable = ContextCompat.getDrawable(this, Pics[mCorentIndex]);
        imageViewQuiz.setImageDrawable(imageDrawable);
        if (mCorentIndex == 12) {
            mCorentIndex = -1;
        }
    }/* دالة اظهار وتعين الصور */

    public void onAnswerClikd(View view) {
        Intent intent = new Intent(this, activityAnswer.class);
        intent.putExtra("answer text extra", mTheDatail);
        startActivity(intent);
    } /* دالة الانتقال الى واجهة الاجابات   */

    public void onShareClikd(View view) {
        Intent intent = new Intent(this, activityShare.class);
        intent.putExtra("image_id", Pics[mCorentIndex]);
        startActivity(intent);
    } /* دالة الانتفال الى واجهة المشاركة */


  /*  public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

   */
}