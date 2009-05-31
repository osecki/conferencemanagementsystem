/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Piyush
 */
public class AuthorPortalUploadFileServlet extends HttpServlet {
   
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

        //TODO: Put an if condition here to check if all variables were filled in, check fileType if PDF. If error, url is calling page, else new servlet.

        HttpSession session = request.getSession();

        //TODO Replace with dynamic binding

        String au_errMsg = null;
        String url = null;
        boolean badData = false;

        try
        {
            if(request.getParameter("paperName").length()==0 || request.getParameter("dataFile").length()==0)
            {
                au_errMsg="<font color=\"red\">One or more fields were left blank. " + request.getParameter("paperName")+" " +request.getParameter("dataFile");
                badData = true;
            }
        }
        catch(NullPointerException e)
        {
            badData = true;
            au_errMsg="<font color=\"red\">One or more fields were left blank. "+ request.getParameter("paperName")+" " +request.getParameter("dataFile");
        }
      


        synchronized(session)
        {
            session.setAttribute("au_errMsg",au_errMsg);
            session.setAttribute("paperName",request.getParameter("paperName"));
            session.setAttribute("dataFile",request.getParameter("dataFile"));

            if(badData)
            {
                url = "/Author/authorportal.jsp";
            }
            else
            {
                url = "/PaperOCRServlet";
            }
        }

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
