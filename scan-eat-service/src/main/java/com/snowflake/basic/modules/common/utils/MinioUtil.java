package com.snowflake.basic.modules.common.utils;

import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class MinioUtil {

    private final MinioClient minioClient;

    @Value("${bully.registry.minio.bucket}")
    private String bucketName;

    @Value("${bully.registry.minio.activity.imagesPath}")
    public String activityIcon;

    @Value("${bully.registry.minio.pet.imagesPath}")
    public String petImagesPath;

    @Value("${bully.registry.minio.showImage}")
    public String showImage;

    @Value("${bully.registry.minio.endpoint}")
    String endpoint;

    @Value("${bully.registry.minio.qr.imagesPath}")
    public String qrPath;

    public MinioUtil(@Value("${bully.registry.minio.endpoint}") String endpoint,
                     @Value("${bully.registry.minio.accessKey}") String accessKey,
                     @Value("${bully.registry.minio.secretKey}") String secretKey,
                     @Value("${bully.registry.minio.region}") String region) {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .region(region)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * 上传文件
     *
     * @param objectName  文件名（支持带路径，如 "folder/file.jpg"）
     * @param stream      文件流
     * @param size        文件大小
     * @param contentType 文件类型（如 "image/jpeg"）
     * @return 文件访问 URL
     */
    public String uploadFile(String objectName, InputStream stream, long size, String contentType) {
        try {
            // 检查 bucket 是否存在，不存在则创建
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // 上传文件
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(stream, size, -1)
                            .contentType(contentType)
                            .build()
            );

            // 返回文件访问 URL
            minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .method(Method.GET)
                            .build()
            );
            return objectName;
        } catch (Exception e) {
            log.error("文件上传失败: {}", e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 下载文件
     *
     * @param objectName 文件名
     * @return 文件流
     */
    public InputStream downloadFile(String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            log.error("文件下载失败: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除文件
     *
     * @param objectName 文件名
     * @return 是否删除成功
     */
    public boolean deleteFile(String objectName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            return true;
        } catch (Exception e) {
            log.error("文件删除失败: {}", e.getMessage());
            return false;
        }
    }
}