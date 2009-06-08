/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

package cms.data;

/**
 * Source: http://bobcat.webappcabaret.net/javachina/jc/share/PwGen.htm
 */

public class PwGen
{
  public static String getPassword(int n) {
    char[] pw = new char[n];
    int c  = 'A';
    int  r1 = 0;
    for (int i=0; i < n; i++)
    {
      r1 = (int)(Math.random() * 3);
      switch(r1) {
        case 0: c = '0' +  (int)(Math.random() * 10); break;
        case 1: c = 'a' +  (int)(Math.random() * 26); break;
        case 2: c = 'A' +  (int)(Math.random() * 26); break;
      }
      pw[i] = (char)c;
    }
    return new String(pw);
  }
}
