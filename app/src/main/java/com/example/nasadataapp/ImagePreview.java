package com.example.nasadataapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class ImagePreview extends AppCompatActivity {

    private ImageView imageView, back, info;
    private String tittlename, desc;
    private TextView textView, description;
    private String imgUrl;
    RelativeLayout about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        about = findViewById(R.id.about);
        textView = findViewById(R.id.tittles);
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.preview_img);
        info = findViewById(R.id.information);
        back = findViewById(R.id.back);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.setVisibility(View.VISIBLE);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.setVisibility(View.GONE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImagePreview.this, MainActivity.class));
            }
        });

        Intent intent = getIntent();
        imgUrl = intent.getStringExtra("URL");
        tittlename = intent.getStringExtra("IMGTITTLE");
        desc = intent.getStringExtra("EXPLAIN");
        textView.setText(tittlename);
        description.setText(desc);
        Picasso.with(getApplicationContext()).load(imgUrl).into(imageView);

    }
}
