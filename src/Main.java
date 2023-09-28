import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();                                                                                                  //считывание выражения
        String[] data;
        char action;
        if(exp.contains(" + ")) {                                                                                                //проверка знака действия
            data = exp.split(" \\+ ");                                                                                           //разделение строки на элементы
            action = '+';                                                                                                                    //утверждение знака
        }
        else if(exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        }
        else if(exp.contains(" * ")){
            data = exp.split(" \\* ");
            action = '*';
        }
        else if (exp.contains(" / ")){
            data = exp.split(" / ");
            action = '/';
        }
        else{
            throw new Exception("Некорректный знак действия");
        }
        if (!data[0].startsWith("\"") || !data[0].endsWith("\"")) {                                                                                    //проверка на наличие кавычек в передаваемом выражении
            throw new Exception("Значение строки должно быть выделено двойными кавычками");
        }
        if(action == '*' || action == '/') {
            if (data[1].contains("\""))
                throw new Exception("Строчку можно делить или умножать только на число");                                       //проверка на "число" при умножении\делении

            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].replace("\"", "");                                                                                    //удаление кавычек циклом по двум элементам
            }
        }
        if (action == '+') {
            if (!data[0].startsWith("\"") || !data[0].endsWith("\"") || !data[1].startsWith("\"") || !data[1].endsWith("\"")) {
                throw new Exception("Значение строки должно быть выделено двойными кавычками");
            } else {
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "");
                }
                printInQuotes(data[0] + data[1]);
            }
        }
        else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);                                                                     //конвертация числа в тип int
            if (multiplier < 1 || multiplier > 10) {                                                                      //проверка с исключением на область чисел
                throw new Exception("Число должно быть больше 1 и меньше 10 включительно");
            }
            String result = "";                                                                                                     //создание "строчки" с выполненным действием циклом
            for (int i = 0; i < multiplier; i++) {
                result+=data[0];
            }
            printInQuotes(result);
        }
        else if (action == '-') {
            if (!data[0].startsWith("\"") || !data[0].endsWith("\"") || !data[1].startsWith("\"") || !data[1].endsWith("\"")) {
                throw new Exception("Значение строки должно быть выделено двойными кавычками");
            } else {
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "");
                }
            int index = data[0].indexOf(data[1]);                                                                                           //метод поиска второй строки в первой и определение местоположения индекса
            if (index == -1) {                                                                                                             //при отсутствии второй строки в первой - выводится первая строка без изменений
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index);                                                                                    //извлечение из исходной строки "подстроки"
                result += data[0].substring(index + data[1].length());                                                                //извлечение "подстроки"(при наличии) после второго выражение
                printInQuotes(result);
            }
        }
        }
        else {
            int newLen = data[0].length()/Integer.parseInt(data[1]);                                                                            //деление "длины" строки на введенное число
            if (newLen < 1 || newLen > 10) {
                throw new Exception("Число должно быть больше 1 и меньше 10 включительно");
            }
            String result = data[0].substring(0,newLen);
            printInQuotes(result);
        }


    }
    static void printInQuotes(String text) {
        String result = addEllipsis(text);
        System.out.println("\"" + result + "\"");
    }
    static String addEllipsis(String text) {                                                                                                //метод, добавляющий "..." в результат при 40+ символах
        if (text.length() > 40) {
            return text.substring(0, 40) + "...";
        }
        return text;
    }
}