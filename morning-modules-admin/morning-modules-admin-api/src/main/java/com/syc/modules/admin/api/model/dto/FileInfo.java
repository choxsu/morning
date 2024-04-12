package com.syc.modules.admin.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xq.su
 */
@Schema(description = "文件对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件URL")
    private String url;

    @Schema(description = "唯一ID")
    private String uuid;

}
