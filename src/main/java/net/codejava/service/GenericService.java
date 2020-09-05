package net.codejava.service;

import java.util.List;

public interface GenericService<T> {
	
    List<T> findAll();
	
    T save(T t);
	
    T findOne(Long id);
    
    String delete(Long id);

}
