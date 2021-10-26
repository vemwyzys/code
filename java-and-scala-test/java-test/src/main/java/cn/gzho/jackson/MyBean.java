package cn.gzho.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({"name", "id"})
public class MyBean {

    public int id;
    private String name;

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    public static void main(String[] args) throws JsonProcessingException {
        MyBean bean = new MyBean();

        bean.id = 5;
        bean.name = "nick";

        String json = new ObjectMapper().writeValueAsString(bean);
        System.out.println(json);

    }
}
