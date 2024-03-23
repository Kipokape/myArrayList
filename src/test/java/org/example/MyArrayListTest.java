package org.example;

import org.example.person.Person;
import org.example.person.comparators.PersonAgeComparator;
import org.example.person.comparators.PersonSexComparator;
import org.example.person.enums.Sex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MyArrayListTest {

    MyArrayList<Person> personList = new MyArrayList<>();
    MyArrayList<Person> personListWithInitialCapacity = new MyArrayList<>(6);
    Person testPerson;
    MyArrayList<Integer> testSortedNumbersList = new MyArrayList<>();
    MyArrayList<Integer> testUnsortedNumbersList = new MyArrayList<>();

    MyArrayList<Person> personListTestSort = new MyArrayList<>();

    MyArrayList<Person> emptyList = new MyArrayList<>();

    MyArrayList<Integer> testNumbersList_10000000 = new MyArrayList<>();

    MyArrayList<Integer> testNumbersList_100000 = new MyArrayList<>();

    ArrayList<Integer> testNumbersListArrayList_10000000 = new ArrayList<>();

    ArrayList<Integer> testNumbersListArrayList_100000 = new ArrayList<>();

    Person person1 = new Person(20, "Надежда", "Петрова", Sex.FEMALE);
    Person person2 = new Person(18, "Иван", "Иванов", Sex.MALE);
    Person person3 = new Person(20, "Олег", "Орлов", Sex.MALE);
    Person person4 = new Person(21, "Анастасия", "Боброва", Sex.MALE);
    Person person5 = new Person(19, "Петр", "Петров", Sex.MALE);

    ListIterator<Integer> iterator = testNumbersList_10000000.iterator();


    @Before
    public void setUp() throws Exception {
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        testPerson = new Person(19, "Иван", "Иванов", Sex.MALE);

        personListWithInitialCapacity.addAll(personList);

        personListTestSort.add(person1);
        personListTestSort.add(person2);
        personListTestSort.add(person3);
        personListTestSort.add(person4);
        personListTestSort.add(person5);

        for (int i = 0; i < 10000000; i++) {
            testNumbersList_10000000.add(i);
            testNumbersListArrayList_10000000.add(i);
        }

        for (int i = 0; i < 100000; i++) {
            testNumbersList_100000.add(i);
            testNumbersListArrayList_100000.add(i);
        }

    }

    @Test
    public void testSize() {
        Assert.assertEquals(5, personList.size());
        Assert.assertEquals(5, personListWithInitialCapacity.size());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertFalse(personList.isEmpty());
        Assert.assertTrue(emptyList.isEmpty());
    }

    @Test
    public void testContains() {
        personList.add(testPerson);
        Assert.assertFalse(emptyList.contains(testPerson));
        Assert.assertTrue(personList.contains(testPerson));
    }

    @Test
    public void testIndexOf() {
        personList.add(testPerson);
        personList.add(testPerson);
        Assert.assertEquals(-1, emptyList.indexOf(testPerson));
        Assert.assertEquals(5, personList.indexOf(testPerson));
    }

    @Test
    public void testContainsAll() {
        Assert.assertTrue(personList.containsAll(personListWithInitialCapacity));
        Assert.assertFalse(personList.containsAll(emptyList));

        personListWithInitialCapacity.add(testPerson);
        Assert.assertFalse(personList.containsAll(personListWithInitialCapacity));
    }

    @Test
    public void testRemoveAll() {
        Assert.assertTrue(personList.removeAll(personListWithInitialCapacity));
        Assert.assertFalse(personList.removeAll(emptyList));
        Assert.assertTrue(personList.isEmpty());
    }

    @Test
    public void testRemoveAll_100000() {
        Assert.assertTrue(testNumbersList_100000.removeAll(testNumbersListArrayList_100000));
    }

    @Test
    public void testRetainAll() {
        personList.add(testPerson);
        Assert.assertTrue(personList.retainAll(personListWithInitialCapacity));
        Assert.assertEquals(5, personList.size());
    }

    @Test
    public void testRetainAll_10000() {
        MyArrayList<Integer> testNumbersList_10000 = new MyArrayList<>();
        for (int i = 0; i < 10000; i++) {
            testNumbersList_10000.add(i);
        }
        Assert.assertTrue(testNumbersList_100000.retainAll(testNumbersList_10000));
        Assert.assertEquals(testNumbersList_10000.size(), testNumbersList_100000.size());
    }

    @Test
    public void testAdd() {
        Assert.assertTrue(personList.add(testPerson));
    }

    @Test
    public void testAdd_withLoop_1000000() {
        for (int i = 0; i < 1000000; i++) {
            Assert.assertTrue(testSortedNumbersList.add(i));
        }
    }

    @Test
    public void testAdd_withLoop_10000000() {
        for (int i = 0; i < 10000000; i++) {
            Assert.assertTrue(testSortedNumbersList.add(i));
        }
    }

    @Test
    public void testAddWithIndex() {
        personList.add(0, testPerson);

        Assert.assertEquals(personList.get(0), testPerson);
        Assert.assertEquals(6, personList.size());
    }

    @Test
    public void testAddWithIndex_withLoop() {
        for (int i = 0; i < 10; i++) {
            personList.add(i, testPerson);
            Assert.assertEquals(personList.get(i), testPerson);
            Assert.assertEquals(i + 6, personList.size());
        }
    }

    @Test
    public void testAddWithIndex_withLoop_1000000() {
        for (int i = 0; i < 1000000; i++) {
            personList.add(i, testPerson);
            Assert.assertEquals(personList.get(i), testPerson);
            Assert.assertEquals(i + 6, personList.size());
        }
    }

    @Test
    public void testAddWithIndex_withLoop_10000000() {
        for (int i = 0; i < 10000000; i++) {
            personList.add(i, testPerson);
            Assert.assertEquals(personList.get(i), testPerson);
            Assert.assertEquals(i + 6, personList.size());
        }
    }

    @Test
    public void testAddWithIndex_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.add(-1, testPerson));
    }

    @Test
    public void testAddWithIndex_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.add(10, testPerson));
    }

    @Test
    public void testGet() {
        personList.add(0, testPerson);

        Assert.assertEquals(testPerson, personList.get(0));
    }

    @Test
    public void testGet_withLoop() {
        for (int i = 0; i < 10; i++) {
            personList.add(i, testPerson);
            Assert.assertEquals(personList.get(i), testPerson);
        }
    }

    @Test
    public void testGet_withLoop_10000000() {
        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            int idx = random.nextInt((10000000));
            Assert.assertEquals(testNumbersList_10000000.get(idx), testNumbersListArrayList_10000000.get(idx));
        }
    }


    @Test
    public void testGet_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.get(-1));
    }

    @Test
    public void testGet_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.get(10));
    }

    @Test
    public void testGet_withEmptyListThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
    }

    @Test
    public void testRemove() {
        Person oldPerson = personList.remove(0);

        Assert.assertNotEquals(personList.get(0), oldPerson);
    }

    @Test
    public void testRemove_withLoop() {
        for (int i = 0; i < personList.size(); i++) {
            Person oldPerson = personList.remove(0);
            Assert.assertNotEquals(personList.get(0), oldPerson);
        }
    }

    @Test
    public void testRemove_withLoop_10000() {
        for (int i = 0; i < 10000; i++) {

            Integer oldNumber = testNumbersList_10000000.remove(i);
            Assert.assertNotEquals(testNumbersList_10000000.get(0), oldNumber);
        }
    }

    @Test
    public void testRemove_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.remove(-1));
    }

    @Test
    public void testRemove_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.remove(10));
    }

    @Test
    public void testRemove_withEmptyListThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
    }

    @Test
    public void testClear() {
        testNumbersList_10000000.clear();

        Assert.assertEquals(0, testNumbersList_10000000.size());
    }

    @Test
    public void testSet() {
        Person oldPerson = personList.set(0, testPerson);

        Assert.assertEquals(testPerson, personList.get(0));
        Assert.assertEquals(personListWithInitialCapacity.get(0), oldPerson);
    }

    @Test
    public void testSet_withLoop() {
        for (int i = 0; i < personList.size(); i++) {
            Person oldPerson = personList.set(i, testPerson);

            Assert.assertEquals(testPerson, personList.get(i));
            Assert.assertEquals(personListWithInitialCapacity.get(i), oldPerson);
        }
    }

    @Test
    public void testSet_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.set(-1, testPerson));
    }

    @Test
    public void testSet_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.set(10, testPerson));
    }

    @Test
    public void testSet_withEmptyListThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.set(0, testPerson));
    }

    @Test
    public void testSort_withPersonAge() {
        personListTestSort.sort(new PersonAgeComparator());

        Assert.assertEquals(person2, personListTestSort.get(0));
        Assert.assertEquals(person5, personListTestSort.get(1));
        Assert.assertEquals(person3, personListTestSort.get(2));
        Assert.assertEquals(person1, personListTestSort.get(3));
        Assert.assertEquals(person4, personListTestSort.get(4));
    }

    @Test
    public void testSort_withPersonSex() {

        personListTestSort.clear();
        personListTestSort.add(testPerson);
        personListTestSort.add(person1);

        personListTestSort.sort(new PersonSexComparator());

        Assert.assertEquals(testPerson, personListTestSort.get(0));
        Assert.assertEquals(person1, personListTestSort.get(1));
    }

    @Test
    public void testSort_withNumbers_10000() {
        for (int i = 0; i < 10000; i++) {
            testSortedNumbersList.add(i);
            testUnsortedNumbersList.add(10000 - i - 1);
        }
        testUnsortedNumbersList.sort(Comparator.naturalOrder());

        Assert.assertArrayEquals(testSortedNumbersList.toArray(), testUnsortedNumbersList.toArray());
    }

    @Test
    public void testSort_withNumbers_100000() {
        for (int i = 0; i < 100000; i++) {
            testSortedNumbersList.add(i);
            testUnsortedNumbersList.add(100000 - i - 1);
        }
        testUnsortedNumbersList.sort(Comparator.naturalOrder());

        Assert.assertArrayEquals(testSortedNumbersList.toArray(), testUnsortedNumbersList.toArray());
    }

    @Test
    public void testSort_withNumbers_1000000() {

        for (int i = 0; i < 1000000; i++) {
            testSortedNumbersList.add(i);
            testUnsortedNumbersList.add(1000000 - i - 1);
        }
        testUnsortedNumbersList.sort(Comparator.naturalOrder());

        Assert.assertArrayEquals(testSortedNumbersList.toArray(), testUnsortedNumbersList.toArray());
    }

    @Test
    public void testAddAll() {
        MyArrayList<Person> testPersonList = new MyArrayList<>();
        testPersonList.addAll(personList);

        Assert.assertArrayEquals(personList.toArray(), testPersonList.toArray());
    }

    @Test
    public void testAddAll_withNegativeIndexThrowsException() {

        Assert.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.addAll(-1, personList));
    }

    @Test
    public void testAddAll_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.addAll(10, personList));
    }

    @Test
    public void testAddAll_withEmptyListThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.addAll(0, personList));
    }

    @Test
    public void testToArray() {
        List<Person> testPersonList = new ArrayList<>(personList);

        Assert.assertArrayEquals(testPersonList.toArray(), personList.toArray());
    }

    @Test
    public void testToArray_2() {
        Person[] personArr = new Person[personList.size()];
        Person[] personArrResult = personList.toArray(personArr);

        Assert.assertArrayEquals(personList.toArray(), personArrResult);
        Assert.assertSame(personArr, personArrResult);
    }

    @Test
    public void testIteratorHasNext() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            Assert.assertTrue(iterator.hasNext());
            iterator.next();
        }
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNext_withEmptyList() {
        testNumbersList_10000000.clear();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            Assert.assertEquals((Integer) i, iterator.next());
        }
    }

    @Test
    public void testIteratorNext_withNoSuchElementException() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
        }
        Assert.assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testIteratorHasPrevious() {
        Assert.assertFalse(iterator.hasPrevious());
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
            Assert.assertTrue(iterator.hasPrevious());
        }

    }

    @Test
    public void testIteratorPrevious() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
        }
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            Assert.assertEquals((Integer) (testNumbersList_10000000.size() - i - 1), iterator.previous());
        }
    }

    @Test
    public void testIteratorPrevious_withNoSuchElementException() {
        Assert.assertThrows(NoSuchElementException.class, () -> iterator.previous());
    }

    @Test
    public void testIteratorNextIndex() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
            Assert.assertEquals(i + 1, iterator.nextIndex());
        }
    }

    @Test
    public void testIteratorPreviousIndex() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
            Assert.assertEquals(i, iterator.previousIndex());
        }
    }

    @Test
    public void testIteratorRemove() {
        for (int i = 0; i < 10000; i++) {
            iterator.next();
            iterator.remove();
        }
        Assert.assertEquals(9990000, testNumbersList_10000000.size());
    }

    @Test
    public void testIteratorRemove_withIllegalStateException() {
        Assert.assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testIteratorRemove_withConcurrentModificationException() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
        }
        testNumbersList_10000000.remove(testNumbersList_10000000.size() - 1);
        Assert.assertThrows(ConcurrentModificationException.class, () -> iterator.remove());
    }

    @Test
    public void testIteratorSet() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
            iterator.set(testNumbersList_10000000.size() - i);
            Assert.assertEquals((Integer) (testNumbersList_10000000.size() - i), iterator.previous());
            iterator.next();
        }
    }

    @Test
    public void testIteratorSet_withIllegalStateException() {
        Assert.assertThrows(IllegalStateException.class, () -> iterator.set(3));
    }

    @Test
    public void testIteratorSet_withConcurrentModificationException() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
        }
        testNumbersList_10000000.remove(testNumbersList_10000000.size() - 1);
        Assert.assertThrows(ConcurrentModificationException.class, () -> iterator.set(3));
    }

    @Test
    public void testIteratorAdd() {
        int oldSize = testNumbersList_10000000.size();
        for (int i = 0; i < 10000; i++) {
            iterator.next();
            iterator.add(oldSize - i);
            Assert.assertEquals((Integer) (oldSize - i), iterator.previous());
            iterator.next();
        }
        Assert.assertEquals(oldSize + 10000, testNumbersList_10000000.size());
    }

    @Test
    public void testIteratorAdd_withConcurrentModificationException() {
        for (int i = 0; i < testNumbersList_10000000.size(); i++) {
            iterator.next();
        }
        testNumbersList_10000000.remove(testNumbersList_10000000.size() - 1);
        Assert.assertThrows(ConcurrentModificationException.class, () -> iterator.add(2));
    }


}