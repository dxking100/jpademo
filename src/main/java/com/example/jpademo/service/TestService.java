package com.example.jpademo.service;

import com.example.jpademo.bo.Test;
import com.example.jpademo.dao.TestDAO;
import org.hibernate.SQLQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.Future;

@RestController
public class TestService {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    TestDAO dao;

    @RequestMapping("/test")
    @ResponseBody
    public Iterable<Test> test(){
        PageRequest pageRequest = new PageRequest(0,5);
        return dao.findAll(pageRequest);
    }

    @RequestMapping("/test1")
    @ResponseBody
    public Iterable<Test> test1(){
        //PageRequest pageRequest = new PageRequest(1,5);
        return dao.findByName("victor");
    }

    @RequestMapping("/test2")
    @ResponseBody
    public Test test2(){
        //PageRequest pageRequest = new PageRequest(1,5);
        return dao.getByName("victor");
    }

    @RequestMapping("/test3")
    @ResponseBody
    public Iterable<Test> test3(){

        Test t = new Test();
        t.setAge(32);
        t.setName("victor");

        dao.save(t);

        return dao.findAll();
    }

    @RequestMapping("/test4")
    @ResponseBody
    public List test4(){
        //支持原生的sql，並裝為json
        Query q = entityManager.createNativeQuery("select id,name from test").unwrap(NativeQueryImpl.class).setResultTransformer(
                AliasToEntityMapResultTransformer
                        .INSTANCE
        );
        return q.getResultList();
    }


    @RequestMapping("/save")
    @ResponseBody
    public Test save(){
        Test t = new Test();
        t.setAge(32);
        t.setName("victor");
        return dao.save(t);
    }
}
