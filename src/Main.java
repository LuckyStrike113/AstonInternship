import customArrayList.CustomArrayList;
import customLinkedList.CustomLinkedList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> num = new CustomArrayList<>(5); // создание CustomArrayList, задаем размер массива через конструктор
        num.add(4);
        num.add(3);
        num.add(2,4);
        num.add(1);//заполнены первые 4 элемента, 5ый null
        System.out.println(num);// вывод в консоль
        num.sort(Integer::compareTo);//сортировка с помощью компоратора Integer
        System.out.println(num);// вывод в консоль
        num.add(1, 2); // вставка элемента по индексу, в данный момент весь массив заполнен
        System.out.println(num);
        num.add(10); // при добавлении нового элемента происходит расширение массива в 1,5 раза: (capacity*2)/3+1
        System.out.println(num);
        System.out.println(num.get(3)); // получение элемента по индексу
        num.removeByValue(4); // удаление по значению
        System.out.println(num);
        num.remove(3); // удаление по индексу
        System.out.println(num);
        num.clear();//очистка коллекции, размер массива = рамеру по умолчанию (10)
        System.out.println(num);

        CustomLinkedList<String> linkedStr = new CustomLinkedList<>();
        linkedStr.add("Zoo");
        linkedStr.add("Bee");
        linkedStr.add("123");
        linkedStr.add("bear"); //добавление элементов в конец списка
        System.out.println(linkedStr);
        linkedStr.add(2, "Boo"); // добавление элемента по индексу
        System.out.println(linkedStr);
        linkedStr.sort(String::compareTo); // сортировка списка
        System.out.println(linkedStr);
        System.out.println(linkedStr.get(3)); // получение элемента по индексу
        linkedStr.remove(3); // удаление элемента по индексу
        System.out.println(linkedStr);
        linkedStr.clear(); // очистка списка
        System.out.println(linkedStr);
    }
}