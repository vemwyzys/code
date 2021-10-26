package cn.gzho.jackson;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RawBean {
    public String name;

    @JsonRawValue
    public String json;


    public static void main(String[] args) throws JsonProcessingException {
        RawBean bean = new RawBean();
        bean.name="nick";
        bean.json="{\"attr\":false}";

        String json = new ObjectMapper().writeValueAsString(bean);
        System.out.println(json);
    }
}
