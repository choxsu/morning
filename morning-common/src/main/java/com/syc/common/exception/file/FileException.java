package com.syc.common.exception.file;

import com.syc.common.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author xq.su
 */
public class FileException extends BaseException {

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
