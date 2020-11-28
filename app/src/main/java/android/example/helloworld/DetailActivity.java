package android.example.helloworld;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = findViewById(R.id.tvTitle);
        TextView tv_name = findViewById(R.id.tvName);
        TextView popularity = findViewById(R.id.tvPopularity);
        TextView sinopsis = findViewById(R.id.tvOverview);
        TextView rilis = findViewById(R.id.tvRelease);
        ImageView gambar = findViewById(R.id.imgCover);

        title.setText(getIntent().getStringExtra("title"));
        tv_name.setText(getIntent().getStringExtra("tv_name"));
        popularity.setText(getIntent().getStringExtra("popularity"));
        sinopsis.setText(getIntent().getStringExtra("sinopsis"));
        rilis.setText(getIntent().getStringExtra("rilis"));
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w200"+getIntent().getStringExtra("gambar"))
                .into(gambar);
    }
}