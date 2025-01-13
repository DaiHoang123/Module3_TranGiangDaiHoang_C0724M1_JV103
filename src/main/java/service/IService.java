package service;

import java.util.Date;
import java.util.List;

public interface IService<T> {
    List<T> getAll();
    
    boolean add(T t);
    
    void remove(String id);

    boolean isMatBangExists(String maMB);
    
    List<T> search(String loaiMatBang, Integer tang, Date ngayBD, Date ngayKT);
}