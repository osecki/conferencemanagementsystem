package cms.entities;
import java.io.InputStream;
import java.io.Serializable;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class Paper implements Serializable
{
    private int paperID;
    private String paperName;
    private int conferenceID;
    private String paperAbstract;
    private String paperKeywords;
    private String authorName;
    private String fileName;
    private InputStream inputStream;
    private int sizeInBytes;

    public Paper ()
    {
        
    }

    public Paper (String n, String a, String fp, int c, String pa, String pk, InputStream i, int size)
    {
        paperName = n;
        authorName = a;
        fileName = fp;
        conferenceID = c;
        paperAbstract = pa;
        paperKeywords = pk;
        inputStream = i;
        sizeInBytes = size;
    }

    /**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * @return the paperAbstract
     */
    public String getPaperAbstract() {
        return paperAbstract;
    }

    /**
     * @param paperAbstract the paperAbstract to set
     */
    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    /**
     * @return the paperKeywords
     */
    public String getPaperKeywords() {
        return paperKeywords;
    }

    /**
     * @param paperKeywords the paperKeywords to set
     */
    public void setPaperKeywords(String paperKeywords) {
        this.paperKeywords = paperKeywords;
    }

    /**
     * @return the paperName
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * @param paperName the paperName to set
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    /**
     * @return the conferenceID
     */
    public int getConferenceID() {
        return conferenceID;
    }

    /**
     * @param conferenceID the conferenceID to set
     */
    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the is
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream is) {
        inputStream = is;
    }

    /**
     * @return the sizeInBytes
     */
    public int getSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * @param sizeInBytes the sizeInBytes to set
     */
    public void setSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    /**
     * @return the paperID
     */
    public int getPaperID() {
        return paperID;
    }

    /**
     * @param paperID the paperID to set
     */
    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

    @Override
    public boolean equals(Object o)
    {
        Paper key = (Paper)o;

        if ( key.getPaperID() == paperID )
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.paperID;
        hash = 67 * hash + (this.paperName != null ? this.paperName.hashCode() : 0);
        return hash;
    }
}
