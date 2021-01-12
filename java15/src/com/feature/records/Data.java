package com.feature.records;

import java.util.Objects;

public record Data(Integer age, String name) {
    public Data {
        Objects.requireNonNull(age);
        Objects.requireNonNull(name);
    }

    public Data(String name) {
        this(10, name);
    }

    /*public Data(Integer age, String name) {
        this.age = age;
        this.name = name;
    }*/
}
