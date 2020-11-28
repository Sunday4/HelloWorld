package android.example.helloworld;

import android.content.Intent;
import android.example.helloworld.Adapter.MovieAdapter;
import android.example.helloworld.Model.ResultsItem;
import android.example.helloworld.Model.RootModelMovie;
import android.example.helloworld.alarm.AlarmActivity;
import android.example.helloworld.rest.ApiConfig;
import android.example.helloworld.rest.ApiService;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.newproject.adapter.MovieAdapter;
//import com.example.newproject.alarm.AlarmActivity;
//import com.example.newproject.model.ResultsItem;
//import com.example.newproject.model.RootModelMovie;
//import com.example.newproject.rest.ApiConfig;
//import com.example.newproject.rest.ApiService;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private ArrayList<ResultsItem> resultsItems;
    private MovieAdapter movieAdapter;

    private Text mAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        resultsItems = new ArrayList<>();

        getData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void getData() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.getData()
                .enqueue(new Callback<RootModelMovie>() {
                    @Override
                    public void onResponse(Call<RootModelMovie> call, Response<RootModelMovie> response) {
                        if (response.isSuccessful()){
                            resultsItems = response.body().getResults();
                            movieAdapter = new MovieAdapter(resultsItems, getApplicationContext());
                            movieAdapter.notifyDataSetChanged();
                            rv.setAdapter(movieAdapter);
                            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }

                    @Override
                    public void onFailure(Call<RootModelMovie> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        mAlarm= findViewById(R.id.action_alarm);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_alarm:
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                String mOrderMessage = null;
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}