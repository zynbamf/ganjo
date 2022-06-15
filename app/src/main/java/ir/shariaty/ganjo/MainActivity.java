package ir.shariaty.ganjo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickF(View v)
    {
        Intent intent1 = new Intent(this,
                Fall.class);

        Bundle b = new Bundle();
        intent1.putExtra("bundle data", b);
        startActivity(intent1);
    }
    public void onClickSh(View v)
    {
        Intent intent1 = new Intent(this,
                Zendegy.class);
        Bundle b = new Bundle();
        intent1.putExtra("bundle data", b);
        startActivity(intent1);
    }
}