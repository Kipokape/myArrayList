package org.example.person.comparators;

import org.example.person.Person;

import java.util.Comparator;

/**
 * Компаратор для сравнения Person по полу
 */
public class PersonSexComparator implements Comparator<Person> {

    /**
     * Сравнивает два объекта Person по полу
     *
     * @param o1 первая персона для сравнения
     * @param o2 вторая персона для сравнения
     * @return результат сравнения полов персон
     */
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getSex().compareTo(o2.getSex());
    }
}
