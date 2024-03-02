package com.engiri.modelo;

import java.util.ArrayList;

public interface InterfaceDAO {
    public int insert(Object obj);
    public int update(Object obj);
    public int delete(Object obj);
    public Object search(Object obj);
    public ArrayList getAll();
    public ArrayList getAllByName(Object obj);
    public int getCount();
}
