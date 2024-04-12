package com.syc.modules.admin.biz.controller;

import com.syc.modules.admin.api.model.dto.FileInfo;
import com.syc.modules.admin.biz.services.OssService;
import com.syc.modules.admin.biz.services.SysFileInfoService;
import com.syc.modules.common.result.Result;
import com.syc.server.MorningApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MorningApplication.class)
public class FileControllerTest {

    @MockBean
    private OssService ossService;

    @MockBean
    private SysFileInfoService sysFileInfoService;

    @InjectMocks
    private FileController fileController;

    private MultipartFile file;
    private FileInfo fileInfo;

    @BeforeEach
    public void setUp() {
        // 初始化模拟文件和期望返回的文件信息
        file = new MockMultipartFile("file", "test-file.txt", "text/plain", "test data".getBytes());
        fileInfo = new FileInfo("test-file.txt", "http://example.com/test-file.txt", "unique-uuid");
    }

    @Test
    public void uploadFileTest() {
        // Arrange: 配置模拟对象的行为
        when(ossService.uploadFile(any(MultipartFile.class))).thenReturn(fileInfo);

        // Act: 调用要测试的方法
        Result<FileInfo> result = fileController.uploadFile(file);

        // Assert: 验证结果是否符合预期
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(fileInfo, result.getData());

        // 验证OSS服务的uploadFile方法是否被调用了一次
        verify(ossService, times(1)).uploadFile(any(MultipartFile.class));

        // 验证文件信息保存服务是否被调用了一次
        verify(sysFileInfoService, times(1)).save(any());
    }
}
