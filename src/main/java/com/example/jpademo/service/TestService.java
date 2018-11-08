package com.example.jpademo.service;

import com.example.jpademo.bo.Test;
import com.example.jpademo.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestService {

    @Autowired
    TestDAO dao;

    @RequestMapping("/test")
    @ResponseBody
    public Iterable<Test> test(){
        PageRequest pageRequest = new PageRequest(1,5);
        return dao.findAll(pageRequest);
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
