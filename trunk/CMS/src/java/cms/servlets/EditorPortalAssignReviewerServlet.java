/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.servlets;

import cms.data.ConferenceDB;
import cms.entities.User;
import cms.services.AccountService;
import cms.services.ConferenceSystemService;
import cms.services.FeedbackSystemService;
import cms.services.ListPaperService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditorPortalAssignReviewerServlet extends HttpServlet
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
        ListPaperService lp = new ListPaperService();
        FeedbackSystemService fs = new FeedbackSystemService();

        if(request.getParameter("selectedReviewers") == null || request.getParameter("selectedPapers") == null || request.getParameter("selectedReviewers").length()==0 || request.getParameter("selectedPapers").length()==0)
        {
            badData = true;
        }

        synchronized(session)
        {
             if(!badData)
             {
                if( a.assignReviewer(request.getParameter("selectedReviewers"), Integer.parseInt(request.getParameter("selectedPapers"))) )
                {
                    errMsg = "<font color=\"blue\">Reviewer Successfully assigned to the paper.";
                }
                else
                {
                    errMsg = "<font color=\"red\">There was a problem. Reviewer could not be assigned. Might already be assigned to this paper. Please try again.";
                }
            }
            else
            {
                 errMsg = "<font color=\"red\">There was a problem. Reviewer could not be assigned. Please try again.";
            }

            session.setAttribute("errMsg",errMsg);
            String editorUserName = ((User)session.getAttribute("loggedInUser")).getUserName();
            session.setAttribute("papersFromConference", lp.listFromConference(ConferenceDB.getConferenceFromEditor(editorUserName).getName()));
            session.setAttribute("getReviewers", a.getReviewers());
            session.setAttribute("getConferenceName", ConferenceDB.getConferenceFromEditor(editorUserName).getName());
            session.setAttribute("papersFromConferenceToRelease", lp.listFromConferenceToRelease(ConferenceDB.getConferenceFromEditor(editorUserName).getName()));

            session.setAttribute("fullName", request.getParameter("fullName"));
            session.setAttribute("userName", request.getParameter("userName"));
            session.setAttribute("password", request.getParameter("password"));
            session.setAttribute("emailAddress", request.getParameter("emailAddress"));
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
