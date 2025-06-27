package com.snowflake.basic.modules.common.utils;

import java.io.*;
import java.net.*;
import java.util.regex.*;

public class ImageDownloaderUtils {

    /**
     * 根据图片的 URL 下载图片并保存到指定路径
     *
     * @param imageUrl 图片的 URL 地址
     * @param destinationPath 保存图片的本地路径
     * @return 是否下载成功
     */
    public static boolean downloadImage(String imageUrl, String destinationPath) {
        // 图片 URL 和保存路径参数检查
        if (imageUrl == null || imageUrl.isEmpty() || destinationPath == null || destinationPath.isEmpty()) {
            System.err.println("图片 URL 或保存路径不能为空");
            return false;
        }

        // 根据 URL 提取图片后缀
        String fileExtension = getFileExtension(imageUrl);
        if (fileExtension == null) {
            System.err.println("无法从 URL 中提取文件扩展名");
            return false;
        }
        // 如果保存路径没有指定扩展名，则自动添加
        if (!destinationPath.endsWith(fileExtension)) {
            destinationPath += fileExtension;
        }

        try {

            // 创建 URL 对象
            URL url = new URL(imageUrl);

            // 打开 URL 的连接并获取输入流
            InputStream in = url.openStream();

            // 创建文件输出流，将图片数据保存到本地文件
            FileOutputStream out = new FileOutputStream(destinationPath);

            byte[] buffer = new byte[4096];
            int bytesRead;

            // 读取数据并写入文件
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            // 关闭流
            in.close();
            out.close();

            System.out.println("图片已成功下载并保存为: " + destinationPath);
            return true;  // 返回下载成功

        } catch (IOException e) {
            // 打印错误信息并返回失败
            System.err.println("下载图片时发生错误: " + e.getMessage());
            return false;
        }
    }
    /**
     * 从 URL 提取文件扩展名
     *
     * @param url 图片 URL
     * @return 文件扩展名（例如 ".jpg", ".png" 等）
     */
    public static String getFileExtension(String url) {
        // 正则表达式提取文件扩展名
        Pattern pattern = Pattern.compile("(?<=\\.)(jpg|jpeg|png|gif|bmp|tiff|webp)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return "." + matcher.group(0).toLowerCase();
        }
        return null;  // 未能匹配到扩展名
    }

    /**
     * 获取不带扩展名的文件名
     */
    public static String getFileNameWithoutExtension(String url) {
        String extension = getFileExtension(url);
        if (extension != null) {
            return url.substring(0, url.length() - extension.length()); // 去掉后缀
        }
        return url; // 没有匹配到后缀，返回原始文件名
    }

//    public static void main(String[] args) {
//        // 测试下载
//        String imageUrl = "https://bully-registry.oss-cn-hongkong.aliyuncs.com/zzzzz.jpg";  // 替换成实际的图片 URL
//        String destinationPath = "C:\\Users\\Craig\\Desktop\\犬舍\\test121234";  // 本地保存的文件路径
//
//        // 调用工具类方法
//        boolean success = downloadImage(imageUrl, destinationPath);
//
//        if (success) {
//            System.out.println("图片下载成功!");
//        } else {
//            System.out.println("图片下载失败.");
//        }
//    }
}

