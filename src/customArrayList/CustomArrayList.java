package customArrayList;


/**
 * Данный класс является домашним заданием на интенсиве по Java от компании Aston.
 * Реализация своего ArrayList(не потокобезопасные методы: добавить элемент,
 * добавить элемент по индексу, получить элемент, удалить элемент,
 * очистить всю коллекцию, отсортировать.
 *
 * @param <E> тип элементов хранящихся в массиве
 * @author Дарвеш Назар.
 */
public class CustomArrayList<E> {
    /**
     * Начальная емкость по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Текущая емкость.
     */
    private int capacity;
    /**
     * Массив объектов, для хранения элементов.
     */
    private E[] array;

    /**
     * Размер CustomArrayList (количество содержащихся в нем элементов).
     */
    private int size;

    /**
     * Создает пустой список с указанной начальной емкостью.
     * При значении емкости = 0, устанавливается значение по умолчанию.
     *
     * @param initialCapacity начальная емкость списка.
     * @throws IllegalArgumentException, если указана отрицательная начальная емкость.
     */
    @SuppressWarnings("unchecked")
    public CustomArrayList(int initialCapacity) {
        size = 0;
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
            this.array = (E[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.capacity = DEFAULT_CAPACITY;
            this.array = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }
    /**
     * Создает пустой список с начальной емкостью десять.
     */
    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    /**
     * Расширяет массив при полном заполнении.
     */
    @SuppressWarnings("unchecked")
    private void expendArray() {
        if(size == capacity) {
            int newCapacity = (capacity * 3) / 2 + 1;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = (E[]) newArray;
        }
    }
    /**
     * Вставка элемента в конец CustomArrayList.
     * При необходимости происходит расширение массива, хранящего элементы.
     * @param e элемент для вставки.
     */
    public void add(E e) {
        expendArray();
        array[size++] = e;
    }

    /**
     * Проверка индекса на корректность.
     * @param index проверяемый индекс.
     */
    private void checkIndex (int index){
        if(index >= capacity || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index +
                    " is out of bounds for array of length " + capacity);
        }
    }
    /**
     * Вставка элемента по индексу.
     * @param index индекс для вставки элемента.
     * @param e элемент для вставки в указанную позицию.
     */
    @SuppressWarnings("ReassignedVariable")
    public void add(int index, E e) {
        checkIndex(index);
        expendArray();

        Object temp = e, temp2;
        for(int i = index; i <= size; i++) {
            temp2 = array[i];
            //noinspection unchecked
            array[i] = (E) temp;
            temp = temp2;
        }
        size++;
    }
    /**
     * Полностью очищает CustomArrayList, устанавливает емкость по умолчанию.
     */
    @SuppressWarnings("unchecked")
    public void clear(){
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * @param index индекс искомого элемента.
     * @return Возвращает значение элемента по индексу.
     */
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Удаление элемента по индексу.
     * @param index индекс удаляемого элемента.
     */
    public void removeByIndex(int index) {
        checkIndex(index);
        int moved = size-index-1;
        System.arraycopy(array, index + 1, array, index, moved);
        size--;
        for (int i = size; i <= capacity; i++){
            array[i] = null;
        }
    }

    /**
     * Удаление элемента по значению.
     * @param value значение удаляемого элемента.
     */
    public void removeByValue(E value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                removeByIndex(i);
            }
        }
    }

    /**
     * Сортирует список с использованием переданного компаратора.
     * @param c компаратор для сортировки элементов списка
     */
    public void sort(MyComparator<? super E> c) {
        if (size > 1) {
            insertionSort( array, size, c);
        }
    }

    /**
     * Реализует сортировку вставками.
     * @param array массив элементов для сортировки
     * @param size  количество элементов для сортировки
     * @param c     компаратор для сравнения элементов
     */
    private void insertionSort(E[] array, int size, MyComparator<? super E> c) {
        for (int i = 1; i < size; i++) {
            E key = array[i];
            int j = i - 1;
            while (j >= 0 && c.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    /**
     * Интерфейс для сравнения элементов.
     *
     * @param <E> тип элементов, которые сравниваются
     */
    public interface MyComparator<E> {
        int compare(E o1, E o2);
    }

    /**
     * CustomToString.
     * @return возвращает строковое представление массива, находящийся "под капотом", включая null значения, для проверки работы CustomArrayList.
     */
    public String toString() {
        return "CustomArrayList{" +
                "array=" + CustomArrayList.toString(array) +
                '}';
    }

    /**
     * CustomToString.
     * @param a массив CustomArrayList.
     * @return вовзращает строковое представление массива.
     */
    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}