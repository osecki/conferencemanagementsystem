/*
 *   Group      : 3, Java Team Hunger Force
 *   Class      : CS575, Spring 2009
*/

package cms.servlets;

// Outside Jars
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import cms.services.FileSystemService;
import cms.services.ConferenceSystemService;
import cms.services.ListPaperService;
import cms.services.OCRService;
import cms.entities.User;
import cms.entities.Paper;
import com.asprise.util.pdf.PDFReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorPortalUploadFileServlet extends HttpServlet
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
        HttpSession session = request.getSession();

        //TODO Replace with dynamic binding
        FileSystemService fs = new FileSystemService();
        ConferenceSystemService c = new ConferenceSystemService();
        ListPaperService lp = new ListPaperService();
        OCRService ocr = new OCRService();

        String au_errMsg = null;
        boolean badData = false;

        try
        {
            if( ! ServletFileUpload.isMultipartContent(request) )
            {
                au_errMsg="<font color=\"red\">One or more fields were left blank.";
                badData = true;
            }
        }
        catch(NullPointerException e)
        {
            au_errMsg="<font color=\"red\">One or more fields were left blank.";
            badData = true;
        }
      
        synchronized(session)
        {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Parse the request
            List /* FileItem */ items = null;

            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException ex) {
                Logger.getLogger(AuthorPortalUploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Process the uploaded items
            Iterator iter = items.iterator();

            // Variables to hold things during the process
            InputStream uploadedStream = null;
            InputStream uploadedStream2 = null;
            String fileName = null;
            String contentType = "";
            int sizeInBytes = 0;
            HashMap<String, String> parameters = new HashMap<String, String>();

            while (iter.hasNext())
            {
                FileItem item = (FileItem) iter.next();

                // Process file
                if ( ! item.isFormField()) {
                    fileName = item.getName();
                    contentType = item.getContentType();
                    sizeInBytes = (int)item.getSize();

                    try {
                        uploadedStream = item.getInputStream();
                        uploadedStream2 = item.getInputStream();
                    } catch (Exception ex) {
                        Logger.getLogger(AuthorPortalUploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Process items
                else
                {
                    parameters.put(item.getFieldName(), item.getString());
                }
            }

            for ( String param : parameters.values())
            {
                if (param == null || param.equals(""))
                    badData = true;
            }

            if ( sizeInBytes == 0 || uploadedStream == null || fileName == null || ! contentType.equals("application/pdf"))
                badData = true;

            // Get current date
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date currentDate = new Date();

            if ( parameters.get("selectedConference") == null )
                badData = true;

            // Get Conference due date
            Date dueDate = c.getConferenceByID(Integer.parseInt(parameters.get("selectedConference"))).getDueDate();

            if ( currentDate.after(dueDate) )
                badData = true;

            if( ! badData )
            {
                // Build up item to send
                User tempUser = (User)session.getAttribute("loggedInUser");
                Paper newPaper = new Paper (parameters.get("paperName"), tempUser.getUserName(), fileName, c.getConferenceByID(Integer.parseInt(parameters.get("selectedConference"))).getConferenceID(), "", "", uploadedStream, sizeInBytes);

                boolean paperUploadedSuccessfully = fs.uploadPaper(newPaper);

                if(paperUploadedSuccessfully)
                {
                    au_errMsg = "<font color=\"blue\">Paper successfully uploaded to the server.";
                }
                else
                {
                    au_errMsg = "<font color=\"red\">There was a problem during the upload. Have you already uploaded a paper with this name to this conference? Please try again.";
                }

                // Now handle the OCR portion of the process
                File f = new File("temp.pdf");
                OutputStream out = new FileOutputStream(f);
                byte buf[] = new byte[1024];
                int len;
                while((len=uploadedStream2.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                
                PDFReader reader = new PDFReader(f);
                reader.open();

                String firstPage = "";
                if ( reader.getNumberOfPages() > 0 )
                    firstPage = reader.extractTextFromPage(0);

                // Grab keywords and abstract and commit to DB

                if(paperUploadedSuccessfully)
                {
                    int lastPaper = fs.getLastPaperID();
                    ocr.extractKeywordsAbstract(firstPage, lastPaper);
                }
                
                reader.close();


            }
            else
            {
                au_errMsg = "<font color=\"red\">There was a problem during the upload. Did you fill in all of the fields? Has the due date of " + dueDate + " passed? Please try again.";
            }

            session.setAttribute("au_errMsg", au_errMsg);
            session.setAttribute("allConferences", c.getAllConferences());
            String authorUserName = ((User)session.getAttribute("loggedInUser")).getUserName();
            session.setAttribute("papersFromAuthor", lp.listFromAuthor(authorUserName));
        }

        String url = "/Author/authorportal.jsp";
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
