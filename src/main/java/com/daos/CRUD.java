package com.daos;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface CRUD<T> {
    boolean create(T t);

    List<T> readAll();

    Boolean update(T t);

    Boolean delete(T t);
}
