package com.example.jpademo.dao;

import com.example.jpademo.bo.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO extends PagingAndSortingRepository<Test,Integer> {

}
