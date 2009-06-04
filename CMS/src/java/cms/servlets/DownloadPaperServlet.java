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
import cms.services.FileSystemService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DownloadPaperServlet extends HttpServlet
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
        FileSystemService fs = new FileSystemService();

        HttpSession session = request.getSession();
        synchronized(session)
        {
            // Get paperID
            int paperID = Integer.parseInt(request.getParameter("paperID"));
            Paper paper = fs.downloadPaper(paperID);

            InputStream is = paper.getInputStream();


            // Init servlet response.
            response.reset();
            response.setBufferSize(10240);
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", paper.getSizeInBytes()+ "");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + paper.getFileName() + "\"");

            // Prepare streams.
            BufferedInputStream input = null;
            BufferedOutputStream output = null;

            try {
                // Open streams.
                input = new BufferedInputStream(is, 10240);
                output = new BufferedOutputStream(response.getOutputStream(), 10240);

                // Write file contents to response.
                byte[] buffer = new byte[10240];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                // Finalize task.
                output.flush();
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }



            String editorUserName = ((User)session.getAttribute("loggedInUser")).getUserName();
            session.setAttribute("papersFromConference", lp.listFromConference(ConferenceDB.getConferenceFromEditor(editorUserName).getName(), 1));
            session.setAttribute("getReviewers", a.getReviewers());
            session.setAttribute("getConferenceName", ConferenceDB.getConferenceFromEditor(editorUserName).getName());
        }

        String url = "/Editor/editorportal.jsp";
        url = response.encodeURL(url);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
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
