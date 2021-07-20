package ru.laskin.myWebApp.controllers;


import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ExceptionController {
    public void printException(HttpServletRequest request, Logger log, Exception e){
        log.log(Level.SEVERE, ExceptionUtils.getStackTrace(e));
        StackTraceElement[] methods = Thread.currentThread().getStackTrace();
        request.setAttribute("method", methods[2].getMethodName());
    }
}
