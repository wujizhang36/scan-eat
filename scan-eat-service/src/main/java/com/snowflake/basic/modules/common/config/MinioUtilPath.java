package com.snowflake.basic.modules.common.config;

import com.snowflake.basic.modules.common.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinioUtilPath {

    private static String showImage;

    private static String activityIcon;

    private static String petImagesPath;

    private static String qrPath;

    @Autowired
    public MinioUtilPath(MinioUtil minioUtil) {
        showImage = minioUtil.showImage;
        activityIcon = minioUtil.activityIcon;
        petImagesPath = minioUtil.petImagesPath;
        qrPath = minioUtil.qrPath;
    }

    public static String getShowImage() {
        return showImage;
    }

    public static String getActivityImagesPath() {
        return activityIcon;
    }

    public static String getPetImagesPath() {
        return petImagesPath;
    }

    public static String getQrPath() {
        return qrPath;
    }

    /**
     * 域名拼接图片地址
     *
     * @param imgPath
     * @return
     */
    public static String domainConcatPath(String imgPath) {
        return getShowImage().concat(imgPath);
    }
}
