package com.whd.interview.preparation.test;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/10 11:03
 * @apiNote Describe the function of this class in one sentence
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1)
public class TestFeildCopy {

    @Benchmark
    @Threads(3)
    public void runCommon() {
        Pet pet = new Pet();
        pet.setAge(10);
        pet.setName("huohuo");
        pet.setSex("male");
        FieldCopy.slefTransform(pet);
    }

    @Benchmark
    @Threads(3)
    public void runApache() {
        Pet pet = new Pet();
        pet.setAge(10);
        pet.setName("huohuo");
        pet.setSex("male");
        FieldCopy.apacheTransform(pet);
    }

    @Benchmark
    @Threads(3)
    public void runSpring() {
        Pet pet = new Pet();
        pet.setAge(10);
        pet.setName("huohuo");
        pet.setSex("male");
        FieldCopy.springTransform(pet);
    }

    @Benchmark
    @Threads(3)
    public void runMapStruct() {
        Pet pet = new Pet();
        pet.setAge(10);
        pet.setName("huohuo");
        pet.setSex("male");
        FieldCopy.PetMapper.mapper.toPetDto(pet);
    }

}
