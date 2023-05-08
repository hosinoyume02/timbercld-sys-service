package com.timbercld.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@Data
@ConfigurationProperties(prefix = "timbercld.upload")
public class LocalUploadConfig {

    private String basicFolder;

    private String basicDomain;


}
