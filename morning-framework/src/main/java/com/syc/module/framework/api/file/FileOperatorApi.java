package com.syc.module.framework.api.file;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作api
 * （后期整合一下阿里云 腾讯云）
 *
 * @author xq.suW
 */
public interface FileOperatorApi {

    void storageFile(String bucketName, MultipartFile file, String[] allowedExtension);

}
