package com.java.java_15.demo.service;

import java.util.List;

public interface TableService<T> {
    void create(T t);

    List<T> readAll();

    T read(Long id);

    Boolean update(T t, Long id);

    Boolean delete(Long id);

}
