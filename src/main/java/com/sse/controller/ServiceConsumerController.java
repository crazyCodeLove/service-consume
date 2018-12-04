package com.sse.controller;

import com.sse.api.StudentServiceApi;
import com.sse.model.RequestParamBase;
import com.sse.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pczhao
 * @email
 * @date 2018-11-05 21:49
 */

@RestController
public class ServiceConsumerController {

    @Autowired
    private StudentServiceApi client;

    @RequestMapping(value = "/consume", method = RequestMethod.GET)
    public void consume() {
        Response result = client.getStudent(RequestParamBase.builder().id(24).name("sse").build());
        System.out.println(result);
    }


}
