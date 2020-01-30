package com.homes.service;

public interface ReadWriteFile<T> {

    boolean storeData(int i, double d);
    T retrieveData(int i);
    T retrieveReport(String str);

    boolean insert(String str);
}
