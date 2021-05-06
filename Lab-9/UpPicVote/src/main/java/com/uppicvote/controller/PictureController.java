package com.uppicvote.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class PictureController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Directory where files will be saved
            File seshdir = new File("/home/calandrinon/WPTomcat/jspAssignment");

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
        /**
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        // ... (do your job here)
         **/
    }
}
