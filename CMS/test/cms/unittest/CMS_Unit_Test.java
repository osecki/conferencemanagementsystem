/*
 * Project:  Conference Management System (CMS)
 * Group 3:  Java Team Hunger Force
 * Team Members:  Jordan, Piyush, Keith, Brad, Danielle, Uri
 */

package cms.unittest;
import java.sql.Date;
import junit.framework.*;

// Here for reference to the stubs
import cms.services.*;

// Common classes that all will share
import cms.entities.*;

// For dynamic binding
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class CMS_Unit_Test extends TestCase
{
	// Instantiate each web services interface
	protected static Account a;
	protected static ConferenceSystem cc;
	protected static FeedbackSystem fe;
	protected static FileSystem fi;
	protected static ListPaper lp;
	protected static OCR ocr;

	// Standard Constructor
	public CMS_Unit_Test ( String name )
	{
		super(name);
	}

	// Method to instantiate all of the classes, dynamically
	protected void setUp()
	{
        /*
        // WSDL URLs
        String Awsdl = "http://localhost:8080/Account/AccountService?WSDL";
        String CCwsdl = "http://localhost:8080/ConferenceSystem/ConferenceSystemService?WSDL";
        String FEwsdl = "http://localhost:8080/FeedbackSystem/FeedbackSystemService?WSDL";
        String FIwsdl = "http://localhost:8080/FileSystem/FileSystemService?WSDL";
        String LPwsdl = "http://localhost:8080/ListPaper/ListPaperService?WSDL";
        String OCRwsdl = "http://localhost:8080/OCR/OCRService?WSDL";

        // Namespace
        String namespace = "http://localhost:8080/CMS/services";

        // Name of implementing services
        String Aname = "AccountService";
        String CCname = "ConferenceSystemService";
        String FEname = "FeedbackSystemService";
        String FIname = "FileSystemService";
        String LPname = "ListPaperService";
        String OCRname = "OCRService";

        try
        {
            // Dynamic Binding
            Service Aservice = Service.create(new URL(Awsdl), new QName(namespace, Aname));
            a = Aservice.getPort(Account.class);

            Service CCservice = Service.create(new URL(CCwsdl), new QName(namespace, CCname));
            cc = CCservice.getPort(ConferenceSystem.class);

            Service FEservice = Service.create(new URL(FEwsdl), new QName(namespace, FEname));
            fe = FEservice.getPort(FeedbackSystem.class);

            Service FIservice = Service.create(new URL(FIwsdl), new QName(namespace, FIname));
            fi = FIservice.getPort(FileSystem.class);

            Service LPservice = Service.create(new URL(LPwsdl), new QName(namespace, LPname));
            lp = LPservice.getPort(ListPaper.class);

            Service OCRservice = Service.create(new URL(OCRwsdl), new QName(namespace, OCRname));
            ocr = OCRservice.getPort(OCR.class);
        }
        catch (Exception e )
        {
            System.err.println("Dynamic Binding error");
        }*/

        // TODO Dynamic Binding
        a = new AccountService();
        cc = new ConferenceSystemService();
        fe = new FeedbackSystemService();
        fi = new FileSystemService();
        lp = new ListPaperService();
        ocr = new OCRService();
	}

	// Method testAccount() - Responsible for unit testing instantiation and each method of Account Interface
	public static void testAccount()
	{
		// Testing Account Service
		System.out.println("Testing ... Account Services Interface ...");

		// Testing method createAccount()
		Assert.assertEquals(true, a.createAccount("jmo1", "AUTHOR", "Jordan Osecki", "jmo1@drexel.edu", "pass1")); // Normal for Author
		Assert.assertEquals(true, a.createAccount("jmo2", "ADMINISTRATOR", "Jordan Osecki", "jmo2@drexel.edu", "pass2")); // Normal for Admin
		Assert.assertEquals(true, a.createAccount("jmo3", "REVIEWER", "Jordan Osecki", "jmo3@drexel.edu", "pass3")); // Normal for Reviewer
		Assert.assertEquals(true, a.createAccount("jmo4", "EDITOR", "Jordan Osecki", "jmo4@drexel.edu", "pass4")); // Normal for Editor
		Assert.assertEquals(false, a.createAccount("jmo1", "REVIEWER", "Jordan Osecki", "jmo1@drexel.edu", "pass1")); // Same username as another
		Assert.assertEquals(false, a.createAccount("jmo5", "Cadet", "Jordan Osecki", "jmo1@drexel.edu", "pass1")); // Incorrect Userrole

		// Testing method resetPassword()
		Assert.assertNotSame("Error", a.resetPassword("jmo1", "Jordan Osecki", "jmo1@drexel.edu")); // Normal request for user in system
		Assert.assertEquals("Error", a.resetPassword("jmo0", "Jordan Osecki", "jmo1@drexel.edu")); // Username doesn't match any records
		Assert.assertEquals("Error", a.resetPassword("jmo1", "Jordan Osecki0", "jmo1@drexel.edu")); // Name doesn't match any records
		Assert.assertEquals("Error", a.resetPassword("jmo1", "Jordan Osecki", "jmo1@drexel.edu0")); // E-mail doesn't match any records

		// Testing method assignEditor()
		cc.addConference(new Conference ("CS Conf", "Phila, PA", new Date(2009, 10, 25), new Date(2009, 10, 20)));
		Assert.assertEquals(true, a.assignEditor(new Conference ("CS Conf", "Phila, PA", new Date(2009, 10, 25), new Date(2009, 10, 20)), "jmo4")); // Normal
		Assert.assertEquals(false, a.assignEditor(new Conference ("CS Conf2", "Phila, PA", new Date(2009, 10, 25), new Date(2009, 10, 20)), "jmo4")); // Conf doesn't exist
		Assert.assertEquals(false, a.assignEditor(new Conference ("CS Conf", "Phila, PA", new Date(2009, 10, 25), new Date(2009, 10, 20)), "jmo25")); // Editor doesn't exist

		// Testing method assignReviewer()
		Assert.assertEquals(true, a.assignReviewer("jmo3", 1)); // Normal
		Assert.assertEquals(false, a.assignReviewer("jmo35", 1)); // Wrong Reviewer
		Assert.assertEquals(false, a.assignReviewer("jmo3", 1000)); // Wrong Paper

		// Testing method releaseToAuthor()
		Assert.assertEquals(true, a.releaseToAuthor(5)); // Normal
		Assert.assertEquals(false, a.releaseToAuthor(1000)); // Wrong Paper

		// Testing method getAvailableEditors()
		Assert.assertNotNull(a.getAvailableEditors()); // Test that an object is returned
        Assert.assertEquals(1, a.getAvailableEditors());

        // Testing method getReviewers()
		Assert.assertNotNull(a.getReviewers()); // Test that an object is returned
        Assert.assertEquals(3, a.getReviewers());

		System.out.println(" ");
	}

	// Method testConferenceSystem() - Responsible for unit testing instantiation and each method of ConferenceSystem Interface
	public static void testConferenceSystem()
	{
		// Testing ConferenceSystem Service
		System.out.println("Testing ... Conference System Services Interface ...");

		// Testing method addConference()
		Assert.assertEquals(true, cc.addConference(new Conference ("Wiki Conf", "Phila, PA", new Date(2009, 5, 25), new Date(2009, 5, 20)))); // Normal
		Assert.assertEquals(false, cc.addConference(new Conference ("Wiki Conf", "Phila, PA", new Date(2009, 5, 25), new Date(2009, 5, 20)))); // Try to do one that exists

		// Testing method getAvailableConferences()
		Assert.assertNotNull(cc.getAvailableConferences()); // Test that an object is returned
        Assert.assertEquals(1, cc.getAvailableConferences());

        // Testing method getConferenceByID()
		Assert.assertNotNull(cc.getConferenceByID(1)); // Test that an object is returned
        Assert.assertEquals("AI Conference", cc.getConferenceByID(1).getName());
        Assert.assertNull(cc.getConferenceByID(10000)); // Test here it is null

        // Testing method getAllConferences()
		Assert.assertNotNull(cc.getAllConferences()); // Test that an object is returned
        Assert.assertEquals(5, cc.getAllConferences());

		System.out.println(" ");
	}

	// Method testFeedbackSystem() - Responsible for unit testing instantiation and each method of FeedbackSystem Interface
	public static void testFeedbackSystem()
	{
		// Testing FeedbackSystem Service
		System.out.println("Testing ... Feedback System Services Interface ...");

		// Testing method send()
        Feedback f = new Feedback(1, "jmo3", 1, 2, 3, 4, "WOW");
		Assert.assertEquals(true, fe.send(f)); // Normal
        f.setPaperID(1000);
		Assert.assertEquals(false, fe.send(f)); // Paper doesn't exist
        f.setPaperID(1);
        f.setReviewerName("jmo366");
		Assert.assertEquals(false, fe.send(f)); // Reviewer doesn't exist

		// Testing method edit()
		Feedback f2 = new Feedback(1, "jmo3", 5, 5, 5, 5, "WOWZERS");
		Assert.assertEquals(true, fe.edit(f2)); // Normal
        f.setPaperID(1000);
		Assert.assertEquals(false, fe.edit(f2)); // Paper doesn't exist
        f.setPaperID(1);
        f.setReviewerName("jmo366");
		Assert.assertEquals(false, fe.edit(f2)); // Reviewer doesn't exist

		// Testing method receive()
		Assert.assertNotNull(fe.receive(1, "jmo3")); // Normal
        Assert.assertEquals("WOWZERS", fe.receive(1, "jmo3").getCommentsBox());
		Assert.assertNull(fe.receive(1000, "jmo3")); // Wrong Paper
		Assert.assertNull(fe.receive(1, "jmo366")); // Wrong reviewer

		System.out.println(" ");
	}

	// Method testFileSystem() - Responsible for unit testing instantiation and each method of FileSystem Interface
	public static void testFileSystem()
	{
		// Testing FileSystem Service
		System.out.println("Testing ... File System Services Interface ...");

        // Testing method downloadPaper()
        Paper temp = fi.downloadPaper(1);
		Assert.assertNotNull(temp); // Normal
        Assert.assertEquals("AI Paper", temp.getPaperName());
		Assert.assertNull(fi.downloadPaper(1000)); // Wrong name

		// Testing method uploadPaper()
		Assert.assertEquals(true, fi.uploadPaper(new Paper ("New Paper", "jmo1", "test.pdf", 1, "Abs", "Key", temp.getInputStream(), 54737))); // Normal
		Assert.assertEquals(false, fi.uploadPaper(new Paper ("New Paper", "jmo100", "test.pdf", 1, "Abs", "Key", temp.getInputStream(), 54737))); // Incorrect author

        // Testing method getLastPaperByID()
		Assert.assertNotNull(fi.getLastPaperID()); // Test that an object is returned
        Assert.assertEquals(6, fi.getLastPaperID());

		System.out.println(" ");
	}

	// Method testListPaper() - Responsible for unit testing instantiation and each method of ListPaper Interface
	public static void testListPaper()
	{
		// Testing ListPaper Service
		System.out.println("Testing ... List Paper Services Interface ...");

		// Testing method listFromConference()
		Assert.assertNotNull(lp.listFromConference("AI Conference")); // Normal
        Assert.assertEquals(4, lp.listFromConference("AI Conference"));
		Assert.assertNull(lp.listFromConference("BLAH")); // Non-existent conference

        // Testing method listFromConferenceToRelease()
		Assert.assertNotNull(lp.listFromConferenceToRelease("AI Conference")); // Normal
        Assert.assertEquals(3, lp.listFromConferenceToRelease("AI Conference"));
		Assert.assertNull(lp.listFromConferenceToRelease("BLAH")); // Non-existent conference

		// Testing method listAssignedToReviewer()
		Assert.assertNotNull(lp.listAssignedToReviewer("jmo3")); // Normal
		Assert.assertNull(lp.listAssignedToReviewer("jmo34234")); // Non-existent reviewer

        // Testing method listAssignedToReviewerNoFeedback()
		Assert.assertNotNull(lp.listAssignedToReviewerNoFeedback("jmo3")); // Normal
		Assert.assertNull(lp.listAssignedToReviewerNoFeedback("jmo34234")); // Non-existent reviewer

		// Testing method listFromAuthor()
		Assert.assertNotNull(lp.listFromAuthor("jmo1")); // Normal
        Assert.assertEquals(1, lp.listFromAuthor("jmo1"));
		Assert.assertNull(lp.listFromAuthor("jmo523453")); // Non-existent author

		System.out.println(" ");
	}

	// Method testOCR() - Responsible for unit testing instantiation and each method of OCR Interface
	public static void testOCR()
	{
		// Testing OCR Service
		System.out.println("Testing ... OCR Services Interface ...");

		// Testing method invokeOCR()
		Assert.assertEquals(true, ocr.extractKeywordsAbstract("BLAA ABSTRACT BODY Categories and Subject Descriptors Keywords BODY2 INTRODUCTION", 6)); // Normal

		System.out.println(" ");
	}

	public static Test suite()
	{
		return new TestSuite(CMS_Unit_Test.class);
	}

	// Method Main() - driver of the unit test application
	public static void main (String[] args)
	{
		// Welcome Messages
		System.out.println("Unit Tester for the Conference Management System (CMS).");
		System.out.println("A web services application produced by Group 3, Java Team Hunger Force.");
		System.out.println("Created for the CS575 Software Design class at Drexel, Spring 2009.");
		System.out.println(" ");
        System.out.println("This suite assumes that the database has been pre-populated with the script included with submission.");
        System.out.println(" ");
		System.out.println("The six service interfaces and their respective methods will be tested below.");
		System.out.println(" ");

		// JUnit Stuff
		junit.textui.TestRunner.run(suite());
	}
}
