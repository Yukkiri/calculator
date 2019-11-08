package ru.puchkova.homework61;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class ComputationClass extends MainActivity {
    private static final String ERROR = "ERROR";

    public void numbers(String sNumber){
        String calcString;
        calcString = computation.getText().toString();
        if(calcString.equals(ERROR)){
            calcString = sNumber;
            computation.setText(calcString);
        } else {
            calcString = calcString + sNumber;
            computation.setText(calcString);
        }
    }

    public void equal(){
        //берем написанное на большом экране
        String calcString = computation.getText().toString();
        //добавляем к инфе на маленьком инфу большого
        String expression = calc.getText().toString() + calcString;
        //ищем самую вложенную часть со скобками
        Pattern brackets = Pattern.compile("\\({1}([0-9+-/\\*]+)\\){1}+");
        Matcher matcher = brackets.matcher(expression);
        //пока не избавимся от всех скобок
        while(matcher.find()){
            int start = matcher.start() + 1;
            int end = matcher.end() - 1;
            String tempExp = expression.substring(start, end);
            //вычисляем часть со скобками
            tempExp = solution(tempExp);
            //замещаем скобки готовым числом
            matcher.replaceFirst(tempExp);
        }
        //когда скобок не осталось, вычисляем итоговое выражение
        expression = solution(expression);
        computation.setText(expression);
        calc.setText("");
    }


    public String solution(String expression){
        //ищем сначала деление и умножение
        Pattern first = Pattern.compile("([0-9\\.]+[/\\*]{1}[0-9\\.]+)");
        Pattern fExp = Pattern.compile("([/\\*])");
        expression = pattern(first,fExp, expression);

        //когда их не останется, ищем сложение и вычитание
        Pattern second = Pattern.compile("([0-9\\.]+[-\\+]{1}[0-9\\.]+)");
        Pattern sExp = Pattern.compile("([-\\+])");
        expression = pattern(second, sExp, expression);

        return expression;
    }

    public String pattern(Pattern first, Pattern second, String expression){
        Matcher mFirst = first.matcher(expression);

        while (mFirst.find()){
            String tempExp = expression.substring(mFirst.start(), mFirst.end());
            //ищем конкретный символ
            Matcher mSecond = second.matcher(tempExp);
            mSecond.find();
            //число 1 до символа, число 2 - после символа, символ символ :)
            Double number1 = Double.parseDouble(tempExp.substring(0, mSecond.start()));
            Double number2 = Double.parseDouble(tempExp.substring(mSecond.start()+1));
            char operation = tempExp.charAt(mSecond.start());
            if(number2 == 0 && operation == '/'){
                expression = ERROR;
                return expression;
            }
            //отправляем непосредственную операцию на вычисление
            tempExp = valueOf(solutionResult(number1, number2, operation));
            //замещаем операцию готовым числом
            expression = mFirst.replaceFirst(tempExp);
            mFirst = first.matcher(expression);
        }
        return expression;
    }




    public double solutionResult(double number1, double number2, char operation){
        double solution;
        switch (operation){
            case '/':
                solution = number1/number2;
                break;
            case '*':
                solution = number1*number2;
                break;
            case '-':
                solution = number1-number2;
                break;
            case '+':
                solution = number1+number2;
                break;
            default:
                solution = 0;
        }
        return solution;
    }

    public void setText(String sNumber){
        String calcExp = calc.getText().toString();
        String compExp = computation.getText().toString();
        if(isError(calcExp, compExp) != -1 && isError(calcExp, compExp) != 1){
            calcExp = calcExp + compExp + sNumber;
            calc.setText(calcExp);
            computation.setText("");
        } else if(isError(calcExp, compExp) == -1){
            computation.setText("");
        }
    }

    public int isError(String calcExp, String compExp){
        int check;
        if (compExp.equals(ERROR)){
            check = -1;
            return check;
        } else if(compExp.equals("") && calcExp.equals("")){
            check = 0;
            return check;
        } else if(compExp.equals("")){
            check = 1;
            return check;
        } else if (calcExp.equals("")){
            check = 2;
            return check;
        } else {
            check = 3;
            return check;
        }
    }

    public void clear(){
        computation.setText("");
        calc.setText("");
    }

    public void setDot(String calcString){
        Pattern dot = Pattern.compile("\\.");
        Matcher mDot = dot.matcher(calcString);
        if (!mDot.find()){
            if (calcString.equals("") || calcString.equals(ERROR)){
                calcString = "0" + ".";
            } else {
                calcString = calcString + ".";
            }
        }
        computation.setText(calcString);
    }

    public void setPercent(String calcString){
        double calcDouble;
        if(calcString.equals(ERROR) || calcString.equals("")){
            calcDouble = 0D;
        } else {
            calcDouble = Double.parseDouble(calcString) / 100;
        }
        computation.setText(valueOf(calcDouble));
    }

    public void getOpposite(String calcString){
        Pattern minus = Pattern.compile("-");
        Matcher min = minus.matcher(calcString);
        if(calcString.equals(ERROR)){
            calcString = "0";
        }
        if(min.find()){
            calcString = calcString.substring(1);
        } else {
            calcString = "-" + calcString;
        }
        computation.setText(calcString);
    }
}
