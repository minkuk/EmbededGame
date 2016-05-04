package com.example.hdy.embeddedgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game3 extends AppCompatActivity {

    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3);

        Intent recv_intent = getIntent();
        final String score = recv_intent.getExtras().getString("Score");
        totalScore = totalScore + Integer.parseInt(score);

        TextView scoreNow = (TextView) findViewById(R.id.scoreNow3);
        scoreNow.setText(String.valueOf(totalScore));

        /*게임이 들어가는 부분*/
        totalScore += 20;

        /*메인 화면으로 돌아가는 부분*/
        Button nextHome = (Button) findViewById(R.id.nextHome);
        nextHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Score", String.valueOf(totalScore));
                setResult(1003,intent);
                finish();
                //startActivityForResult(intent, 1004);
            }
        });
    }

}
