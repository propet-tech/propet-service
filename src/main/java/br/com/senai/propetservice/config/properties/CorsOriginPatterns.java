package br.com.senai.propetservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server.cors")
public class CorsOriginPatterns {

    private String[] originPatterns = new String[]{"locahost"};

    public String[] getOriginPatterns() {
        return originPatterns;
    }

    public void setOriginPatterns(String[] originPatterns) {
        this.originPatterns = originPatterns;
    }

}
