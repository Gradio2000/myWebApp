package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.service.FileLoadService;
import ru.laskin.myWebApp.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    private final TestService testService;
    private final FileLoadService fileLoadService;

    public FileController(TestService testService, FileLoadService fileLoadService) {
        this.testService = testService;
        this.fileLoadService = fileLoadService;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String uploadFile (@RequestParam Integer id, HttpServletRequest request){
        request.setAttribute("id", id);
        return "loadFile";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file, @RequestParam Integer id) throws IOException {
        try {
            fileLoadService.loadFile(file, id);
        } catch (IllegalStateException e) {
            return "errors/error";
        } catch (ArrayIndexOutOfBoundsException e){
            return "errors/error_array";
        }

        return "redirect:/tests/update?id=" + id;
    }

}
