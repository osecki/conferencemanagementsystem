/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cms.servlets;

import cms.data.UserDB;
import cms.entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Piyush
 */
public class LoginRedirectServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String username = (String)request.getRemoteUser();
        User loggedInUser = UserDB.getUser(username);

        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", loggedInUser);

        String url = "";

        if(loggedInUser.getUserType().equals("Administrator"))
        {
            url = "/Admin/adminportal.jsp";
//            To-DO: Replace this with the servlet call and add logged in user to session
//            Student student = StudentDB.getStudent(loggedInUser);
//            session.setAttribute("loggedInStudent", student);
        }
        else if((loggedInUser.getUserType().equals("Author")))
        {
            url = "/Author/authorportal.jsp";
//            Same changes as above need to be added
        }
        else if((loggedInUser.getUserType().equals("Editor")))
        {
            url = "/Editor/editorportal.jsp";
//            Same changes as above need to be added
        }
        else if((loggedInUser.getUserType().equals("Reviewer")))
        {
            url = "/Reviewer/reviewerportal.jsp";
//            Same changes as above need to be added
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
