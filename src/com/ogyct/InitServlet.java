package com.ogyct;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ogyct.Utils.Utils;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(urlPatterns = "/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        String exampleXML;
        try {
            exampleXML = Utils.readFile(getServletContext().getRealPath("/") + "xml/actor.xml", Charset.defaultCharset());
            getServletContext().setAttribute("exampleXML", exampleXML);
        } catch (IOException e) {
            DebugLog.error(e.getMessage());
        }
        
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
