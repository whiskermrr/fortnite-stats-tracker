package com.example.mrr.fortnitetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrr.fortnitetracker.Model.UserProfileModel;
import com.example.mrr.fortnitetracker.Network.FortniteApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tText)
    TextView tText;

    FortniteApiService fortniteApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fortniteApiService = FortniteTrackerApplication.get(this).getFortniteApiService();

        Call<UserProfileModel> call = fortniteApiService.getUserStats("pc", "whiskermrr");
        call.enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "XD", Toast.LENGTH_SHORT).show();
                    tText.setText(response.body().getEpicUserHandle());
                }
                else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    tText.setText(response.toString());
                }
            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {
                tText.setText(call.toString());
                Log.v("ERROR", call.toString());
                Log.v("ERROR", t.getMessage());
            }
        });
    }
}
