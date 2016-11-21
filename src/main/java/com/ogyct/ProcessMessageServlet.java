package com.ogyct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ogyct.db.ManageActor;
import com.ogyct.mappings.Actor;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/ProcessMessageServlet")
public class ProcessMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessMessageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputString = request.getParameter("text1");

        final InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        //start message processing
        MessageProcessorAPI mpa = new MessageProcessorAPI();
        try {
            mpa.processMessage(stream);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("returnMessage", e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        ManageActor ma = new ManageActor();
        request.setAttribute("returnMessage", createTableFromList(ma.listActors()));

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String createTableFromList(List<Actor> actors) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < actors.size(); i++) {
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(actors.get(i).getFirstName());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(actors.get(i).getLastName());
            sb.append("</td>");
            sb.append("</tr>");
        }

        return sb.toString();
    }

}
