package com.taotao.service.impl;

import com.taotao.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map result = new HashMap();
        FileOutputStream fo = null;
        try {
            String oldName = uploadFile.getOriginalFilename();
            String name = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            String dirStr = new SimpleDateFormat("MM/dd").format(new Date());
            String fullDir = "/Users/yangqiang/Desktop/tmpDir/test/" + dirStr;
            File dir = new File(fullDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fo = new FileOutputStream(fullDir + "/" + name);
//            byte[] buf = new byte[1024];
//            while (uploadFile.getInputStream().read(buf) != -1) {
//                fo.write(buf);
//                fo.flush();
//            }

            fo.write(uploadFile.getBytes());
            fo.flush();

            result.put("error", 0);
            result.put("url", "/img/" + dirStr + "/" + name);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", 1);
            result.put("message", e.getMessage());
        } finally {
            try {
                if (null != fo) {
                    fo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
