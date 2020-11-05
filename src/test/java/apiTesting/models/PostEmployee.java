package apiTesting.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostEmployee {
//    @JsonProperty("name")
    String name;
//    @JsonProperty("salary")
    String salary;
//    @JsonProperty("age")
    String age;

    public PostEmployee(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
