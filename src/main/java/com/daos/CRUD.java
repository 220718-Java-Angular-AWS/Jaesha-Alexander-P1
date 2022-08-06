package com.daos;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface CRUD<T> {
    void create(T t);

    List<T> readAll();

    void update(T t);

    void delete(T t);
}
