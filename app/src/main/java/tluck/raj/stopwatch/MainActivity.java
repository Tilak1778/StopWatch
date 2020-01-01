package tluck.raj.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running;

    private boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasrunning");

        }


        runTimmer();

    }



    public void onClickStart(View view){
        running=true;


    }

    public void onClickStop(View view){

        running=false;

    }

    public void onClickReset(View view){
        running=false;
        seconds=0;


    }

    protected void runTimmer(){


      final TextView time=findViewById(R.id.Textview_Time);

        final Handler handler=new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes= (seconds%3600)/60;
                int secs=seconds%60;

                String timeFormatted= String.format("%d:%02d:%02d",hours,minutes,secs);

                time.setText(timeFormatted);

                if(running){
                    seconds++;
                }

                handler.postDelayed(this,1000);

            }
        });




    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning=running;
        running=false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(wasRunning)
            running=true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstance) {
        super.onSaveInstanceState(savedInstance);
        savedInstance.putInt("seconds",seconds);
        savedInstance.putBoolean("running",running);
        savedInstance.putBoolean("wasrunning",wasRunning);


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
