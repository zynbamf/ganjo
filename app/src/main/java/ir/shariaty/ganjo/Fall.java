package ir.shariaty.ganjo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fall extends AppCompatActivity {

    TextView sher, number;
    ImageView back;
    String random_string = "";
    TextView textView;

    ImageView again;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);

        sher = findViewById(R.id.sher);
        number = findViewById(R.id.qazal);


        back=findViewById(R.id.imageView10);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fall.this, MainActivity.class);
                startActivity(intent);
            }
        });


        textView=findViewById(R.id.textView2);
        again = findViewById(R.id.imageView11);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFaal();

            }
        });




        getFaal();

        int random_string_length = 5;
        String[] all_characters = {
        };
        int all_characters_length = all_characters.length;

        int min = 0;
        int max = all_characters_length-1;



        Random r = new Random();
        int random_number = r.nextInt(max - min + 1) + min;
        String random_character = all_characters[random_number];
        random_string = random_string + random_character;


    }


    private void getFaal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetData.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetData GetData = retrofit.create(GetData.class);
        Call<Faal> call = GetData.getFal();
        call.enqueue(new Callback<Faal>() {
            @Override
            public void onResponse(Call<Faal> call, Response<Faal> response) {
                if (response.isSuccessful()) {
                    Faal faal = response.body();
                    number.setText(faal.getTitle());
                    sher.setText(faal.getPlainText());
                    textView.setText(random_string);








                }
            }

            @Override
            public void onFailure(Call<Faal> call, Throwable t) {
                Toast.makeText(Fall.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickB(View v)
    {
        Intent intent2 = new Intent(this,
                MainActivity.class);

        Bundle b = new Bundle();
        intent2.putExtra("bundle data", b);
        startActivity(intent2);
    }
}