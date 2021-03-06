package controller;

import param.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

/**
 * Created by Margarita on 05.08.2014.
 */
public class ImagesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File file;
        if(!filename.equals("")) {
            file = new File(RequestParams.bundle.getString("uploadPath"), filename);
        }
        else{
            String homeDir = request.getSession().getServletContext().getRealPath("/");
            file = new File(homeDir, RequestParams.bundle.getString("standardImage"));
        }
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());

    }
}
