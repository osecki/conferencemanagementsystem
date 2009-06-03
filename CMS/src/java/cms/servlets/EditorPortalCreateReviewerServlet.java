/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.servlets;

import cms.services.AccountService;
import cms.services.ConferenceSystemService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditorPortalCreateReviewerServlet extends HttpServlet
{   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String errMsg;
        boolean badData = false;

        //TODO Replace with dynamic binding
        AccountService a = new AccountService();
        ConferenceSystemService c = new ConferenceSystemService();

        if(request.getParameter("userName").length()==0 || request.getParameter("password").length()==0)
        {
            badData = true;
        }

        synchronized(session)
        {
             if(!badData)
             {
                if(a.createAccount(request.getParameter("userName"), "REVIEWER", request.getParameter("fullName"), request.getParameter("emailAddress"), request.getParameter("password")))
                {
                    errMsg = "<font color=\"blue\">New Reviewer account successfully created.";
                }
                else
                {
                    errMsg = "<font color=\"red\">There was a problem. Reviewer account could not be created.";
                }
            }
            else
            {
                 errMsg = "<font color=\"red\">There was a problem. Reviewer account could not be created.";
            }

            session.setAttribute("errMsg",errMsg);
            session.setAttribute("availableEditors", a.getAvailableEditors());
            session.setAttribute("availableConferences", c.getAvailableConferences());

            session.setAttribute("fullName", request.getParameter("fullName"));
            session.setAttribute("userName", request.getParameter("userName"));
            session.setAttribute("password", request.getParameter("password"));
            session.setAttribute("emailAddress", request.getParameter("emailAddress"));
            session.setAttribute("name", request.getParameter("name"));
            session.setAttribute("location", request.getParameter("location"));
            session.setAttribute("eventDate", request.getParameter("eventDate"));
            session.setAttribute("dueDate", request.getParameter("dueDate"));

        }

        String url = "/Editor/editorportal.jsp";
        url = response.encodeURL(url);
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
