package com.uppicvote.controller;

import com.uppicvote.model.Image;
import com.uppicvote.repository.Repository;
import com.uppicvote.service.AuthenticationService;
import com.uppicvote.service.ImageService;
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
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PictureController extends HttpServlet {
    private ImageService imageService;
    private AuthenticationService authenticationService;

    public PictureController() {
        this.imageService = new ImageService(new Repository());
        this.authenticationService = new AuthenticationService(new Repository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestTopNParameter = (String)request.getAttribute("topN");
        System.out.println("REQUEST TOP N PARAMETER: " + requestTopNParameter);
        List<Image> images = null;
        if (requestTopNParameter == null) {
            images = this.imageService.getAllImages();
        } else {
            Integer topN = Integer.parseInt(requestTopNParameter);
            images = this.imageService.getTheTopNImages(topN);
        }
        System.out.println(images.size());
        request.setAttribute("images", images);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Directory where files will be saved
            File directory = new File("/home/calandrinon/WPTomcat/apache-tomcat-9.0.45/webapps/ROOT/Files");
            File persistenceDirectory = new File("/home/calandrinon/Documents/an2sem2/Web-Programming/Lab-9/UpPicVote/src/main/webapp/Files");

            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                Image savedImage = new Image("", "");

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        System.out.println("item.isFormField() for regular fields: " + fieldname + "->" + fieldvalue);
                        savedImage.setDescription(fieldvalue);
                    } else {
                        HttpSession session = request.getSession();
                        String username = (String)session.getAttribute("username");
                        String fieldname = item.getFieldName();
                        String filename = username + "_" + FilenameUtils.getName(item.getName());
                        System.out.println("item.isFormField() for file fields: " + fieldname + "->" + filename);
                        savedImage.setFilename(filename);
                        Integer userId = this.authenticationService.getUserId(username);
                        System.out.println("Trying to obtain the userId...");
                        System.out.println("The userId is: " + userId);
                        savedImage.setUserId(userId);

                        byte[] image = item.get();
                        File file = new File(directory, filename);
                        File persistenceFile = new File(persistenceDirectory, filename);
                        this.createTemporaryFile(file);
                        this.createPersistenceFile(persistenceFile);

                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(image);
                        fileOutputStream.flush();

                        FileOutputStream fileOutputStreamPersistence = new FileOutputStream(persistenceFile);
                        fileOutputStreamPersistence.write(image);
                        fileOutputStreamPersistence.flush();
                        System.out.println("The image should now be saved. Enjoy.");

                        this.imageService.saveImage(savedImage);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            response.sendRedirect("/pictures");
        }
    }

    private void createTemporaryFile(File file) throws IOException {
        if (file.createNewFile()) {
            System.out.println("The file has been created because it didn't exist. Everything's fine...");
        } else {
            System.out.println("The file already exists.");
        }
    }


    private void createPersistenceFile(File persistenceFile) throws IOException {
        if (persistenceFile.createNewFile()) {
            System.out.println("The persistence file has been created because it didn't exist. Everything's fine...");
        } else {
            System.out.println("The persistance file already exists.");
        }
    }
}
