package com.springbootseries.controller;

import com.springbootseries.service.Interface.DemoService;
import com.springbootseries.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rendong on 11/12/17.
 */
@RequestMapping("/")
@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResultBean<String> helloWorld(@RequestParam String name) {
        return new ResultBean<>(demoService.helloWorld(name));
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public ResultBean<Boolean> intendedException() {
        return new ResultBean<>(demoService.intendedException());
    }


}
