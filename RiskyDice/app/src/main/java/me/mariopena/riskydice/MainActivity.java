package me.mariopena.riskydice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public int userOverallScore = 0;
    public int userTurnScore = 0;
    public int computerOverallScore = 0;
    public int computerTurnScore = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRoll(View view) {

        if(rollHelper(0) == 0)
            onHold(view);
        hasWon(0);
    }

    public void onReset(View view){

        userOverallScore = 0;
        userTurnScore = 0;
        computerOverallScore = 0;
        computerTurnScore = 0;

        TextView playerScore = (TextView) findViewById(R.id.playerScore);
        TextView computerScore = (TextView) findViewById(R.id.computerScore);
        TextView message = (TextView) findViewById(R.id.message);
        ImageView dicePic = (ImageView) findViewById(R.id.diceView);

        playerScore.setText("0");
        computerScore.setText("0");
        message.setText("");
        dicePic.setImageResource(R.drawable.dice1);
    }

    public void onHold(View view){

        userOverallScore += userTurnScore;
        userTurnScore = 0;

        TextView message = (TextView) findViewById(R.id.message);
        message.setText("Computer's Turn");

        //Disable both buttons while the computer rolls
        Button roll = (Button) findViewById(R.id.roll);
        roll.setEnabled(false);

        Button hold = (Button) findViewById(R.id.hold);
        hold.setEnabled(false);

        //Call Computer To Roll

        computerTurn();
    }

    public void computerTurn() {

            final Handler timerHandler = new Handler();
            Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {

                    int numRolled = rollHelper(1);

                    if (numRolled != 0 && getCurrentScores(1) < 100 && computerTurnScore < 20)
                        timerHandler.postDelayed(this, 1000);

                }
            };

            timerRunnable.run();

            computerOverallScore += computerTurnScore;
            computerTurnScore = 0;

            //Re-enabled both buttons
            Button roll = (Button) findViewById(R.id.roll);
            roll.setEnabled(true);
            Button hold = (Button) findViewById(R.id.hold);
            hold.setEnabled(true);

            hasWon(1);

    }

    private int rollHelper(int turn){

        int numRolled = random.nextInt(5);
        TextView score;
        TextView message = (TextView) findViewById(R.id.message);

        //Check if player or computer turn
        if(turn == 0) {
            userTurnScore += numRolled + 1;
            score = (TextView) findViewById(R.id.playerScore);
        }
        else {
            computerTurnScore += numRolled + 1;
            score = (TextView) findViewById(R.id.computerScore);
        }

        ImageView dicePic = (ImageView) findViewById(R.id.diceView);

        switch (numRolled) {
            case 0: {

                if(turn == 0) {
                    userTurnScore = 0;
                    String t = "Player rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }
                else {
                    computerTurnScore = 0;
                    String t = "Computer rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }

                dicePic.setImageResource(R.drawable.dice1);
                score.setText(String.valueOf(getCurrentScores(turn)));
                break;
            }
            case 1: {
                if(turn == 0) {
                    String t = "Player rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }
                else {
                    String t = "Computer rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }

                String display = String.valueOf(getCurrentScores(turn));
                dicePic.setImageResource(R.drawable.dice2);
                score.setText(display);
                break;
            }
            case 2: {
                if(turn == 0) {
                    String t = "Player rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }
                else {
                    String t = "Computer rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }

                String display = String.valueOf(getCurrentScores(turn));
                dicePic.setImageResource(R.drawable.dice3);
                score.setText(display);
                break;
            }
            case 3: {
                if(turn == 0) {
                    String t = "Player rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }
                else {
                    String t = "Computer rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }

                String display = String.valueOf(getCurrentScores(turn));
                dicePic.setImageResource(R.drawable.dice4);
                score.setText(display);
                break;
            }
            case 4: {
                if(turn == 0) {
                    String t = "Player rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }
                else {
                    String t = "Computer rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }

                String display = String.valueOf(getCurrentScores(turn));
                dicePic.setImageResource(R.drawable.dice5);
                score.setText(display);
                break;
            }
            case 5: {
                if(turn == 0) {
                    String t = "Player rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }
                else {
                    String t = "Computer rolled " + String.valueOf(numRolled + 1);
                    message.setText(t);
                }

                String display = String.valueOf(userOverallScore + userTurnScore);
                dicePic.setImageResource(R.drawable.dice6);
                score.setText(display);
                break;
            }
            default:{
                score.setText("Error!");
                break;
            }

        }
        return numRolled;
    }

    private int getCurrentScores(int player){

        if(player == 0)
            return userTurnScore + userOverallScore;
        else
            return computerTurnScore + computerOverallScore;
    }

    private void hasWon(int turn){
        if(getCurrentScores(turn) >= 100)
        {
            //Disable both buttons while the computer rolls
            Button roll = (Button) findViewById(R.id.roll);
            roll.setEnabled(false);

            Button hold = (Button) findViewById(R.id.hold);
            hold.setEnabled(false);

            TextView message = (TextView) findViewById(R.id.message);

            if(turn == 0)
                message.setText("Player has Won! Please Reset");
            else
                message.setText("Computer has Won! Please Reset");
        }

    }


}
