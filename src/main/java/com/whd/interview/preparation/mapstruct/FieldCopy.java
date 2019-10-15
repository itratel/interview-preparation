package com.whd.interview.preparation.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/10 10:47
 * @apiNote Describe the function of this class in one sentence
 */
@Mapper(componentModel = "spring")
public interface FieldCopy {


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "sex", target = "sex"),
            @Mapping(source = "age", target = "age")
    })
    PetDto toPetDto(Pet pet);


}
