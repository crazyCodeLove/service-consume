package com.sse.controller;

import com.sse.api.StudentServiceApi;
import com.sse.model.ResponseBase;
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
        StudentParam[] params = new StudentParam[num];
        for (int i = 0; i < num; i++) {
            params[i] = new StudentParam();
        }
        params[0].setId(15);
        params[0].setName("good");
        ArrayList<Fruit> fruits0 = new ArrayList<>();
        params[0].setFruits(fruits0);
        fruits0.add(Fruit.builder().price(15).color("red").build());

        params[1].setId(15);
//        params[1].setName("good");
        ArrayList<Fruit> fruits1 = new ArrayList<>();
        params[1].setFruits(fruits1);
        fruits1.add(Fruit.builder().price(15).color("red").build());

        params[2].setId(15);
        params[2].setName("good");
        ArrayList<Fruit> fruits2 = new ArrayList<>();
        params[2].setFruits(fruits2);
//        fruits2.add(Fruit.builder().price(15).color("red").build());

        params[3].setId(15);
        params[3].setName("good");
        ArrayList<Fruit> fruits3 = new ArrayList<>();
        params[3].setFruits(fruits3);
        fruits3.add(Fruit.builder().color("red").build());

        params[4].setId(15);
        params[4].setName("good");
        ArrayList<Fruit> fruits4 = new ArrayList<>();
        params[4].setFruits(fruits4);
        fruits4.add(Fruit.builder().price(15).build());

//        params[5].setId(15);
        params[5].setName("good");
        ArrayList<Fruit> fruits5 = new ArrayList<>();
        params[5].setFruits(fruits5);
        fruits5.add(Fruit.builder().price(15).color("red").build());

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            ResponseBase result = client.getStudent(params[random.nextInt(num)]);
            System.out.println((i + 1) + ":" + result.toString());

        }

    }


}
