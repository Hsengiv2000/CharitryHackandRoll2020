package com.ti.stripepaymentsample;

import android.support.v7.app.AppCompatActivity;




import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.PaymentConfiguration;

import org.w3c.dom.Text;

import java.util.logging.Logger;
enum State{

    PAUSE, RUNNING, STOP;
}
public class HomeScreen extends AppCompatActivity {
    TextView welcome;
    int currenttime;
    CountDownTimer counter;
    State state = State.RUNNING;

    Boolean flag;
    int fine=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("susss" , "eyess");
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home_screen);

        final  EditText duration = (EditText)findViewById(R.id.Duration);
        duration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    duration.setHint("");

            }
        });
        welcome= (TextView)findViewById(R.id.Welcome);
        final Button b = (Button)findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter!= null){

                    counter.cancel();
                }
                counter=new CountDownTimer(Integer.parseInt(duration.getText().toString())*1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        welcome.setText(String.valueOf( millisUntilFinished / 1000));
                        if (state !=State.RUNNING){
                            if(currenttime - millisUntilFinished/1000 >3 ){
                               fine+=currenttime-millisUntilFinished/1000;
                               alertLad(fine);
                            }

                        }
                        Log.i("oncreate" , "fff");
                    }

                    public void onFinish() {
                        ReducePayment();
                        welcome.setText("done!");
                        b.setClickable(true);
                    }
                }.start();
                b.setClickable(false);
            }
        });

    }

    @Override
    protected void onPause() {
        flag = true;
        state = State.PAUSE;

        super.onPause();
        currenttime = Integer.valueOf(welcome.getText().toString());

        Log.i("gero" , "heii");
    }
    @Override
    protected void onStop() {
        state = State.STOP;
        super.onStop();




    }
    @Override
    protected void onResume() {
        state=State.RUNNING;
        try{
        currenttime = Integer.valueOf(welcome.getText().toString());

        }
        catch(Exception e){
            currenttime =0;

        }
        Log.i("hey" , String.valueOf(currenttime));
        super.onResume();
        flag = false;
    }
    public void alertLad(int penalty){
        Toast toast = Toast.makeText(getApplicationContext(), "Current fine "+ String.valueOf(penalty), Toast.LENGTH_LONG);
        toast.show();

    }
    public void ReducePayment(){
        Toast toast = Toast.makeText(getApplicationContext(), "Payment will be deducted", Toast.LENGTH_LONG);
        toast.show();
        BackgroundWorker backgroundWorker=new BackgroundWorker(getApplicationContext());
        backgroundWorker.execute("sup?fined="+String.valueOf(fine));
        Log.i("DONE" , "DONE");
    }
}
