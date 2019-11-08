package ru.puchkova.homework61;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    ComputationClass com = new ComputationClass();

    protected TextView computation;
    protected TextView calc;
    protected Button button0;
    protected Button button1;
    protected Button button2;
    protected Button button3;
    protected Button button4;
    protected Button button5;
    protected Button button6;
    protected Button button7;
    protected Button button8;
    protected Button button9;
    protected Button dot;
    protected Button ac;
    protected Button opposite;
    protected Button percent;
    protected Button division;
    protected Button multiply;
    protected Button subtraction;
    protected Button addition;
    protected Button equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        init();
    }

    private void init(){
        computation = findViewById(R.id.computation);
        calc = findViewById(R.id.calc);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        dot = findViewById(R.id.dot);
        ac = findViewById(R.id.ac);
        opposite = findViewById(R.id.opposite);
        percent = findViewById(R.id.percent);
        division = findViewById(R.id.division);
        multiply = findViewById(R.id.multiply);
        subtraction = findViewById(R.id.subtraction);
        addition = findViewById(R.id.addition);
        equal = findViewById(R.id.equal);



        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/calc.ttf");
        computation.setTypeface(typeFace);
        calc.setTypeface(typeFace);

        button0.setOnClickListener(numOnClickListener);
        button1.setOnClickListener(numOnClickListener);
        button2.setOnClickListener(numOnClickListener);
        button3.setOnClickListener(numOnClickListener);
        button4.setOnClickListener(numOnClickListener);
        button5.setOnClickListener(numOnClickListener);
        button6.setOnClickListener(numOnClickListener);
        button7.setOnClickListener(numOnClickListener);
        button8.setOnClickListener(numOnClickListener);
        button9.setOnClickListener(numOnClickListener);
        division.setOnClickListener(operOnClickListener);
        multiply.setOnClickListener(operOnClickListener);
        addition.setOnClickListener(operOnClickListener);
        subtraction.setOnClickListener(operOnClickListener);
        equal.setOnClickListener(techOnClickListener);
        dot.setOnClickListener(techOnClickListener);
        ac.setOnClickListener(techOnClickListener);
        percent.setOnClickListener(techOnClickListener);
        opposite.setOnClickListener(techOnClickListener);
    }

    View.OnClickListener numOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sNumber;

            switch(v.getId()){
                case R.id.button1:
                    sNumber = "1";
                    break;
                case R.id.button2:
                    sNumber = "2";
                    break;
                case R.id.button3:
                    sNumber = "3";
                    break;
                case R.id.button4:
                    sNumber = "4";
                    break;
                case R.id.button5:
                    sNumber = "5";
                    break;
                case R.id.button6:
                    sNumber = "6";
                    break;
                case R.id.button7:
                    sNumber = "7";
                    break;
                case R.id.button8:
                    sNumber = "8";
                    break;
                case R.id.button9:
                    sNumber = "9";
                    break;
                default:
                sNumber = "0";
            }
            com.numbers(sNumber);
        }
    };

    View.OnClickListener techOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String calcString;

            switch (v.getId()){
                case R.id.ac:
                    com.clear();
                    break;
                case R.id.opposite:
                    calcString = computation.getText().toString();
                    com.getOpposite(calcString);
                    break;
                case R.id.percent:
                    calcString = computation.getText().toString();
                    com.setPercent(calcString);
                    break;
                case R.id.dot:
                    calcString = computation.getText().toString();
                    com.setDot(calcString);
                    break;
                case R.id.equal:
                    com.equal();
                    break;
            }

        }
    };

    View.OnClickListener operOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sNumber;

            switch (v.getId()){
                case R.id.division:
                    sNumber = "/";
                    break;
                case R.id.multiply:
                    sNumber = "*";
                    break;
                case R.id.addition:
                    sNumber = "+";
                    break;
                case R.id.subtraction:
                    sNumber = "-";
                    break;
                default:
                    sNumber = "+";
            }
            com.setText(sNumber);
        }
    };
}