package com.example.hdy.embeddedgame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Game1 extends AppCompatActivity {

    public static final int GAME1 = 10;
    String SelectedGameIndex;
    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1);

        Intent recv_intent = getIntent();
        final String score = recv_intent.getExtras().getString("Score");
        totalScore = totalScore + Integer.parseInt(score);

        TextView scoreNow = (TextView) findViewById(R.id.scoreNow);
        scoreNow.setText(String.valueOf(totalScore));

        /* 게임 들어갈 부분 */

        totalScore += 10;

        /*게임 종료 후 다음 게임으로 넘어가는 버튼*/
        final String selectedGameIndex = recv_intent.getExtras().getString("SelectedGameIndex");
        SelectedGameIndex = selectedGameIndex;
        Button nextGame2 = (Button) findViewById(R.id.nextGame2);
        nextGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(GAME1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1002) {
            try{
                Intent intent = new Intent();
                intent.putExtra("Score", data.getStringExtra("Score"));
                setResult(1001,intent);
                finish();
            }catch (Exception e){

            }
        }
    }


    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case GAME1:
                dialog = new Dialog(this);
                dialog.setTitle("게임 선택");
                dialog.setContentView(R.layout.game_select_dialog);
                dialog.setCanceledOnTouchOutside(false);

                if(SelectedGameIndex.equals("1")){
                    RadioButton thisGame = (RadioButton) dialog.findViewById(R.id.game1);
                    thisGame.setEnabled(false);
                    thisGame.setFocusable(false);
                }else if(SelectedGameIndex.equals("2")){
                    RadioButton thisGame = (RadioButton) dialog.findViewById(R.id.game2);
                    thisGame.setEnabled(false);
                    thisGame.setFocusable(false);
                }else if(SelectedGameIndex.equals("3")){
                    RadioButton thisGame = (RadioButton) dialog.findViewById(R.id.game3);
                    thisGame.setEnabled(false);
                    thisGame.setFocusable(false);
                }

                Button complete = (Button) dialog.findViewById(R.id.complete);
                final Dialog closeDialog = dialog;
                complete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closeDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(),Game2.class);
                        intent.putExtra("Score", String.valueOf(totalScore));
                        startActivityForResult(intent, 1002);

                    }
                });

                break;
        }

        return dialog;
    }

}
