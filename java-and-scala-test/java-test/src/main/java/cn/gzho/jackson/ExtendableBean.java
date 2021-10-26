package cn.gzho.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

public class ExtendableBean {
    public String name;
    private Map<String, String> properties;
    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        ExtendableBean bean = new ExtendableBean();
    }
}
