package cms.entities;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class Paper
{
    private String name;
    private String authorName;
    private String filePath;
    private String conferenceName;
    private String paperAbstract;
    private String paperKeywords;

    public Paper (String n, String a, String fp, String c, String pa, String pk)
    {
        name = n;
        authorName = a;
        filePath = fp;
        conferenceName = c;
        paperAbstract = pa;
        paperKeywords = pk;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the conferenceName
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * @param conferenceName the conferenceName to set
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
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
}
