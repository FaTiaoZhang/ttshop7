package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.util.FtpUtils;
import com.dhc.ttshop.common.util.IDUtils;
import com.dhc.ttshop.common.util.Prop;
import com.dhc.ttshop.common.util.PropKit;
import com.dhc.ttshop.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/11/30
 * Time: 14:26
 * Version:V1.0
 */
@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Map<String, Object> uploadImage(MultipartFile upfile) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //ftp.properties要在类路径之下
            Prop prop = PropKit.use("ftp.properties");
            String host = prop.get("ftp.host");
            Integer port = prop.getInt("ftp.port");
            String username = prop.get("ftp.username");
            String password = prop.get("ftp.password");
            String basePath = prop.get("ftp.basepath");
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            //获取上传图片的原来名称
            String originalFilename = upfile.getOriginalFilename();//abc.jpg
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));//.jpg substring(0,2)   [0,2)
            //调用工具生成图片名称
            String newName = IDUtils.genImageName();//1122344555
            newName += fileType;
            InputStream inputStream = upfile.getInputStream();
            //上传文件
            boolean bool = FtpUtils.uploadFile(host,port,username,password,basePath,filePath,newName,inputStream);
            if (bool) {
                map.clear();
                map.put("state","SUCCESS");
                map.put("title",newName);
                map.put("original",originalFilename);
                map.put("type",fileType);
                map.put("url", filePath + "/" + newName);
                map.put("size",upfile.getSize());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return map;
    }
}
