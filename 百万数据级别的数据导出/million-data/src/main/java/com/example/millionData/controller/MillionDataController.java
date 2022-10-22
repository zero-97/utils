package com.example.millionData.controller;

import com.example.millionData.service.IAsyncTaskLogService;
import com.example.millionData.service.IMillionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/milloinData")
public class MillionDataController {

    @Autowired
    private IMillionDataService service;

    @Autowired
    private IAsyncTaskLogService asyncTaskLogService;

    @GetMapping("export")
    public void export(HttpServletResponse response){
        try {
            service.export(response);
        } catch (IOException e) {
        }
    }

    @GetMapping("exportAsyn")
    public int exportAsyn() throws FileNotFoundException {
        int logId = asyncTaskLogService.insert();
        service.exportAsyn(logId);
        return logId;
    }

    @GetMapping("getUrl")
    public String getUrl(@RequestParam int id) {
        return asyncTaskLogService.getUrl(id);
    }
}
