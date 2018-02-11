package by.yakovchik.task2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {


    String[] argument;
    List<String> listLine = new ArrayList<>();
    List<String> listSort = new ArrayList<>();
    Pattern pattern;

    public static void main(String[] args) {
        Main instanc = new Main();
        instanc.inputString();
        instanc.sorting();
        instanc.outputString();
    }

    /**
     * Метод считывает параметры программы и строки в которых нужно вести поиск
     */
    private void inputString(){

       try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
           System.out.println("Параметр программы:");
           argument = reader.readLine().split("\\s+");  // Чтение параметров

           System.out.println("Строки:");
           String line = reader.readLine();

            while (line != null & !line.equals("")) { //Выполняется до ввода пустой строки
               listLine.add(line);
               line = reader.readLine();
           }

       } catch (IOException ex) {
           ex.printStackTrace();
       }
    }

    /**
     * Метод в которм идет поиск строк содержащих одно из слов введенное в качестве параметра программы
     */
    private void sorting(){
        Matcher matcher;

        for (int i = 0; i < listLine.size(); i++ ){                    // Перебирает все строки
            String[] tmp = listLine.get(i).split("\\s+");       // Строка разбивается на отдельные слова
            for(int x = 0 ; x <tmp.length; x++ ) {                     // Перебирает все слова из ранее разбитой строки

                for (String s: argument) {                             //Перебирает слова введеные как параметры
                    if (checkReg(s)){                                  //Если слово можно записать как регулярное выражение
                        matcher = pattern.matcher(tmp[x]);
                        if (matcher.matches()){
                            listSort.add(listLine.get(i));
                            x = tmp.length;
                            break;
                        }
                    } else {                                            //Eсли слово не прошло как регулярное выражение
                        if (s.equals(tmp[x])){
                            listSort.add(listLine.get(i));
                            x = tmp.length;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Метод выводит результат работы программы
     */
    private void outputString(){
        System.out.println("Вывод:");
        for (String str: listSort) System.out.println(str);
    }


    /**
     * Метод проверяет является ли слово валидным регулярным выражением
     */
    private boolean checkReg(String str){
        try {
            pattern = Pattern.compile(str);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

}
