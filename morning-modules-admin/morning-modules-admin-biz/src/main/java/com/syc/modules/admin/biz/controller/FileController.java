package com.syc.modules.admin.biz.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.syc.modules.admin.api.model.entity.SysFileInfo;
import com.syc.modules.admin.biz.services.SysFileInfoService;
import com.syc.modules.common.result.Result;
import com.syc.modules.admin.api.model.dto.FileInfo;
import com.syc.modules.admin.biz.services.OssService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation; 
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xq.su
 */
@Tag(name = "07.文件接口")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final OssService ossService;

    private final SysFileInfoService sysFileInfoService;

    @PostMapping
    @Operation(summary = "文件上传")
    public Result<FileInfo> uploadFile(
            @Parameter(description ="表单文件对象") @RequestParam(value = "file") MultipartFile file
    ) {
        FileInfo fileInfo = ossService.uploadFile(file);
        // 保存到数据库
        SysFileInfo sysFileInfo = BeanUtil.toBean(fileInfo, SysFileInfo.class);
        this.sysFileInfoService.save(sysFileInfo);
        return Result.success(fileInfo);
    }

    @DeleteMapping
    @Operation(summary = "文件删除")
    @SneakyThrows(Exception.class)
    public Result<Object> deleteFile(
            @Parameter(description ="文件路径") @RequestParam String filePath
    ) {
        boolean result = ossService.deleteFile(filePath);
        this.sysFileInfoService.remove(Wrappers.<SysFileInfo>lambdaUpdate().eq(SysFileInfo::getName, filePath));
        return Result.judge(result);
    }
}
