package com.homes.repository;


public interface ObjectRepository<T>
{
    boolean store(int i, double d);
    T retrieve(int id);
    T retrieveReport(String duration);
    String addVillage(String village_name);
}
