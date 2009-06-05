/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
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

public class LoginRedirectServlet extends HttpServlet
{
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

        if(loggedInUser.getUserType().equalsIgnoreCase("Administrator"))
        {
            url = "/AdminPortalMainServlet";
        }
        else if((loggedInUser.getUserType().equalsIgnoreCase("Author")))
        {
            url = "/AuthorPortalMainServlet";
        }
        else if((loggedInUser.getUserType().equalsIgnoreCase("Editor")))
        {
            url = "/EditorPortalMainServlet";
        }
        else if((loggedInUser.getUserType().equalsIgnoreCase("Reviewer")))
        {
            url = "/ReviewerPortalMainServlet";
        }
        else if((loggedInUser.getUserType().equalsIgnoreCase("Test")))
        {
            url = "/Test/index.jsp";
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
