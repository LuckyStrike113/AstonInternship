package customList;

/**
 * Интерфейс для сравнения элементов.
 * @param <E> тип элементов, которые сравниваются
 */
public interface MyComparator<E> {
    int compare(E o1, E o2);
}
