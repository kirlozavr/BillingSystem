package com.application.billingsystem.entity;

import java.util.ArrayList;
import java.util.List;

/** Класс обертка для List **/
public class DataListEntity<E>{
    private final List<E> list;

    public DataListEntity() {
        list = new ArrayList<>();
    }

    public DataListEntity<E> addEntity(E entity){
        list.add(entity);
        return this;
    }

    public DataListEntity<E> removeEntity(E entity){
        list.remove(entity);
        return this;
    }

    public List<E> getList(){
        return list;
    }
}
