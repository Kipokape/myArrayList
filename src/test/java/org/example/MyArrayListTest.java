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

    List<Person> personList = new MyArrayList<>();
    List<Person> personListWithInitialCapacity = new MyArrayList<>(6);
    Person testPerson;
    List<Integer> testSortedNumbersList = new ArrayList<>();
    List<Integer> testUnsortedNubmersList = new ArrayList<>();

    List<Person> testPersonList = new MyArrayList<>();

    List<Person> personListTestSort = new MyArrayList<>();

    Person person1 = new Person(20, "Надежда", "Петрова", Sex.FEMALE);
    Person person2 = new Person(18, "Иван", "Иванов", Sex.MALE);
    Person person3 = new Person(20, "Олег", "Орлов", Sex.MALE);
    Person person4 = new Person(21, "Анастасия", "Боброва", Sex.MALE);
    Person person5 = new Person(19, "Петр", "Петров", Sex.MALE);


    @Before
    public void setUp() throws Exception {
        personList.add(new Person(18, "Иван", "Иванов", Sex.MALE));
        personList.add(new Person(19, "Петр", "Петров", Sex.MALE));
        personList.add(new Person(20, "Олег", "Орлов", Sex.MALE));
        personList.add(new Person(20, "Надежда", "Петрова", Sex.FEMALE));
        personList.add(new Person(21, "Анастасия", "Боброва", Sex.MALE));

        testPerson = new Person(19, "Иван", "Иванов", Sex.MALE);

        personListWithInitialCapacity.addAll(personList);

        personListTestSort.add(person1);
        personListTestSort.add(person2);
        personListTestSort.add(person3);
        personListTestSort.add(person4);
        personListTestSort.add(person5);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(5, personList.size());
        Assert.assertEquals(5, personListWithInitialCapacity.size());
    }

    @Test
    public void testSize_withLoop() {
        for (int i = 0; i < 10; i++) {
            testPersonList.add(testPerson);
        }
        Assert.assertEquals(10, testPersonList.size());
    }


    @Test
    public void testAdd() {
        Assert.assertTrue(personList.add(testPerson));
    }

    @Test
    public void testAdd_withLoop() {
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(personList.add(testPerson));
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
    public void testGet_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.get(-1));
    }

    @Test
    public void testGet_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.get(10));
    }

    @Test
    public void testGet_withEmptyListThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> new MyArrayList<>().get(0));
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
    public void testRemove_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.remove(-1));
    }

    @Test
    public void testRemove_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> personList.remove(10));
    }

    @Test
    public void testRemove_withEmptyListThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> new MyArrayList<>().remove(0));
    }

    @Test
    public void testClear() {
        personList.clear();

        Assert.assertEquals(0, personList.size());
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
        assertThrows(IndexOutOfBoundsException.class, () -> new MyArrayList<>().set(0, testPerson));
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
            testUnsortedNubmersList.add(10000 - i - 1);
        }
        testUnsortedNubmersList.sort(Comparator.naturalOrder());

        Assert.assertArrayEquals(testSortedNumbersList.toArray(), testUnsortedNubmersList.toArray());
    }

    @Test
    public void testSort_withNumbers_100000() {
        for (int i = 0; i < 100000; i++) {
            testSortedNumbersList.add(i);
            testUnsortedNubmersList.add(100000 - i - 1);
        }
        testUnsortedNubmersList.sort(Comparator.naturalOrder());

        Assert.assertArrayEquals(testSortedNumbersList.toArray(), testUnsortedNubmersList.toArray());
    }

    @Test
    public void testSort_withNumbers_1000000() {

        for (int i = 0; i < 1000000; i++) {
            testSortedNumbersList.add(i);
            testUnsortedNubmersList.add(1000000 - i - 1);
        }
        testUnsortedNubmersList.sort(Comparator.naturalOrder());

        Assert.assertArrayEquals(testSortedNumbersList.toArray(), testUnsortedNubmersList.toArray());
    }

    @Test
    public void testAddAll() {
        List<Person> testPersonList = new MyArrayList<>();
        testPersonList.addAll(personList);

        Assert.assertArrayEquals(personList.toArray(), testPersonList.toArray());
    }

    @Test
    public void testAddAll_withNegativeIndexThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> new MyArrayList<>().addAll(-1, personList));
    }

    @Test
    public void testAddAll_withIndexGreaterThanSizeThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> new MyArrayList<>().addAll(10, personList));
    }

    @Test
    public void testAddAll_withEmptyListThrowsException() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> new MyArrayList<>().addAll(0, personList));
    }

    @Test
    public void testToArray() {
        List<Person> testPersonList = new ArrayList<>(personList);

        Assert.assertArrayEquals(testPersonList.toArray(), personList.toArray());
    }
}