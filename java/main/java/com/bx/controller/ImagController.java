package com.bx.controller;

import com.bx.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ImagController {


    @Value("${imageFilePath}")
    private String imageFilePath;

    /**
     * 上传图片
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(MultipartFile file, String filename)throws Exception{
        Map<String,Object> map=new HashMap<>();
        if(!file.isEmpty()){
            String fileName=file.getOriginalFilename();
            String suffixName=fileName.substring(fileName.lastIndexOf('.')); // 图片后缀
            String newFileName= DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+newFileName));
            map.put("code",0);
            map.put("msg","上传成功");
            Map<String,Object> map2=new HashMap<>();
            map2.put("title",newFileName);
            map2.put("src","/image/"+newFileName);
            map.put("data",map2);
        }
        return map;
    }
}
