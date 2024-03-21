package org.example.person.comparators;

import org.example.person.Person;

import java.util.Comparator;

/**
 * Компаратор для сравнения Person по возрасту
 */
public class PersonAgeComparator implements Comparator<Person> {


    /**
     * Сравнивает два объекта Person по возрасту
     *
     * @param o1 первая персона для сравнения
     * @param o2 вторая персона для сравнения
     * @return результат сравнения возрастов персон
     */
    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
