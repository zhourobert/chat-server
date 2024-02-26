package com.robertZhou.chat.commons.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


public class BaseController<T> {

    @Autowired
    protected T service;

    protected T getService(){
        return service;
    }
}
