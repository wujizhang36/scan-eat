package com.snowflake.basic.modules.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<File> listFiles(String directoryPath) {
        File dir = new File(directoryPath);
        List<File> fileList = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
}
