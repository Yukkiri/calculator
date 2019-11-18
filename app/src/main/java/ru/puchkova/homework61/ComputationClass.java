package ru.puchkova.homework61;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class ComputationClass{
    private static final String ERROR = "ERROR";
    private static final String NAN = "NaN";
    private static final String INFINITY = "INFINITY";
    private static final String zero = "0";
    private static final int one = 1;
    private static final Double cubeNum = 3D;
    private static final Double sqrNum = 2D;
    private static final Double tenNum = 10D;
    private static final String bracket_l = "(";
    private static final String bracket_r = ")";
    private static final String plus = "+";
    private static final String minus = "-";
    private static final String division = "/";
    private static final String multiplication = "*";
    private static final String empty = "";
    private HashMap<Integer, String> expression = new HashMap<>();
    private HashMap<Integer, String> tempExpression = new HashMap<>();
    private static int key = 1;
    private static final int expKey = 0;
    private static int tempKey = 0;
    private String lastObj;
    private String curr1;
    private String curr2;

    private boolean flagFirst = false;

    public String numbers(String sNumber, String computationString){
        expression.put(expKey, "0");
        if(computationString.equals(ERROR) || flagFirst == true || computationString.equals("0")){
            computationString = sNumber;
        } else {
            computationString = computationString + sNumber;
        }
        flagFirst = false;
        return computationString;
    }


    //То что срабатывает при нажатии на =
    public String equal(String computationString){
        //добавляем ячейку "решение" 0
        expression.put(expKey, "0");

        //Если на основном экране есть данные, добавляем их в вычисления
        if(computationString != null) {
            expression.put(key, computationString);
            key++;
        }

        //Если количество открывающих скобок не равно количеству закрывающих скобок, то возвращаем ошибку
        if (!checkBrackets() || expression.isEmpty()){
            expression.clear();
            key = 1;
            return ERROR;
        }
        //Пока в мэпе есть скобки, ищем их
        while (expression.containsValue(bracket_l) && expression.containsValue(bracket_r)) {
            for (int lastBracket = 1; lastBracket < key; lastBracket++) {
                //если нашли правую скобку, то ищем левую
                if (expression.get(lastBracket) != null && expression.get(lastBracket).equals(bracket_r)) {
                    for (int firstBracket = lastBracket; firstBracket > 0; firstBracket--) {
                        //если нашли парную скобку
                        if (expression.get(firstBracket) != null && expression.get(firstBracket).equals(bracket_l)) {
                            //удаляем ячейки со скобками
                            expression.remove(lastBracket);
                            expression.remove(firstBracket);
                            //передаем на решение то, что внутри скобок
                            findOperations(firstBracket + 1, lastBracket - 1);
                        }
                        //если находим пустую ячейку, то продолжаем
                        if (expression.get(firstBracket) == null) {
                            continue;
                        }
                    }
                }
                //если находим пустую ячейку, то продолжаем
                if (expression.get(lastBracket) == null) {
                    continue;
                }
            }
        }
        //когда скобок уже не осталось, запускаем на вычисление
        findOperations(1, key);
        //забираем в строку решение, очищаем мэп и обнуляем переменную ключей
        computationString = expression.get(0);
        expression.clear();
        key = 1;
        return computationString;
    }


    //определяем наличие операторов
    public void findOperations(int start, int end){
        //если находим умножение или деление...
        for (int i = start; i <= end; i++){
            if (expression.get(i) != null) {
                if (expression.get(i).equals(multiplication) || expression.get(i).equals(division)) {
                    //...запускаем вычисление умножения и деления
                    order(start, end, i);
                }
            }
        }
        //если не находим, то ищем сложение и вычитание и по аналогии
        for (int i = start; i <= end; i++){
            if (expression.get(i) != null) {
                if (expression.get(i).equals(plus) || expression.get(i).equals(minus)) {
                    order(start, end, i);
                }
            }
        }
    }

    public void order(int start, int end, int operator){
        int first = start;
        int second = end;

        for(int i = operator-1; i >= start; i--){
            if (expression.get(i) != null){
                first = i;
                break;
            }
        }
        for(int i = operator+1; i <= end; i++){
            if (expression.get(i) != null){
                second = i;
                break;
            }
        }
        curr1 = solution(operator, first, second);
        expression.put(expKey, curr1);
        findOperations(start, end);
    }


    public String solution(int operator, int start, int end){
        Double number1 = Double.parseDouble(expression.get(start));
        Double number2 = Double.parseDouble(expression.get(end));
        char operation = expression.get(operator).charAt(0);
        double solution;

        switch (operation){
            case '/':
                if(number2 == 0D){
                    expression.clear();
                    key = 1;
                    expression.put(expKey, ERROR);
                    return ERROR;
                }
                solution = number1 / number2;
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
        curr1 = valueOf(solution);
        expression.put(start, curr1);
        expression.remove(end);
        expression.remove(operator);
        return curr1;
    }


    public String setText(String sNumber, String calculationString, String computationString){
        expression.put(expKey, "0");
        if (isError(calculationString, computationString) == -1) {
            calculationString = empty;
        }else if (isError(calculationString, computationString) != 1 && !expression.get(expression.size()).equals(bracket_l) && checkOperator()) {
            expression.put(key, computationString);
            key++;
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString + computationString + sNumber;
        } else if (computationString.equals(empty) && expression.get(key-1).equals(bracket_r) && checkOperator()) {
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString + sNumber;
        }
        return calculationString;
    }

    private boolean checkOperator(){
        if(expression.get(expression.size()) == division || expression.get(expression.size()) == multiplication ||
                expression.get(expression.size()) == plus || expression.get(expression.size()) == minus){
            return false;
        }else {
            return true;
        }
    }

    public String setBracket(String sNumber, String calculationString, String computationString){
        int check = isError(calculationString, computationString);
        if(!checkBrackets() && sNumber.equals(bracket_r)){
            if (check == 3){
                expression.put(key, computationString);
                key++;
                calculationString = calculationString + computationString + bracket_r;
                expression.put(key, bracket_r);
                key++;
            }
        } else if(sNumber.equals(bracket_l)) {
            if (check == 1) {
                calculationString = calculationString + computationString + bracket_l;
                expression.put(key, computationString);
                key++;
            } else if (check == 0 || check == -1) {
                calculationString = bracket_l;
            }
            expression.put(key, bracket_l);
            key++;
        }
        return calculationString;
    }

    public boolean checkBrackets(){
        int left = 0;
        int right = 0;
        for (int brackets = 0; brackets < expression.size(); brackets++){
            try {
                if (expression.get(brackets).equals(bracket_l)) {
                    left++;
                } else if (expression.get(brackets).equals(bracket_r)) {
                    right++;
                }
            } catch (NullPointerException e){
                continue;
            }
        }
        if (left == right){
            return true;
        }
        return false;
    }

    public int isError(String calculationString, String computationString){
        int check;
        if (computationString.equals(ERROR)){
            check = -1;
            return check;
        } else if(computationString.equals(empty) && calculationString.equals(empty)){
            check = 0;
            return check;
        } else if(computationString.equals(empty)){
            check = 1;
            return check;
        } else if (calculationString.equals(empty)){
            check = 2;
            return check;
        } else {
            check = 3;
            return check;
        }
    }

    public String setDot(String computationString){
        Pattern dot = Pattern.compile("\\.");
        Matcher mDot = dot.matcher(computationString);
        if (!mDot.find()){
            if (computationString.equals("") || computationString.equals(ERROR)){
                computationString = zero + ".";
            } else {
                computationString = computationString + ".";
            }
        }
        return computationString;
    }

    public String setPercent(String computationString){
        double calculationDouble;
        if(computationString.equals(ERROR) || computationString.equals("")){
            calculationDouble = 0D;
        } else {
            calculationDouble = Double.parseDouble(computationString) / 100;
        }
        computationString = valueOf(calculationDouble);
        return computationString;
    }

    public String getOpposite(String computationString){
        Pattern minus = Pattern.compile("-");
        Matcher min = minus.matcher(computationString);
        if(computationString.equals(ERROR)){
            computationString = "0";
        }
        if(min.find()){
            computationString = computationString.substring(one);
        } else {
            computationString = minus + computationString;
        }
        return computationString;
    }

    public String checkError(String calcString){
        if (calcString.equals(NAN) || calcString.equals(INFINITY)){
            calcString = ERROR;
        }
        return calcString;
    }

    public String replaceError(String calculationString){
        if (calculationString.equals(ERROR) || calculationString.equals(empty)){
            calculationString = zero;
        }
        return calculationString;
    }

    public String factorial(String calcString){
        int fact = Integer.parseInt(calcString);
        int temp = 1;
        for(int i = 1; i < fact; i++){
            temp = temp*i;
        }
        calcString = valueOf(temp);
        return calcString;
    }

    public String degrees(String calcString, String useless, int operation){
        Double calcDouble = Double.parseDouble(calcString);
        if (isError(calcString, useless) == -1 || isError(calcString,useless) == 1){
            calcDouble = 0D;
        }
        switch (operation){
            case 0:
                calcDouble = Math.pow(calcDouble, sqrNum);
                break;
            case 1:
                calcDouble = Math.pow(calcDouble, cubeNum);
                break;
            case 2:
                calcDouble = Math.sqrt(calcDouble);
                break;
            case 3:
                calcDouble = Math.cbrt(calcDouble);
                break;
            case 4:
                calcDouble = one/calcDouble;
                break;
            case 5:
                calcDouble = Math.pow(sqrNum, calcDouble);
                break;
            case 6:
                calcDouble = Math.pow(Math.E, calcDouble);
                break;
            case 7:
                calcDouble = Math.pow(tenNum, calcDouble);
                break;
            case 8:
                calcDouble = Math.log(calcDouble);
                break;
            case 9:
                calcDouble = Math.log10(calcDouble);
                break;
            case 10:
                calcDouble = Math.log(calcDouble)/Math.log(sqrNum);
                break;
            default:

        }
        calcString = valueOf(calcDouble);
        return calcString;
    }

    public String logs(Double memored, String calculationString, int option){
        switch (option){
            case 1:
            case 2:
            case 3:
            case 4:
        }
        return calculationString;
    }

    public void clear(){
        expression.clear();
        key = 1;
    }
}
