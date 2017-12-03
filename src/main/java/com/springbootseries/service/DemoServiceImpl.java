package com.springbootseries.service;

import com.springbootseries.exception.DemoException;
import com.springbootseries.service.Interface.DemoService;
import org.springframework.stereotype.Service;

/**
 * Created by rendong on 11/12/17.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public boolean intendedException() {
        throw new DemoException("intended to throw exception");
    }

    @Override
    public String helloWorld(String name) {
        return "hello " + name + "!";
    }


}
