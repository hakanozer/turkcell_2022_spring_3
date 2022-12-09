package com.works.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile("dev")
@PropertySource(value = "classpath:application.properties")
public class DevConfig implements IConfig {

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new HashMap<>();
        hm.put("rowCount", 50);
        hm.put("apiKey", apiKey);
        hm.put("theme", "dark");
        return hm;
    }

}
