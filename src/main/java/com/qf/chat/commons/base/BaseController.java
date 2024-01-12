package com.qf.chat.commons.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


public class BaseController<T> {

    @Autowired
    protected T service;

    protected T getBaseService(){
        return service;
    }
}
