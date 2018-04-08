package com.community.utils;
  
import org.springframework.web.multipart.MultipartFile;  
  
import javax.servlet.http.HttpServletRequest;  
import java.io.File;  
import java.io.IOException;  
import java.util.Date;  
  
  
public class FileUploadUtil {
    private static final String ICON_DIRECTORY= "static/images/user";
  
    //文件上传  
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {  
        String fileName = file.getOriginalFilename();  
        String path=request.getSession().getServletContext().getRealPath(ICON_DIRECTORY);
        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdir();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return tempFile.getName();
    }  
  
}  
