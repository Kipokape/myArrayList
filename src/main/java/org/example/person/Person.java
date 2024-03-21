package org.example.person;

import org.example.person.enums.Sex;

/**
 * Класс персоны для проверки MyArrayList
 */
public class Person {

    /**
     * Возраст
     */
    private int age;

    /**
     * Имя
     */
    private String name;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Пол
     */
    private Sex sex;

    /**
     * Конструктор персоны с передачей всех полей
     *
     * @param age     возраст
     * @param name    имя
     * @param surname фамилия
     * @param sex     пол
     */
    public Person(int age, String name, String surname, Sex sex) {
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
    }

    /**
     * Возвращает возраст
     *
     * @return возраст
     */
    public int getAge() {
        return age;
    }

    /**
     * Устанавливает возраст
     *
     * @param age возраст
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Возвращает имя
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя
     *
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает фамилию
     *
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Устанавливает фамилию
     *
     * @param surname фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Возвращает пол
     *
     * @return пол
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Устанавливает пол
     *
     * @param sex пол
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }


}
