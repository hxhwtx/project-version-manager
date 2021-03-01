package com.wpg.projectversion.minio.conf;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    /**
     * endPoint是一个URL，域名，IPv4或者IPv6地址
     */
    private String endpoint="10.200.5.227";

    /**
     * TCP/IP端口号
     */
    private int port= 9000;

    /**
     * accessKey类似于用户ID，用于唯一标识你的账户
     */
    private String accessKey="admin";

    /**
     * secretKey是你账户的密码
     */
    private String secretKey="wpg@2020";

    /**
     * 如果是true，则用的是https而不是http,默认值是true
     */
    private Boolean secure=false;

    /**
     * 默认存储桶名称
     */
    private String bucketDefaultName = "test";


    @Bean
    public MinioClient getMinioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint , port , secure)
                .credentials(accessKey, secretKey)
                .build();
        return minioClient;
    }

}
