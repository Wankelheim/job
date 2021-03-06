package com.pl.api.controller;

import com.pl.api.ApiConst;
import com.pl.api.model.PaginationDto;
import com.pl.api.model.RecordDto;
import com.pl.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/" + ApiConst.API_VERSION + "/file")
@AllArgsConstructor
public class FileController {

    FileService fileService;

    @PostMapping
    public void upload(@RequestBody MultipartFile file) {

        fileService.upload(file);
    }

    @GetMapping
    public ResponseEntity<RecordDto> getRecord(@RequestParam Integer id) {

        return new ResponseEntity<>(fileService.getRecord(id), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteRecord(@RequestParam Integer id) {

        fileService.deleteRecord(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ResponseEntity<PaginationDto<List<RecordDto>>> getRecords(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
                                                                     @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo) {

        return new ResponseEntity<>(fileService.getRecords(dateFrom, dateTo), HttpStatus.OK);
    }
}
