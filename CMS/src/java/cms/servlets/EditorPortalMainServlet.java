/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.servlets;

import cms.data.ConferenceDB;
import cms.entities.Paper;
import cms.entities.User;
import cms.services.ListPaperService;
import cms.services.AccountService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditorPortalMainServlet extends HttpServlet
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

        //TODO replace with dynamic binding
        AccountService a = new AccountService();
        ListPaperService lp = new ListPaperService();

        HttpSession session = request.getSession();
        synchronized(session)
        {
            String editorUserName = ((User)session.getAttribute("loggedInUser")).getUserName();
            session.setAttribute("papersFromConference", lp.listFromConference(ConferenceDB.getConferenceFromEditor(editorUserName).getName(), 1));

            Paper p = new Paper();
            p.setPaperID(1);
            p.setPaperName("Regli Paper");

            System.out.println(lp.listFromConference(ConferenceDB.getConferenceFromEditor(editorUserName).getName(), 1).get(p).size() + "YO HERE");

            session.setAttribute("getReviewers", a.getReviewers());
            session.setAttribute("getConferenceName", ConferenceDB.getConferenceFromEditor(editorUserName).getName());
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
