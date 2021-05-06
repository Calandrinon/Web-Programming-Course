package com.uppicvote.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PictureController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File folder = new File("/home/calandrinon/WPTomcat/apache-tomcat-9.0.45/webapps/ROOT/Files");
        File[] listOfFiles = folder.listFiles();

        System.out.println("The list of images:");
        if (listOfFiles.length > 0) {
            for (File file: listOfFiles) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No images uploaded.");
        }

        List<String> filenames = Arrays.stream(listOfFiles)
                .map(File::getName)
                .collect(Collectors.toList());

        request.setAttribute("filenames", filenames);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Directory where files will be saved
            File seshdir = new File("/home/calandrinon/WPTomcat/apache-tomcat-9.0.45/webapps/ROOT/Files");

            if (!seshdir.exists()) {
                seshdir.mkdirs();
            }

            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        System.out.println("item.isFormField() for regular fields: " + fieldname + "->" + fieldvalue);
                    } else {
                        String fieldname = item.getFieldName();
                        String filename = FilenameUtils.getName(item.getName());
                        System.out.println("item.isFormField() for file fields: " + fieldname + "->" + filename);

                        byte[] image = item.get();
                        System.out.println("Image bytes:");
                        System.out.println(Arrays.toString(image));
                        System.out.println("============================================================");
                        File file = new File(seshdir, filename);
                        if (file.createNewFile()) {
                            System.out.println("The file has been created because it didn't exist. Everything's fine...");
                        } else {
                            System.out.println("The file already exists.");
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(image);
                        fileOutputStream.flush();
                        System.out.println("The image should now be saved. Enjoy.");
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}
