package customList;

public interface CustomList<E> {
    /**
     * Возвращает количество элементов в этом списке.
     * @return количество элементов.
     */
    int size();

    /**
     * Добавляет элемент в конец списка.
     */
    void add(E e);

    /**
     * Добавляет элемент по указанному индексу.
     * @param index индекс.
     * @param e добавляемый элемент.
     */
    void add(int index, E e);

    /**
     * Возвращает элемент коллекции по индексу.
     * @param index индекс.
     * @return элемент коллекции.
     */
    E get(int index);

    /**
     * Очищает всю коллекцию.
     */
    void clear();

    /**
     * Удаляет элемент коллекции по индексу.
     * @param index индекс.
     */
    void remove(int index);

}
