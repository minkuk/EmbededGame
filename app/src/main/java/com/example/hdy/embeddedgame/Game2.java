package com.example.hdy.embeddedgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game2 extends AppCompatActivity {

    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2);

        Intent recv_intent = getIntent();
        final String score = recv_intent.getExtras().getString("Score");
        totalScore = totalScore + Integer.parseInt(score);

        TextView scoreNow = (TextView) findViewById(R.id.scoreNow2);
        scoreNow.setText(String.valueOf(totalScore));

        /* 게임이 들어가는 부분*/
        totalScore += 10;

        /*다음 게임으로 넘어가는 부분*/
        Button nextGame3 = (Button) findViewById(R.id.nextGame3);
        nextGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game3.class);
                intent.putExtra("Score", String.valueOf(totalScore));
                startActivityForResult(intent, 1003);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003) {
            try {
                Intent intent = new Intent();
                intent.putExtra("Score", data.getStringExtra("Score"));
                setResult(1002,intent);
                finish();
            }catch (Exception e){

            }
        }
    }

}
