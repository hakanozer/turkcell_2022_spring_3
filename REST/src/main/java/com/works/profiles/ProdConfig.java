package com.works.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile("prod")
@PropertySource(value = "classpath:application.properties")
public class ProdConfig implements IConfig {

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new HashMap<>();
        hm.put("rowCount", 20);
        hm.put("apiKey", apiKey);
        hm.put("theme", "light");
        return hm;
    }

}
