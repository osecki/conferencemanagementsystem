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

public class RegisterAuthorServlet extends HttpServlet
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
        String registerErrMsg = null;
        boolean badData = false;

        //TODO Replace with dynamic binding
        AccountService a = new AccountService();

        if(request.getParameter("r_userName").length()==0 || request.getParameter("r_password").length()==0)
        {
            badData = true;
        }

        synchronized(session)
        {
            if(!request.getParameter("r_password").equals(request.getParameter("r_repeatPassword")))
            {
                registerErrMsg = "<font color=\"red\">The two entered passwords do not match.";
                badData = true;
            }

            if(!badData)
             {
                if(a.createAccount(request.getParameter("r_userName"), "AUTHOR", request.getParameter("r_fullName"), request.getParameter("r_emailAddress"), request.getParameter("r_password")))
                {
                    registerErrMsg = "<font color=\"blue\">New Author account successfully created.";
                }
                else
                {
                    registerErrMsg = "<font color=\"red\">There was a problem. Author account could not be created.";
                }
            }
            else if(request.getParameter("r_password").equals(request.getParameter("r_repeatPassword")))
            {
                 registerErrMsg = "<font color=\"red\">There was a problem. Author account could not be created.";
            }
            
            session.setAttribute("registerErrMsg",registerErrMsg);

            session.setAttribute("r_fullName", request.getParameter("r_fullName"));
            session.setAttribute("r_userName", request.getParameter("r_userName"));
            session.setAttribute("r_password", request.getParameter("r_password"));
            session.setAttribute("r_repeatPassword", request.getParameter("r_repeatPassword"));
            session.setAttribute("r_emailAddress", request.getParameter("r_emailAddress"));

        }

        String url = "/register.jsp";
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
