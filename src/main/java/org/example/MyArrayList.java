package org.example;

import java.util.*;

/**
 * Динамический массив, реализующий интерфейс Collection. Реализует следующие операции над списком:
 * добавить элемент; добавить элемент по индексу; получить элемент по индексу; удалить элемент по индексу;
 * очистить список; вставить элемент по индексу; сортировка; добавление всех элементов другой коллекции
 * по индексу; конвертация списка в массив; конвертация списка в передаваемый массив; получение размера списка;
 * добавление всех элементов другой коллекции; проверка на наличие элементов в списке; поиск наличия элемента в списке;
 * поиск индекса элемента в списке; итератор; проверка наличия всех элементов другой коллекции в списке; удаление всех
 * элементов списка, которые содержаться в передаваемой коллекции; замена всех элементов списка на те, которые
 * содержатся в другой коллекции.
 *
 * <p>
 * Класс содержит методы автоматического изменения размера массива для хранения внутри списка.
 * Для сортировки имеется метод, реализующий быструю сортировку
 *
 * @param <E> тип элементов списка
 */
public class MyArrayList<E> implements Collection<E> {

    /**
     * Стандартная начальная ёмкость
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Экземпляр пустого массива, используемый для пустых экземпляров
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * Экземпляр пустого массива, используемый для пустых экземпляров с размером по умолчанию.
     * Необходим для того, чтобы знать на сколько нужно увеличить массив при добавлении первого элемента
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    /**
     * Массив, в котором хранятся элементы MyArrayList. Емкость MyArrayList это длина массива.
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
     * Конструктор списка, содержащий элементы передаваемой коллекции
     *
     * @param c передаваемая коллекция, элементы которой должны быть скопированы в список
     * @throws NullPointerException если передаваемая коллекция пустая
     */
    public MyArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENT_DATA;
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
     * Возвращает true если список пустой
     *
     * @return true если список пустой
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает true если передаваемый элемент содержится в списке
     *
     * @param o передаваемый элемент для проверки наличия в списке
     * @return true если элемент содержится в списке
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Возвращает индекс первого вхождения передаваемого элемента в списке,
     * если его нет в списке, возвращает -1
     *
     * @param o передаваемый элемент для получения индекса в списке
     * @return индекс передаваемого элемента в списке, -1 если элемента нет
     */
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    /**
     * Возвращает индекс первого вхождения передаваемого элемента в списке,
     * если его нет в списке, возвращает -1
     *
     * @param o     передаваемый элемент для получения индекса в списке
     * @param start начальная позиция поиска
     * @param end   конечная позиция поиска
     * @return индекс передаваемого элемента в списке, -1 если элемента нет
     */
    private int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elementData == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    /**
     * Возвращает итератор списка
     *
     * @return итератор списка
     */
    @Override
    public ListIterator<E> iterator() {
        return new MyArrayListIterator(0);
    }

    /**
     * Увеличивает емкость списка, чтобы он мог хранить хотя бы количество элементов,
     * указанное в minCapacity
     *
     * @param minCapacity минимальная емкость
     * @return массив нового размера
     * @throws OutOfMemoryError если minCapacity отрицательная
     */
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = oldCapacity + oldCapacity / 2;
            elementData = Arrays.copyOf(elementData, newCapacity);
            return elementData;
        } else {
            elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
            return elementData;
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
     * Возвращает результат проверки того, содержится ли передаваемая коллекция в списке
     *
     * @param c передаваемая коллекция
     * @return true, если все элементы передаваемой коллекции содержатся в списке, иначе false
     * @throws NullPointerException если передаваемая коллекция не инициализирована
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        if (c.isEmpty() && !isEmpty()) return false;
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    /**
     * Удаляет из списка все элементы, содержащиеся в передаваемой коллекции
     *
     * @param c передаваемая коллекция, элементы которой должны быть удалены из списка
     * @return true, если элементы передаваемой коллекции удалены из списка, иначе false
     * @throws NullPointerException если передаваемая коллекция не инициализирована
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * @param c передаваемая коллекция для замены
     * @return true, если произошла замена
     * @throws NullPointerException если передаваемая коллекция не инициализирована
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Добавление элемента в список по указанной позиции index. Сдвигает остальные элементы списка вправо
     *
     * @param index   индекс, по которому вставляется новый элемент (не замена)
     * @param element элемент, добавляемый в список
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
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
     * @param index индекс (позиция) возвращаемого элемента
     * @return элемент списка по указанной позиции
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elementData[index];
    }

    /**
     * Удаляет элемент из списка по указанной позиции
     *
     * @param index индекс (позиция) удаляемого элемента
     * @return элемент, удалённый из списка
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
    public E remove(int index) {
        Objects.checkIndex(index, size);
        @SuppressWarnings("unchecked") E oldValue = (E) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldValue;
    }

    /**
     * Удаляет первое вхождение передаваемого элемента
     *
     * @param o удаляемый объект
     * @return true, если элемент удалён, иначе false
     * @throws NullPointerException если передаваемый объект не инициализирован
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException();

        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
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
     * @param index   индекс (позиция) элемента для замены
     * @param element элемент, который должен быть вставлен по указанной позиции
     * @return элемент, который был заменён
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     */
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

        int i = low;
        int j = high;
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
     * Вставляет все элементы из передаваемой коллекции в список.
     * Новые элементы добавляются в конец списка согласно порядку, в передаваемой коллекции
     *
     * @param c передаваемая коллекция
     * @return true
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     * @throws NullPointerException      если передаваемая коллекция пустая
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] arr = c.toArray();
        int numNew = arr.length;
        if (numNew == 0)
            return false;
        if (numNew > elementData.length - size)
            elementData = grow(size + numNew);
        System.arraycopy(arr, 0, elementData, size, numNew);
        size += numNew;
        return true;
    }

    /**
     * Вставляет все элементы из передаваемой коллекции по указанной позиции в список.
     * Сдвигает все элементы после указанной вправо, новые элементы добавляются согласно порядку,
     * в передаваемой коллекции
     *
     * @param index индекс, с которого начинают добавляться новые элементы передаваемой коллекции
     * @param c     передаваемая коллекция
     * @return true
     * @throws IndexOutOfBoundsException если index выходит за пределы размера списка, или он отрицательный
     * @throws NullPointerException      если передаваемая коллекция пустая
     */
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
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Возвращает массив, содержащий все элементы в этом списке в правильной последовательности.
     * Тип возвращаемого массива является типом передаваемого массива. Если список помещается в массив,
     * то он возвращается в нем, иначе для этой цели выделяется новый массив того же типа
     *
     * @param a   массив, в который должны быть сохранены элементы этого списка, если он
     *            достаточно большой; в противном случае для этой цели выделяется новый
     *            массив того же типа
     * @param <T> тип элементов массива
     * @return массив, содержащий все элементы списка
     * @throws NullPointerException если передаваемый массив пустой
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * Итератор списка, реализующий интерфейс ListIterator.
     * Содержит основные методы итератора: наличие следующего элемента; следующий элемент;
     * наличие предыдущего элемента; предыдущий элемент; индекс следующего элемента;
     * индекс предыдущего элемента; удаление элемента, вставка (замена) элемента;
     * добавление элемента.
     */
    private class MyArrayListIterator implements ListIterator<E> {

        /**
         * Индекс элемента, который вернёт следующий next
         */
        private int cursor;

        /**
         * Индекс последнего элемента, возвращённого next или previous, устанавливается -1,
         * если этот элемент был удалён remove
         */
        int lastRet = -1;

        /**
         * Конструктор итератора
         *
         * @param index индекс элемента, который вернёт следующих next
         */
        MyArrayListIterator(int index) {
            cursor = index;
        }

        /**
         * Возвращает true если курсор не дошёл до конца списка
         *
         * @return true если курсор не дошёл до конца списка
         */
        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        /**
         * Возвращает следующий элемент списка
         *
         * @return следующий элемент списка
         * @throws NoSuchElementException если курсор дошел до конца списка
         */
        @Override
        public E next() {
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException(e);
            }
        }

        /**
         * Возвращает true, если есть предыдущий элемент до курсора
         *
         * @return true, если есть предыдущий элемент до курсора
         */
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        /**
         * Возвращает предыдущий элемент списка
         *
         * @return предыдущий элемент списка
         * @throws NoSuchElementException если курсор указывает на первый элемент списка
         */
        @Override
        public E previous() {
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException(e);
            }
        }

        /**
         * Возвращает индекс следующего элемента в списке
         *
         * @return индекс следующего элемента в списке
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * Возвращает индекс предыдущего элемента в списке
         *
         * @return индекс предыдущего элемента в списке
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * Удаляет элемент списка, на который указывает курсор
         *
         * @throws IllegalStateException           если курсор не вернул элемент через next или previous,
         *                                         или он уже был удалён remove
         * @throws ConcurrentModificationException если последний возвращённый элемент выходит за границы списка,
         *                                         т.е. размер списка был изменён
         */
        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                MyArrayList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Заменяет элемент, на который указывает курсор, передаваемым элементом
         *
         * @param e передаваемый элемент который нужно вставить в список
         * @throws IllegalStateException           если курсор не вернул элемент через next или previous,
         *                                         или он уже был удалён remove
         * @throws ConcurrentModificationException если последний возвращённый элемент выходит за границы списка,
         *                                         т.е. размер списка был изменён
         */
        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Добавляет элемент в список по индексу, на который указывает курсор
         *
         * @param e передаваемый элемент для добавления в список
         * @throws ConcurrentModificationException если курсор выходит за границы списка,
         *                                         т.е. размер списка был изменён
         */
        @Override
        public void add(E e) {
            try {
                int i = cursor;
                MyArrayList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
