package com.will.pokemontrainerhub.trainer;

import com.will.pokemontrainerhub.Enums.Gender;

import javax.persistence.Column;
import java.util.Objects;

public class TrainerReqBody {
    private String name;
    private Integer age;
    private Gender gender;

    public TrainerReqBody() {
    }

    public TrainerReqBody(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerReqBody that = (TrainerReqBody) o;
        return Objects.equals(name, that.name) && Objects.equals(age, that.age) && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }

    @Override
    public String toString() {
        return "TrainerReqBody{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
