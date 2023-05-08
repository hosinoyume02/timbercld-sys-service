package com.timbercld.core.utils;


import com.timbercld.core.config.LocalUploadConfig;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class LocalUploadUtils {

    @Autowired
    protected LocalUploadConfig localUploadConfig;

    public String saveLocal(MultipartFile multipartFile, String folder , String fileName) {


        File file = new File(this.localUploadConfig.getBasicFolder() + folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 4K的数据缓冲
        byte[] bs = new byte[4096];
        // 读取到的数据长度
        int len;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String path = file.getPath() + File.separator + fileName;
        try {
            inputStream = multipartFile.getInputStream();
            fileOutputStream = new FileOutputStream(path);
            while ((len = inputStream.read(bs)) != -1) {
                fileOutputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return path;
    }

    public void saveExcelLocal(Workbook workbook, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

}

