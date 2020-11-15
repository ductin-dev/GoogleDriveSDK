package com.example.demo.controllers;

import jdk.internal.util.xml.impl.Input;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.model.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = {"/", "/index"})
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void uploadPhotoForm(HttpServletRequest request,HttpServletResponse response) {
        try {
            //File id on google
            String fileId="1DU9Y0ECFMZH-HigL0Yl6exvwpR2pKSQP";

            //GG DRIVE
            //com.example.demo.model.Main.downFile(response);

            //HIDDEN URL
            InputStream inputStream = new URL("https://docs.google.com/uc?export=download&id="+fileId).openStream();
            response.addHeader("Content-Disposition", "attachment; filename=test.jpg");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
