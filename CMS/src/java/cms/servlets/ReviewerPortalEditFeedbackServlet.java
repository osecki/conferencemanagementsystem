/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.servlets;

import cms.entities.Feedback;
import cms.entities.User;
import cms.services.ListPaperService;
import cms.services.FeedbackSystemService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewerPortalEditFeedbackServlet extends HttpServlet
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
        ListPaperService lp = new ListPaperService();
        FeedbackSystemService fs = new FeedbackSystemService();

        if(request.getParameter("commentBoxEdit").length()==0 )
        {
            badData = true;
        }

        synchronized(session)
        {
            String reviewerUserName = ((User)session.getAttribute("loggedInUser")).getUserName();

            Feedback f = null;
            try
            {
                f = new Feedback(Integer.parseInt(request.getParameter("selectedPapers2")), reviewerUserName, Integer.parseInt(request.getParameter("contentEdit")), Integer.parseInt(request.getParameter("innovativeEdit")), Integer.parseInt(request.getParameter("qualityEdit")), Integer.parseInt(request.getParameter("depthEdit")), request.getParameter("commentBoxEdit"));
            }
            catch (Exception e)
            {
                badData = true;
            }


             if(!badData)
             {
                if( fs.edit(f) )
                {
                    errMsg = "<font color=\"blue\">Your feedback has been edited for this paper.";
                }
                else
                {
                    errMsg = "<font color=\"red\">There was a problem. The feedback could not be edited. Please try again.";
                }
            }
            else
            {
                 errMsg = "<font color=\"red\">There was a problem. The feedback could not be edited. Please try again.";
            }

            session.setAttribute("errMsg", errMsg);
            session.setAttribute("papersForReviewer", lp.listAssignedToReviewer(reviewerUserName));
            session.setAttribute("papersForReviewerNoFeedback", lp.listAssignedToReviewerNoFeedback(reviewerUserName));
        }

        String url = "/Reviewer/reviewerportal.jsp";
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
