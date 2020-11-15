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
import java.security.SecureRandom;

@Controller
@RequestMapping("/")
public class MainController {

    public static String authozToken;

    String randomString(int len){
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    @RequestMapping(value = {"/", "/index"})
    public String home() {
        authozToken=randomString(30);
        return "home";
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ModelAndView uploadPhotoForm(HttpServletRequest request,HttpServletResponse response) {
        try {
            //Validate azthortoken
            if (!request.getParameter("authozToken").equals(authozToken)) throw new Exception("You don't have valid token, please make sure close all the window and submit again");

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
            ModelAndView mav=new ModelAndView("failure");
            mav.addObject("log",e.getMessage());
            return mav;
        }
        return new ModelAndView("home");
    }

}
