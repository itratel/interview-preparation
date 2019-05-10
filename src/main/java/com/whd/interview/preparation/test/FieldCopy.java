package com.whd.interview.preparation.test;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/10 10:47
 * @apiNote Describe the function of this class in one sentence
 */
public class FieldCopy {


    public static PetDto apacheTransform(Pet pet) {
        PetDto dto = new PetDto();
        try {
            BeanUtils.copyProperties(dto, pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static PetDto springTransform(Pet pet) {
        PetDto dto = new PetDto();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dto, pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static PetDto slefTransform(Pet pet) {
        PetDto dto = new PetDto();
        dto.setName(pet.getName());
        dto.setAge(pet.getAge());
        dto.setSex(pet.getSex());
        return dto;
    }

    @Mapper
    public interface PetMapper {

        PetMapper mapper = Mappers.getMapper(PetMapper.class);

        @Mappings({
                @Mapping(source = "name", target = "name"),
                @Mapping(source = "sex", target = "sex"),
                @Mapping(source = "age", target = "age")
        })
        PetDto toPetDto(Pet pet);

        @InheritInverseConfiguration
        Pet fromPetDto(PetDto petDto);

    }


}
