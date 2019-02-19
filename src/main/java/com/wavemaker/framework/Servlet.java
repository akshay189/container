package com.wavemaker.framework;

import com.wavemaker.framework.exceptions.CustomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;


@WebServlet(urlPatterns = {"/rest/*"})
public class Servlet extends HttpServlet {
    private RequestHandler requestHandler = null;

    public Servlet() throws IllegalAccessException, InstantiationException {
        requestHandler = new RequestHandler();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter out = response.getWriter();
            //request.getRequestDispatcher("/jsp/home.jsp").forward(request,response);
            out.println(requestHandler.handleRequest(request.getPathInfo(), request, RequestMethod.POST));
        } catch (IllegalAccessException | InvocationTargetException | CustomException e) {
            throw new CustomException("custom runtime exception", e);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        try {
            out.println(requestHandler.handleRequest(req.getPathInfo(), req, RequestMethod.GET));
            //req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (InvocationTargetException | IllegalAccessException | CustomException e) {
            throw new CustomException("Exception caused in get", e);
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PrintWriter out = resp.getWriter();
            out.println(requestHandler.handleRequest(req.getPathInfo(), req, RequestMethod.PUT));
        } catch (InvocationTargetException | IllegalAccessException | CustomException e) {
            throw new CustomException("Exception in put", e);
        }
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PrintWriter out = resp.getWriter();
            out.println(requestHandler.handleRequest(req.getPathInfo(), req, RequestMethod.DELETE));
        } catch (InvocationTargetException | IllegalAccessException | CustomException e) {
            throw new CustomException("Exception caused in delete", e);
        }
    }
}
