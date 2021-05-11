package com.lzh.jmeter.ui.utils;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存视频
     * @param base64
     * @param videoLocation
     */
    public static void saveVideo(String base64, String videoLocation) {
        byte[] decode = Base64.getDecoder().decode(base64);
        try {
            FileUtils.writeByteArrayToFile(new File(videoLocation),
                    decode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
