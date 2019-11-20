package ru.puchkova.homework61;

import java.util.HashMap;

public class ComputationClass {
    private static final String ERROR = "ERROR";
    private static final String NAN = "NaN";
    private static final String INFINITY = "INFINITY";
    private static final String NINFINITY = "-Infinity";
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
    private static final String dot = ".";
    private HashMap<Integer, String> expression = new HashMap<>();
    private static int key = 1;
    private static final int expKey = 0;
    private String curr1;

    DoubleToString doub = new DoubleToString();

    private boolean flagFirst = false;

    public String numbers(String sNumber, String computationString) {
        expression.put(expKey, "0");
        if (computationString.equals(ERROR) || flagFirst == true || computationString.equals("0") || computationString.equals("-0")) {
            computationString = sNumber;
        } else {
            computationString = computationString + sNumber;
        }
        flagFirst = false;
        return computationString;
    }

    private void checkString() {
        String last = expression.get(key - 1);
        if (last.equals(plus) || last.equals(minus) || last.equals(multiplication) || last.equals(division)) {
            expression.remove(key - 1);
            key--;
        }
    }


    //То что срабатывает при нажатии на =
    public String equal(String computationString) {
        //добавляем ячейку "решение" 0
        expression.put(expKey, expression.get(1));

        //Если на основном экране есть данные, добавляем их в вычисления
        if (!computationString.equals(empty)) {
            expression.put(key, computationString);
            key++;
        }

        checkString();

        //Если количество открывающих скобок не равно количеству закрывающих скобок, то возвращаем ошибку
        if (!checkBrackets() || expression.isEmpty()) {
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
                            break;
                        }
                        //если находим пустую ячейку, то продолжаем
                        if (expression.get(firstBracket) == null) {
                            continue;
                        }
                    }
                    break;
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
    public void findOperations(int start, int end) {
        //если находим умножение или деление...
        for (int i = start; i <= end; i++) {
            if (expression.get(i) != null) {
                if (expression.get(i).equals(multiplication) || expression.get(i).equals(division)) {
                    //...запускаем вычисление умножения и деления
                    order(start, end, i);
                }
            }
        }
        //если не находим, то ищем сложение и вычитание и по аналогии
        for (int i = start; i <= end; i++) {
            if (expression.get(i) != null) {
                if (expression.get(i).equals(plus) || expression.get(i).equals(minus)) {
                    order(start, end, i);
                }
            }
        }
    }

    public void order(int start, int end, int operator) {
        int first = start;
        int second = end;

        for (int i = operator - 1; i >= start; i--) {
            if (expression.get(i) != null) {
                first = i;
                break;
            }
        }
        for (int i = operator + 1; i <= end; i++) {
            if (expression.get(i) != null) {
                second = i;
                break;
            }
        }
        curr1 = solution(operator, first, second);
        expression.put(expKey, curr1);
        findOperations(start, end);
    }


    public String solution(int operator, int start, int end) {
        Double number1 = Double.parseDouble(expression.get(start));
        Double number2 = Double.parseDouble(expression.get(end));
        char operation = expression.get(operator).charAt(0);
        double solution;

        switch (operation) {
            case '/':
                if (number2 == 0D) {
                    expression.clear();
                    key = 1;
                    expression.put(expKey, ERROR);
                    return ERROR;
                }
                solution = number1 / number2;
                break;
            case '*':
                solution = number1 * number2;
                break;
            case '-':
                solution = number1 - number2;
                break;
            case '+':
                solution = number1 + number2;
                break;
            default:
                solution = 0;
        }
        curr1 = doub.value(solution);
        expression.put(start, curr1);
        expression.remove(end);
        expression.remove(operator);
        return curr1;
    }


    public String setText(String sNumber, String calculationString, String computationString) {

        curr1 = expression.get(key - 1);

        int check = isError(calculationString, computationString);

        if (check == -1 || check == 0 || curr1.equals(bracket_l)) {
            calculationString = empty;
        } else if (check == 1) {
            checkString();
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString.substring(0, calculationString.length() - 1) + sNumber;
        } else if (check == 2 && !computationString.equals(empty)) {
            expression.put(key, computationString);
            key++;
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString + computationString + sNumber;
        } else {
            expression.put(key, computationString);
            key++;
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString + computationString + sNumber;
        }

        return calculationString;
    }


    public String setBracket(String sNumber, String calculationString, String computationString) {
        int check = isError(calculationString, computationString);
        if (!checkBrackets() && sNumber.equals(bracket_r)) {
            if (check == 3) {
                expression.put(key, computationString);
                key++;
                calculationString = calculationString + computationString + bracket_r;
                expression.put(key, bracket_r);
                key++;
            }
        } else if (sNumber.equals(bracket_l)) {
            if (check == 1) {
                calculationString = calculationString + computationString + bracket_l;
            } else if (check == 0 || check == -1) {
                calculationString = bracket_l;
            }
            expression.put(key, bracket_l);
            key++;
        }
        return calculationString;
    }

    public boolean checkBrackets() {
        int left = 0;
        int right = 0;
        for (int brackets = 0; brackets < expression.size(); brackets++) {
            try {
                if (expression.get(brackets).equals(bracket_l)) {
                    left++;
                } else if (expression.get(brackets).equals(bracket_r)) {
                    right++;
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        if (left == right) {
            return true;
        }
        return false;
    }

    public int isError(String calculationString, String computationString) {
        int check;
        if (computationString.equals(ERROR)) {
            check = -1;
            return check;
        } else if (computationString.equals(empty) && calculationString.equals(empty)) {
            check = 0;
            return check;
        } else if (computationString.equals(empty)) {
            check = 1;
            return check;
        } else if (calculationString.equals(empty)) {
            check = 2;
            return check;
        } else {
            check = 3;
            return check;
        }
    }

    public String setDot(String computationString) {
        char dotChar;
        computationString = replaceError(computationString);
        for (int i = 0; i < computationString.length(); i++) {
            dotChar = computationString.charAt(i);
            if (dotChar == dot.charAt(0)) {
                return computationString;
            }
        }
        computationString = computationString + dot;
        return computationString;
    }

    public String setPercent(String computationString) {
        double calculationDouble;
        computationString = replaceError(computationString);
        calculationDouble = Double.parseDouble(computationString) / 100;
        computationString = doub.value(calculationDouble);
        return computationString;
    }

    public String getOpposite(String computationString) {
        computationString = replaceError(computationString);
        char first = computationString.charAt(0);
        if (first == minus.charAt(0)) {
            computationString = computationString.substring(1);
        } else {
            computationString = minus + computationString;
        }
        return computationString;
    }

    public String checkError(String calcString) {
        if (calcString.equals(NAN) || calcString.equals(INFINITY) || calcString.equals(NINFINITY)) {
            calcString = ERROR;
        }
        return calcString;
    }

    public String replaceError(String calculationString) {
        if (calculationString.equals(ERROR) || calculationString.equals(empty)) {
            calculationString = zero;
        }
        return calculationString;
    }

    public String factorial(String calcString) {
        calcString = replaceError(calcString);
        int fact = Integer.parseInt(calcString);
        if (fact < 1) {
            return "1";
        }
        int temp = 1;
        for (int i = 1; i <= fact; i++) {
            temp = temp * i;
        }
        calcString = doub.value(temp);
        return calcString;
    }

    public String degrees(String computationString, int operation) {
        computationString = replaceError(computationString);
        Double calculationDouble = Double.parseDouble(computationString);

        switch (operation) {
            case 0:
                calculationDouble = Math.pow(calculationDouble, sqrNum);
                break;
            case 1:
                calculationDouble = Math.pow(calculationDouble, cubeNum);
                break;
            case 2:
                calculationDouble = Math.sqrt(calculationDouble);
                break;
            case 3:
                calculationDouble = Math.cbrt(calculationDouble);
                break;
            case 4:
                calculationDouble = one / calculationDouble;
                break;
            case 5:
                calculationDouble = Math.pow(sqrNum, calculationDouble);
                break;
            case 6:
                calculationDouble = Math.pow(Math.E, calculationDouble);
                break;
            case 7:
                calculationDouble = Math.pow(tenNum, calculationDouble);
                break;
            case 8:
                calculationDouble = Math.log(calculationDouble);
                break;
            case 9:
                calculationDouble = Math.log10(calculationDouble);
                break;
            case 10:
                calculationDouble = Math.log(calculationDouble) / Math.log(sqrNum);
                break;
            default:

        }
        computationString = doub.value(calculationDouble);
        computationString = checkError(computationString);
        return computationString;
    }

    public String trigonometry(String computationString, int operation, boolean flag) {
        computationString = replaceError(computationString);
        Double calculationDouble = Double.parseDouble(computationString);
        if (!flag) {
            calculationDouble = Math.toRadians(calculationDouble);
        }

        switch (operation) {
            case 0:
                calculationDouble = Math.sin(calculationDouble);
                break;
            case 1:
                calculationDouble = Math.cos(calculationDouble);
                break;
            case 2:
                calculationDouble = Math.tan(calculationDouble);
                break;
            case 3:
                calculationDouble = Math.sinh(calculationDouble);
                break;
            case 4:
                calculationDouble = Math.cosh(calculationDouble);
                break;
            case 5:
                calculationDouble = Math.tanh(calculationDouble);
                break;
            case 6:
                calculationDouble = one / Math.sin(calculationDouble);
                break;
            case 7:
                calculationDouble = one / Math.cos(calculationDouble);
                break;
            case 8:
                calculationDouble = one / Math.tan(calculationDouble);
                break;
            case 9:
                calculationDouble = one / Math.sinh(calculationDouble);
                break;
            case 10:
                calculationDouble = one / Math.cosh(calculationDouble);
                break;
            default:
                calculationDouble = one / Math.tanh(calculationDouble);
                break;

        }
        computationString = doub.value(calculationDouble);
        computationString = checkError(computationString);
        return computationString;
    }

    public String logs(Double memored, String calculationString, int option) {
        calculationString = replaceError(calculationString);
        Double calculationDouble = Double.parseDouble(calculationString);
        switch (option) {
            case 1:
                calculationDouble = Math.pow(memored, calculationDouble);
                break;
            case 2:
                calculationDouble = Math.pow(memored, one / calculationDouble);
                break;
            case 3:
                calculationDouble = Math.pow(calculationDouble, memored);
                break;
            case 4:
                calculationDouble = Math.log(memored) / Math.log(calculationDouble);
                break;
        }
        calculationString = doub.value(calculationDouble);
        calculationString = checkError(calculationString);
        return calculationString;
    }

    public void clear() {
        expression.clear();
        key = 1;
    }
}
