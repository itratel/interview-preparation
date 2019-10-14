package com.whd.interview.preparation.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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

    @Mapper(componentModel = "spring")
    public interface PetMapper {

//        PetMapper mapper = Mappers.getMapper(PetMapper.class);

        @Mappings({
                @Mapping(source = "name", target = "name"),
                @Mapping(source = "sex", target = "sex"),
                @Mapping(source = "age", target = "age")
        })
        PetDto toPetDto(Pet pet);

//        @InheritInverseConfiguration
//        Pet fromPetDto(PetDto petDto);
    }

//    public static void main(String[] args) {
//        PetDto petDto = PetMapper.mapper.toPetDto(new Pet("whd", 20, "male"));
//        System.out.println("petDto = " + petDto);
//    }

}
