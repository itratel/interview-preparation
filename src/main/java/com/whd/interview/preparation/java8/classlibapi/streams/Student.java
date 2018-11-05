package com.whd.interview.preparation.java8.classlibapi.streams;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/2 16:25
 * @apiNote Student
 */
public class Student {

    private String id;

    private String name;

    private Status status;

    public Student() {
    }

    public Student(String id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public enum Status {
        OPEN, CLOSED
    }
}
