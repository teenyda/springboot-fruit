package com.teenyda.controller.file.controller;

import com.teenyda.common.*;
import com.teenyda.controller.file.dto.FileUploadResponse;
import com.teenyda.controller.file.exception.FileUploadException;
import com.teenyda.controller.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springboottemplate
 * @author: Teenyda
 * @create: 2019-08-24 09:07
 * @description: 文件上传
 **/

@RestController
@RequestMapping("/file")
public class FileUploadController {

    public static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResultBody<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) throws GlobalErrorInfoException {

        String fileName = fileService.saveToFile(file);

        // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //         .path("/file")
        //         .path("/downloadFile/")
        //         .path(fileName)
        //         .toUriString();
        // 已改成这个
        String fileDownloadUri = Util.buildDownloadFilePath(fileName);

        FileUploadResponse response = new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
        return ResultUtil.success(GlobalResponseInfoEnum.SUCCESS, response);
    }

    @PostMapping("/multipleFiles")
    public ResultBody<List<FileUploadResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws GlobalErrorInfoException {
        /*return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());*/
        List<FileUploadResponse> list = new ArrayList<>();

        String fileName = null;

        for (MultipartFile file : files) {
            fileName = fileService.saveToFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/file")
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            FileUploadResponse response = new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
            list.add(response);
        }


        return ResultUtil.success(GlobalResponseInfoEnum.SUCCESS, list);

    }

    //http://192.168.1.66:9000/api/app/file/downloadFile/3374b5783b934664b624cdc2cb582959.jpg
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = null;
        String contentType = null;

        try {
            resource = fileService.loadFileAsResource(fileName);
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (GlobalErrorInfoException e) {
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            logger.info("Could not determine file type.");

        }


        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
