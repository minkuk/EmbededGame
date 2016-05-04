package com.example.hdy.embeddedgame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int DIALOG_CUSTOM_ID = 10;
    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gameStart = (Button) findViewById(R.id.gameStart);
        gameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_CUSTOM_ID);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            try {
                TextView totalScoreText = (TextView) findViewById(R.id.totalScore);
                totalScoreText.setText(data.getStringExtra("Score"));
            }catch (Exception e){

            }
        }
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case DIALOG_CUSTOM_ID:
                dialog = new Dialog(this);
                dialog.setTitle("게임 선택");
                dialog.setContentView(R.layout.game_select_dialog);
                dialog.setCanceledOnTouchOutside(false);

                Button complete = (Button) dialog.findViewById(R.id.complete);
                final RadioGroup gameGroup = (RadioGroup) dialog.findViewById(R.id.gamegroup);
                final Dialog closeDialog = dialog;
                complete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int id = gameGroup.getCheckedRadioButtonId();
                        if (id == -1){
                            Toast.makeText(getApplicationContext(),"하나의 게임을 선택해주세요",Toast.LENGTH_SHORT).show();
                        }
                        else if (id == R.id.game1){
                            Toast.makeText(getApplicationContext(),"야구 게임",Toast.LENGTH_SHORT).show();
                            closeDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(),Game1.class);
                            intent.putExtra("SelectedGameIndex","1");
                            intent.putExtra("Score","0");
                            startActivityForResult(intent, 1001);
                        }else if (id == R.id.game2){
                            Toast.makeText(getApplicationContext(),"지뢰 찾기",Toast.LENGTH_SHORT).show();
                            closeDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(),Game1.class);
                            intent.putExtra("SelectedGameIndex","2");
                            intent.putExtra("Score","0");
                            startActivityForResult(intent, 1001);
                        }else if (id == R.id.game3){
                            Toast.makeText(getApplicationContext(),"영단어 맞추기",Toast.LENGTH_SHORT).show();
                            closeDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(),Game1.class);
                            intent.putExtra("SelectedGameIndex","3");
                            intent.putExtra("Score","0");
                            startActivityForResult(intent, 1001);
                        }
                    }
                });

                break;
        }

        return dialog;
    }
}
