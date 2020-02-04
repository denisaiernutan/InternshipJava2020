package com.arobs.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name="FirstServlet", urlPatterns="/firstServlet"
, initParams = {
        @WebInitParam(name="elem", value="valuee")}
        )
public class FirstServlet extends HttpServlet
{

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter=resp.getWriter();
        for(int i=0;i<5;i++){
            printWriter.println("elem-"+i);
        }
        printWriter.println("param elem= "+ getInitParameter("elem"));

    }
}
