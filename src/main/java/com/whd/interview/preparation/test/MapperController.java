package com.whd.interview.preparation.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whd.java@gmail.com
 * @date 2019/06/10 22:56
 * @apiNote Describe the function of this class in one sentence
 */

@RestController
public class MapperController {


    @Autowired
    private FieldCopy.PetMapper mapper;

    @RequestMapping("/index")
    public PetDto index(){
        return mapper.toPetDto(new Pet("whd", 20, "male"));
    }
}
