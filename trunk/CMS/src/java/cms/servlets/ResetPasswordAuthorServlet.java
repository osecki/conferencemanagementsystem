/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.servlets;

import cms.services.AccountService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResetPasswordAuthorServlet extends HttpServlet
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
        String resetErrMsg = null;
        boolean badData = false;

        // TODO Replace with dynamic binding
        AccountService a = new AccountService();

        if(request.getParameter("userName").length()==0 || request.getParameter("fullName").length()==0 || request.getParameter("emailAddress").length()==0)
        {
            badData = true;
        }

        synchronized(session)
        {
            if( ! badData )
             {
                String newPass = a.resetPassword(request.getParameter("userName"), request.getParameter("fullName"), request.getParameter("emailAddress"));

                if( ! newPass.equals("Error") )
                {
                    resetErrMsg = "<font color=\"blue\">New Author password created. It is '" + newPass + "'.";
                }
                else
                {
                    resetErrMsg = "<font color=\"red\">There was a problem. Author password could not be reset.";
                }
            }
            else
            {
                 resetErrMsg = "<font color=\"red\">There was a problem. Author password could not be reset.";
            }
            
            session.setAttribute("resetErrMsg",resetErrMsg);

            session.setAttribute("fullName", request.getParameter("fullName"));
            session.setAttribute("userName", request.getParameter("userName"));
            session.setAttribute("emailAddress", request.getParameter("emailAddress"));

        }

        String url = "/resetpassword.jsp";
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
