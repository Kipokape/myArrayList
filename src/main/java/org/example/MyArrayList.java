package org.example;

import java.util.*;

/**
 * Динамический массив, реализующий интерфейс List. Реализует следующие операции над списком:
 * добавить элемент, добавить элемент по индексу, получить элемент по индексу, удалить элемент по индексу,
 * очистить список, вставить элемент по индексу, сортировка, добавление всех элементов другой коллекции
 * по индексу, конвертация списка в массив, получение размера списка.
 * <p>
 * Класс содержит методы  автоматического изменения рамера массива для хранения внутри списка.
 * Для сортировки имеется метод, реализующий быструю сортировку
 *
 * @param <E> тип элементов списка
 */
public class MyArrayList<E> extends AbstractList<E> {

    /**
     * Стандартная начальная ёмкость
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Экземпляр пустого массива, используемый для пустых экземпляров
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * Экземпляр пустого массива, используемый для пустыых экземпляров с размером по умолчанию.
     * Необходим для того, чтобы знать на сколько нужно увеличить массив при добавлении первого элемента
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    /**
     * Массив, в котором храняться элемнты MyArrayList. Емкость MyArrayList это длина массива.
     */
    private Object[] elementData;

    /**
     * Размер MyArrayList (количество содержащихся элементов)
     */
    private int size;

    /**
     * Конструктор пустого списка с указанной начальной емкостью
     *
     * @param initialCapacity начальная емкость списка
     * @throws IllegalArgumentException если начальная ёмкость отрицательная
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Конструктор пустого списка
     *
     * @throws IllegalArgumentException если начальная ёмкость отрицательная
     */
    public MyArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    /**
     * Возвращает количество элементов в списке
     *
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Увеличивает емкость списка, чтобы он мог хранить хотя бы количестов элементов,
     * указанное в minCapacity
     *
     * @param minCapacity минимальная емкость
     * @return массив нового размера
     * @throws OutOfMemoryError если minCapacity отрицательная
     */
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = oldCapacity + 1;
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    /**
     * Вспомогательный метод, вызывающий метод увеличения емкости
     *
     * @return массив нового размера
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param e элемент, добавляемый в список
     * @return true
     */
    @Override
    public boolean add(E e) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = e;
        size++;
        return true;
    }

    /**
     * Добавление элемента в список по указанной позиции index. Сдвигает остальные элементы списка вправо
     *
     * @param index   индекс, по которому вставляется новый элемент (не замена)
     * @param element элемент, добавляемый в список
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Возвращает элемент списка по указанной позиции
     *
     * @param index индекс (позиция) возвращаемого эелемента
     * @return элемент списка по указанной позиции
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elementData[index];
    }

    /**
     * Удаляет элемент из списка по указанной позиции
     *
     * @param index индекс (позиция) удаляемого эелемента
     * @return элемент, удалённый из списка
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        @SuppressWarnings("unchecked") E oldValue = (E) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldValue;
    }

    /**
     * Очищает список от содержащихся элементов
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    /**
     * Заменяет элемент по указанной позиции в списке на другой (заменяющий)
     *
     * @param index   индекс (позиция) эелемента для замены
     * @param element элемент, который должен быть вставлен по указанной позиции
     * @return элемент, который был заменён
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        @SuppressWarnings("unchecked") E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;

    }

    /**
     * Сортировка элементов списка
     *
     * @param c Comparator используемый для сравнения элементов
     */
    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(0, size - 1, c);
    }

    /**
     * Реализация метода быстрой сортировки
     *
     * @param low  начальная позиция массива
     * @param high конечная позиция массива
     * @param c    Comparator используемый для сравнения элементов
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void quickSort(int low, int high, Comparator c) {
        if (elementData.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        Object border = elementData[middle];

        int i = low, j = high;
        while (i <= j) {
            while (c.compare(elementData[i], border) < 0) i++;
            while (c.compare(elementData[j], border) > 0) j--;
            if (i <= j) {
                Object swap = elementData[i];
                elementData[i] = elementData[j];
                elementData[j] = swap;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(low, j, c);
        if (high > i) quickSort(i, high, c);
    }

    /**
     * Вставляет все элементы из передаваеемой коллекции по указанной позиции.
     * Сдвигает все элементы после указанной вправо, новые элементы добавлятся согласно порядку,
     * в передаваемой коллекции
     *
     * @param index индекс, с которого начинают добавляться новые элементы передаваемой коллекции
     * @param c     передаваемая коллекция
     * @return true
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     * @throws NullPointerException      если передаваемая коллекция пустая
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Objects.checkIndex(index, size);

        Object[] arr = c.toArray();
        int numNew = arr.length;
        if (numNew == 0)
            return false;
        if (numNew > elementData.length - size)
            elementData = grow(size + numNew);
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        System.arraycopy(arr, 0, elementData, index, numNew);
        size += numNew;
        return true;
    }

    /**
     * Возвращает все элементы списка в виде массива
     *
     * @return массив, содержащий все элементы списка
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
}
