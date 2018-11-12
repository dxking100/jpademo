package com.example.jpademo.dao;

import com.example.jpademo.bo.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

@Repository
public interface TestDAO extends PagingAndSortingRepository<Test,Integer> {
    public Iterable<Test> findByName(String name);
    public Test getByName(String name);

    public Page<Test> findAllByName(String name, Pageable pageable);

    //spel取得對象name
    @Query("select t from #{#entityName} t")
    public List<Test> getTest();

    //Modifying 這個一定要用，應為他會刷新一級cache, 當你設置了一級cache時
    @Modifying
    @Query("update #{#entityName} t set t.age = 10")
    public int updateTest();

    @Query(value = "select id,name from test",nativeQuery = true,name = "ItemResults")
    public List<Test> getTesta();

}
