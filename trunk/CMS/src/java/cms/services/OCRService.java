package cms.services;

import cms.data.PaperDB;

/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

public class OCRService implements OCR
{
    /*
     * Method:  extractKeywordsAbstract
     * Input:  String of first PDF page text
     * Output:  boolean
     * Algorithm:  The method will take in this string, extract the keywords and abstract
     * if they exist, enter them in the DB, and return a boolean explaining if it worked
    */
    public boolean extractKeywordsAbstract (String firstPage, int paperID)
    {
        return PaperDB.addKeywordsAbstract(firstPage, paperID);
    }
}
