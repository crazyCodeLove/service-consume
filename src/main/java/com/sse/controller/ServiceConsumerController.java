package com.sse.controller;

import com.sse.api.StudentServiceApi;
import com.sse.model.RequestParamHolder;
import com.sse.model.ResponseResultHolder;
import com.sse.model.student.Fruit;
import com.sse.model.student.StudentParam;
import com.sse.model.student.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

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
        int num = 6;
        StudentParam[] stuParams = new StudentParam[num];
        for (int i = 0; i < num; i++) {
            stuParams[i] = new StudentParam();
        }
        stuParams[0].setId(15);
        stuParams[0].setName("good");
        ArrayList<Fruit> fruits0 = new ArrayList<>();
        stuParams[0].setFruits(fruits0);
        fruits0.add(Fruit.builder().price(15).color("red").build());

        stuParams[1].setId(15);
//        params[1].setName("good");
        ArrayList<Fruit> fruits1 = new ArrayList<>();
        stuParams[1].setFruits(fruits1);
        fruits1.add(Fruit.builder().price(15).color("red").build());

        stuParams[2].setId(15);
        stuParams[2].setName("good");
        ArrayList<Fruit> fruits2 = new ArrayList<>();
        stuParams[2].setFruits(fruits2);
//        fruits2.add(Fruit.builder().price(15).color("red").build());

        stuParams[3].setId(15);
        stuParams[3].setName("good");
        ArrayList<Fruit> fruits3 = new ArrayList<>();
        stuParams[3].setFruits(fruits3);
        fruits3.add(Fruit.builder().color("red").build());

        stuParams[4].setId(15);
        stuParams[4].setName("good");
        ArrayList<Fruit> fruits4 = new ArrayList<>();
        stuParams[4].setFruits(fruits4);
        fruits4.add(Fruit.builder().price(15).build());

//        params[5].setId(15);
        stuParams[5].setName("good");
        ArrayList<Fruit> fruits5 = new ArrayList<>();
        stuParams[5].setFruits(fruits5);
        fruits5.add(Fruit.builder().price(15).color("red").build());

        RequestParamHolder<StudentParam>[] requestHolders = new RequestParamHolder[num];
        for (int i = 0; i < num; i++) {
            requestHolders[i] = new RequestParamHolder<>();
            requestHolders[i].setParam(stuParams[i]);
        }
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            ResponseResultHolder<StudentResponse> result = client.getStudent(requestHolders[random.nextInt(num)]);
            System.out.println((i + 1) + ":" + result.toString());

        }

    }


}
