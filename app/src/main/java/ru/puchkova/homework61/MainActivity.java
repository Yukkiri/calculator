package ru.puchkova.homework61;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.Math;

import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {
    private static final String radS = "Rad";
    private static final String degS = "Deg";
    private static final String empty = "";
    private static final int one = 1;
    private static final int zero = 0;
    private static final Double test = 111111111111111111111111111111111D;
    private static final int degreeInt = 1;
    private static final int numRootInt = 2;
    private static final int yDegreeInt = 3;
    private static final int logYInt = 4;

    ComputationClass com = new ComputationClass();

    private TextView computation;
    private TextView calc;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button dot;
    private Button ac;
    private Button opposite;
    private Button percent;
    private Button division;
    private Button multiply;
    private Button subtraction;
    private Button addition;
    private Button equal;
    //инжереные кнопки
    private Button bracket_l;
    private Button bracket_r;
    private Button mc;
    private Button mr;
    private Button m_p;
    private Button m_m;

    private Button second;
    private Button sin;
    private Button cos;
    private Button tan;
    private Button sinh;
    private Button cosh;
    private Button tanh;

    private Button sinm;
    private Button cosm;
    private Button tanm;
    private Button sinhm;
    private Button coshm;
    private Button tanhm;

    private Button rad;
    private Button pi;
    private Button e;
    private Button rand;

    private Button square;
    private Button cube;
    private Button degree;
    private Button sqr_root;
    private Button cube_root;
    private Button num_root;
    private Button fraction;
    private Button y_degree;
    private Button two_degree;
    private Button log_y;
    private Button log_two;
    private Button e_degree;
    private Button ten_degree;
    private Button ln;
    private Button log_ten;

    private Button fact;

    private String sNumber;
    private String calculationString;
    private String computationString;

    private Double calculationDouble;



    private Double [] memory = {0D};
    Double [] number = {test};


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
        bracket_l = findViewById(R.id.bracket_l);
        bracket_r = findViewById(R.id.bracket_r);
        mc = findViewById(R.id.mc);
        mr = findViewById(R.id.mr);
        m_p = findViewById(R.id.m_p);
        m_m = findViewById(R.id.m_m);

        second = findViewById(R.id.second);
        sin = findViewById(R.id.sin);
        sinh = findViewById(R.id.sinh);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        cosh = findViewById(R.id.cosh);
        tanh = findViewById(R.id.tanh);

        sinm = findViewById(R.id.sinm);
        cosm = findViewById(R.id.cosm);
        tanm = findViewById(R.id.tanm);
        sinhm = findViewById(R.id.sinhm);
        coshm = findViewById(R.id.coshm);
        tanhm = findViewById(R.id.tanhm);


        rad = findViewById(R.id.rad);
        pi = findViewById(R.id.pi);
        e = findViewById(R.id.e);
        rand = findViewById(R.id.rand);

        y_degree = findViewById(R.id.y_degree);
        two_degree = findViewById(R.id.two_degree);
        log_y = findViewById(R.id.log_y);
        log_two = findViewById(R.id.log_two);
        square = findViewById(R.id.square);
        cube = findViewById(R.id.cube);
        degree = findViewById(R.id.degree);
        sqr_root = findViewById(R.id.sqr_root);
        cube_root = findViewById(R.id.cube_root);
        num_root = findViewById(R.id.num_root);
        fraction = findViewById(R.id.fraction);
        e_degree = findViewById(R.id.e_degree);
        ten_degree = findViewById(R.id.ten_degree);
        ln = findViewById(R.id.ln);
        log_ten = findViewById(R.id.log_ten);
        fact = findViewById(R.id.fact);


        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/calc.ttf");
        computation.setTypeface(typeFace);
        calc.setTypeface(typeFace);

        setOcl();
    }

    private void setOcl(){
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
        equal.setOnClickListener(equalOnClickListener);
        dot.setOnClickListener(techOnClickListener);
        percent.setOnClickListener(techOnClickListener);
        opposite.setOnClickListener(techOnClickListener);
        ac.setOnClickListener(clearOnClickListener);

        try {
            bracket_r.setOnClickListener(bracketsOnClickListener);
            bracket_l.setOnClickListener(bracketsOnClickListener);
            mc.setOnClickListener(memoryOnClickListener);
            mr.setOnClickListener(memoryOnClickListener);
            m_p.setOnClickListener(memoryOnClickListener);
            m_m.setOnClickListener(memoryOnClickListener);
            sin.setOnClickListener(trigOnClickListener);
            sinh.setOnClickListener(trigOnClickListener);
            cos.setOnClickListener(trigOnClickListener);
            tan.setOnClickListener(trigOnClickListener);
            cosh.setOnClickListener(trigOnClickListener);
            tanh.setOnClickListener(trigOnClickListener);
            sinm.setOnClickListener(trigOnClickListener);
            sinhm.setOnClickListener(trigOnClickListener);
            cosm.setOnClickListener(trigOnClickListener);
            tanm.setOnClickListener(trigOnClickListener);
            coshm.setOnClickListener(trigOnClickListener);
            tanhm.setOnClickListener(trigOnClickListener);

            second.setOnClickListener(secondOnClickListener);
            rad.setOnClickListener(radOnClickListener);
            pi.setOnClickListener(piOnClickListener);
            e.setOnClickListener(eOnClickListener);
            rand.setOnClickListener(randOnClickListener);

            //спосити

            square.setOnClickListener(degreeOnClickListener);
            cube.setOnClickListener(degreeOnClickListener);
            degree.setOnClickListener(logsOnClickListener);
            sqr_root.setOnClickListener(degreeOnClickListener);
            cube_root.setOnClickListener(degreeOnClickListener);
            num_root.setOnClickListener(logsOnClickListener);
            fraction.setOnClickListener(degreeOnClickListener);
            y_degree.setOnClickListener(logsOnClickListener);
            two_degree.setOnClickListener(degreeOnClickListener);
            log_y.setOnClickListener(logsOnClickListener);
            log_two.setOnClickListener(degreeOnClickListener);
            e_degree.setOnClickListener(degreeOnClickListener);
            ten_degree.setOnClickListener(degreeOnClickListener);
            ln.setOnClickListener(degreeOnClickListener);
            log_ten.setOnClickListener(degreeOnClickListener);

            fact.setOnClickListener(factOnClickListener);
        } catch (NullPointerException e){
            //хз чо тут писать
        }
    }

    View.OnClickListener numOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
            computationString = computation.getText().toString();
            computationString = com.numbers(sNumber, computationString);
            computation.setText(computationString);
        }
    };

    View.OnClickListener techOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computationString = computation.getText().toString();

            switch (v.getId()){
                case R.id.opposite:
                    computationString = com.getOpposite(computationString);
                    break;
                case R.id.percent:
                    computationString = com.setPercent(computationString);
                    break;
                case R.id.dot:
                    computationString = com.setDot(computationString);
                    break;
            }
            computation.setText(computationString);
        }
    };

    View.OnClickListener equalOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int option;
            try{
                option = calculationLogs();
            } catch (NullPointerException e){
                option = 0;
            }
            computationString = computation.getText().toString();
            calculationString = calc.getText().toString();
            if(option != 0){
                calculationDouble = number[0];
                computationString = com.logs(calculationDouble, calculationString, option);
                number[0] = test;
            } else {
                computationString = com.equal(computationString);
            }
            calc.setText(empty);
            computation.setText(computationString);
        }
    };

    View.OnClickListener operOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.division:
                    sNumber = "/";
                    break;
                case R.id.multiply:
                    sNumber = "*";
                    break;
                case R.id.subtraction:
                    sNumber = "-";
                    break;
                default:
                    sNumber = "+";
            }
            calculationString = calc.getText().toString();
            computationString = computation.getText().toString();
            calculationString = com.setText(sNumber, calculationString, computationString);
            computation.setText(empty);
            calc.setText(calculationString);
        }
    };

    View.OnClickListener bracketsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bracket_l:
                    sNumber = "(";
                    break;
                case R.id.bracket_r:
                    sNumber = ")";
                    break;
                default:
                    sNumber = "+";
            }
            calculationString = calc.getText().toString();
            computationString = computation.getText().toString();
            calculationString = com.setBracket(sNumber, calculationString, computationString);
            computation.setText(empty);
            calc.setText(calculationString);
        }
    };

    View.OnClickListener clearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computation.setText(empty);
            calc.setText(empty);
            com.clear();
        }
    };

    View.OnClickListener memoryOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Double temp;
            Double add;
            switch (v.getId()){
                case R.id.mc:
                    memory[0] = 0D;
                    break;
                case R.id.mr:
                    computationString = valueOf(memory[0]);
                    computation.setText(computationString);
                    break;
                case R.id.m_m:
                    temp = memory[0];
                    add = Double.parseDouble(computation.getText().toString());
                    temp = temp - add;
                    memory[0] = temp;
                    break;
                case R.id.m_p:
                    temp = memory[0];
                    add = Double.parseDouble(computation.getText().toString());
                    temp = temp + add;
                    memory[0] = temp;
                    break;
            }
        }
    };

    View.OnClickListener secondOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(sin.getVisibility() == View.VISIBLE){
                sin.setVisibility(View.INVISIBLE);
                cos.setVisibility(View.INVISIBLE);
                tan.setVisibility(View.INVISIBLE);
                sinh.setVisibility(View.INVISIBLE);
                cosh.setVisibility(View.INVISIBLE);
                tanh.setVisibility(View.INVISIBLE);
                e_degree.setVisibility(View.INVISIBLE);
                ten_degree.setVisibility(View.INVISIBLE);
                ln.setVisibility(View.INVISIBLE);
                log_ten.setVisibility(View.INVISIBLE);

                sinm.setVisibility(View.VISIBLE);
                cosm.setVisibility(View.VISIBLE);
                tanm.setVisibility(View.VISIBLE);
                sinhm.setVisibility(View.VISIBLE);
                coshm.setVisibility(View.VISIBLE);
                tanhm.setVisibility(View.VISIBLE);
                y_degree.setVisibility(View.VISIBLE);
                two_degree.setVisibility(View.VISIBLE);
                log_y.setVisibility(View.VISIBLE);
                log_two.setVisibility(View.VISIBLE);
            } else {
                sin.setVisibility(View.VISIBLE);
                cos.setVisibility(View.VISIBLE);
                tan.setVisibility(View.VISIBLE);
                sinh.setVisibility(View.VISIBLE);
                cosh.setVisibility(View.VISIBLE);
                tanh.setVisibility(View.VISIBLE);
                e_degree.setVisibility(View.VISIBLE);
                ten_degree.setVisibility(View.VISIBLE);
                ln.setVisibility(View.VISIBLE);
                log_ten.setVisibility(View.VISIBLE);

                sinm.setVisibility(View.INVISIBLE);
                cosm.setVisibility(View.INVISIBLE);
                tanm.setVisibility(View.INVISIBLE);
                sinhm.setVisibility(View.INVISIBLE);
                coshm.setVisibility(View.INVISIBLE);
                tanhm.setVisibility(View.INVISIBLE);
                y_degree.setVisibility(View.INVISIBLE);
                two_degree.setVisibility(View.INVISIBLE);
                log_y.setVisibility(View.INVISIBLE);
                log_two.setVisibility(View.INVISIBLE);
            }
        }
    };

    View.OnClickListener trigOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computationString = computation.getText().toString();
            computationString = com.replaceError(computationString);
            calculationDouble = Double.parseDouble(computationString);
            if(!rad.isPressed()){
                calculationDouble = Math.toRadians(calculationDouble);
            }
            switch (v.getId()){
                case R.id.sin:
                    calculationDouble = Math.sin(calculationDouble);
                    break;
                case R.id.cos:
                    calculationDouble = Math.cos(calculationDouble);
                    break;
                case R.id.tan:
                    calculationDouble = Math.tan(calculationDouble);
                    break;
                case R.id.sinh:
                    calculationDouble = Math.sinh(calculationDouble);
                    break;
                case R.id.cosh:
                    calculationDouble = Math.cosh(calculationDouble);
                    break;
                case R.id.tanh:
                    calculationDouble = Math.tanh(calculationDouble);
                    break;
                case R.id.sinm:
                    calculationDouble = one/Math.sin(calculationDouble);
                    break;
                case R.id.cosm:
                    calculationDouble = one/Math.cos(calculationDouble);
                    break;
                case R.id.tanm:
                    calculationDouble = one/Math.tan(calculationDouble);
                    break;
                case R.id.sinhm:
                    calculationDouble = one/Math.sinh(calculationDouble);
                    break;
                case R.id.coshm:
                    calculationDouble = one/Math.cosh(calculationDouble);
                    break;
                case R.id.tanhm:
                    calculationDouble = one/Math.tanh(calculationDouble);
                    break;
            }
            computation.setText(valueOf(calculationDouble));
        }
    };

    View.OnClickListener radOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rad.isPressed()){
                rad.setText(radS);
                rad.setPressed(false);
            } else{
                rad.setText(degS);
            }
        }
    };

    View.OnClickListener piOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computation.setText(valueOf(Math.PI));
        }
    };

    View.OnClickListener eOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computation.setText(valueOf(Math.E));
        }
    };

    View.OnClickListener randOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computation.setText(valueOf(Math.random()));
        }
    };

    View.OnClickListener degreeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int operation = 0;
            computationString = computation.getText().toString();
            calculationString = calc.toString();
            switch (v.getId()){
                case R.id.square:
                    operation = 0;
                    break;
                case R.id.cube:
                    operation = 1;
                    break;
                case R.id.sqr_root:
                    operation = 2;
                    break;
                case R.id.cube_root:
                    operation = 3;
                    break;
                case R.id.fraction:
                    operation = 4;
                    break;
                case R.id.two_degree:
                    operation = 5;
                    break;
                case R.id.e_degree:
                    operation = 6;
                    break;
                case R.id.ten_degree:
                    operation = 7;
                    break;
                case R.id.ln:
                    operation = 8;
                    break;
                case R.id.log_ten:
                    operation = 9;
                    break;
                case R.id.log_two:
                    operation = 10;
                    break;
            }
            computationString = com.degrees(computationString, calculationString, operation);
            computation.setText(com.checkError(computationString));
        }
    };

    View.OnClickListener logsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computationString = computation.getText().toString();
            calculationDouble = Double.parseDouble(computationString);
            logsOff();
            switch (v.getId()) {
                case R.id.degree://........
                    degree.setPressed(true);
                    break;
                case R.id.num_root://.......
                    num_root.setPressed(true);
                    break;
                case R.id.y_degree: //........
                    y_degree.setPressed(true);
                    break;
                case R.id.log_y://......
                    log_y.setPressed(true);
                    break;
            }
            number[0] = calculationDouble;
        }
    };

    View.OnClickListener factOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computationString = computation.getText().toString();
            computationString = com.factorial(computationString);
            computation.setText(com.checkError(computationString));
        }
    };

    private int calculationLogs(){
        if(degree.isPressed()){
            return degreeInt;
        } else if (num_root.isPressed()){
            return numRootInt;
        } else if (y_degree.isPressed()){
            return yDegreeInt;
        } else if (log_y.isPressed()){
            return logYInt;
        }
        return zero;
    }

    private void logsOff(){
        degree.setPressed(false);
        num_root.setPressed(false);
        y_degree.setPressed(false);
        log_y.setPressed(false);
    }
}