package customLinkedList;

import customList.CustomList;

/**
 * Данный класс является домашним заданием на интенсиве по Java от компании Aston.
 * Реализация своего LinkedList(не потокобезопасные методы: добавить элемент, добавить элемент по индексу, получить элемент, удалить элемент,
 * очистить всю коллекцию, отсортировать.
 *
 * @param <E> тип элементов хранящихся в коллекции
 * @author Дарвеш Назар.
 */
public class CustomLinkedList<E> implements CustomList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Внутренний класс, представляющий узел в связном списке.
     * @param <E> тип данных, хранимый в узле.
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    /**
     * Конструктор, создается пустой CustomLinkedList.
     */
    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Добавляет элемент в конец CustomLinkedList.
     * @param e добавляемый элемент.
     */
    public void add(E e) {
        if (head == null && tail == null) {
            head = new Node<>(null, e, null);
            tail = head;
        } else if (head == tail){
            tail = new Node<>(head, e, null);
            head.next = tail;
        } else {
            Node<E> temp = tail;
            tail = new Node<>(temp, e, null);
            temp.next = tail;
        }
        size++;
    }
    /**
     * Проверка индекса на корректность.
     * @param index проверяемый индекс.
     */
    private void checkIndex (int index){
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size);
        }
    }
    /**
     * Возвращает узел по указанному индексу.
     * @param index индекс узла, который нужно вернуть.
     * @return узел по указанному индексу.
     */
    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    /**
     * Добавляет элемент по индексу.
     * @param index индекс, по которому вставляется элемент.
     * @param e добавляемый элемент.
     */
    public void add(int index, E e) {
        checkIndex(index);
        Node<E> temp = getNode(index);
        Node<E> tempPrev = temp.prev;
        Node<E> newE = new Node<>(temp.prev, e, temp);
        if(tempPrev != null) {
            tempPrev.next = newE;
        }
        temp.prev = newE;
        if (index == 0) {
            head = newE;
        }
        size++;
    }

    /**
     * Получение элемента по индексу.
     * @param index индекс элемента, который нужно получить.
     * @return элемент по указанному индексу.
     */
    public E get(int index) {
        return getNode(index).element;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удоляемого элемента.
     */
    public void remove(int index) {
        checkIndex(index);
        Node<E> removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<E> prevNode = getNode(index - 1);
            removedNode = prevNode.next;
            prevNode.next = removedNode.next;
            if (removedNode.next == null) {
                tail = prevNode;
            }
        }
        size--;
    }

    /**
     * Очищает список.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Сортирует список с использованием переданного компаратора.
     * @param c компаратор для сортировки элементов списка.
     */
    public void sort(MyComparator<? super E> c) {
        if (size > 1) {
            head = mergeSort(head, c);
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            tail = current;
        }
    }

    /**
     * Сортировки слиянием - Merge Sort.
     * @param head начальный узел подсписка для сортировки.
     * @param c компаратор для сравнения элементов.
     * @return отсортированный список.
     */
    private Node<E> mergeSort(Node<E> head, MyComparator<? super E> c) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<E> middle = getMiddle(head);
        Node<E> nextOfMiddle = middle.next;
        middle.next = null;
        Node<E> left = mergeSort(head, c);
        Node<E> right = mergeSort(nextOfMiddle, c);
        return sortedMerge(left, right, c);
    }

    /**
     * Объединяет два отсортированных списка.
     * @param left левый подсписок.
     * @param right правый подсписок.
     * @param c компаратор для сравнения элементов.
     * @return объединенный отсортированный список.
     */
    private Node<E> sortedMerge(Node<E> left, Node<E> right, MyComparator<? super E> c) {
        Node<E> result;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (c.compare(left.element, right.element) <= 0) {
            result = left;
            result.next = sortedMerge(left.next, right, c);
        } else {
            result = right;
            result.next = sortedMerge(left, right.next, c);
        }
        return result;
    }

    /**
     * Находит средний узел списка.
     * @param head начальный узел списка.
     * @return средний узел списка.
     */
    private Node<E> getMiddle(Node<E> head) {
        if (head == null) {
            return head;
        }
        Node<E> slow = head;
        Node<E> fast = head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    /**
     * Возвращает количество элементов в списке.
     * @return количество элементов в списке.
     */
    public int size() {
        return size;
    }

    /**
     * Интерфейс для сравнения элементов.
     * @param <E> тип элементов, которые сравниваются
     */
    public interface MyComparator<E> {
        int compare(E o1, E o2);
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        Node<E> current = head;
        if (current != null){
            list.append(current.element).append(" ");
        } else return "Список пуст";
        while (current.next != null) {
            current = current.next;
            list.append(current.element).append(" ");
        }
        return String.valueOf(list);
    }
}
