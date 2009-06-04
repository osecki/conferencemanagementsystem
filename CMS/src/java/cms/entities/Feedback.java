package cms.entities;

import java.io.Serializable;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class Feedback implements Serializable
{
    private int feedbackID;
    private int paperID;
    private String reviewerName;
    private int contentRate;
    private int innovativeRate;
    private int qualityRate;
    private int depthRate;
    private String commentsBox;

    public Feedback ()
    {
        
    }

    public Feedback (int pn, String rn, int cr, int ir, int qr, int dr, String cb)
    {
        paperID = pn;
        reviewerName = rn;
        contentRate = cr;
        innovativeRate = ir;
        qualityRate = qr;
        depthRate = dr;
        commentsBox = cb;
    }

    /**
     * @return the paperName
     */
    public int getPaperID() {
        return paperID;
    }

    /**
     * @param paperName the paperName to set
     */
    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

    /**
     * @return the reviewerName
     */
    public String getReviewerName() {
        return reviewerName;
    }

    /**
     * @param reviewerName the reviewerName to set
     */
    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    /**
     * @return the contentRate
     */
    public int getContentRate() {
        return contentRate;
    }

    /**
     * @param contentRate the contentRate to set
     */
    public void setContentRate(int contentRate) {
        this.contentRate = contentRate;
    }

    /**
     * @return the innovativeRate
     */
    public int getInnovativeRate() {
        return innovativeRate;
    }

    /**
     * @param innovativeRate the innovativeRate to set
     */
    public void setInnovativeRate(int innovativeRate) {
        this.innovativeRate = innovativeRate;
    }

    /**
     * @return the qualityRate
     */
    public int getQualityRate() {
        return qualityRate;
    }

    /**
     * @param qualityRate the qualityRate to set
     */
    public void setQualityRate(int qualityRate) {
        this.qualityRate = qualityRate;
    }

    /**
     * @return the depthRate
     */
    public int getDepthRate() {
        return depthRate;
    }

    /**
     * @param depthRate the depthRate to set
     */
    public void setDepthRate(int depthRate) {
        this.depthRate = depthRate;
    }

    /**
     * @return the commentsBox
     */
    public String getCommentsBox() {
        return commentsBox;
    }

    /**
     * @param commentsBox the commentsBox to set
     */
    public void setCommentsBox(String commentsBox) {
        this.commentsBox = commentsBox;
    }

    /**
     * @return the feedbackID
     */
    public int getFeedbackID() {
        return feedbackID;
    }

    /**
     * @param feedbackID the feedbackID to set
     */
    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }
}
