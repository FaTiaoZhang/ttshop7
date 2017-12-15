package com.dhc.ttshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * User: DHC
 * Date: 2017/11/30
 * Time: 14:26
 * Version:V1.0
 */
public interface FileService {

    Map<String,Object> uploadImage(MultipartFile upfile);
}
