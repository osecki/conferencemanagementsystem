<%-- 
    Document   : editorportal
    Group      : 3, Java Team Hunger Force
    Class      : CS575, Spring 2009
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMS - Editor Portal</title>
<style type="text/css">
<!--
body {
	font: 100% Verdana, Arial, Helvetica, sans-serif;
	background: #666666;
	margin: 0; /* it's good practice to zero the margin and padding of the body element to account for differing browser defaults */
	padding: 0;
	text-align: center; /* this centers the container in IE 5* browsers. The text is then set to the left aligned default in the #container selector */
	color: #000000;
}
.oneColFixCtr #container {
	width: 780px;  /* using 20px less than a full 800px width allows for browser chrome and avoids a horizontal scroll bar */
	background: #FFFFFF;
	margin: 0 auto; /* the auto margins (in conjunction with a width) center the page */
	border: 1px solid #000000;
	text-align: left; /* this overrides the text-align: center on the body element. */
}
.oneColFixCtr #mainContent {
	padding: 0 20px; /* remember that padding is the space inside the div box and margin is the space outside the div box */
}
-->
</style></head>

<body class="oneColFixCtr">

<div id="container">
  <div id="mainContent">
    <h1> Conference Management System</h1>
    <h2>Editor Portal - ${getConferenceName}</h2>
    <h3><a href="<c:url value='/Logout'/>"><font color="red">Logout</font></a></h3>

    <table width="100%" border="0">
      <tr>
        <td>
        <h3>Create Reviewer</h3>
    <form id="createEditor" name="createEditor" method="post" action="<c:url value='/EditorPortalCreateReviewerServlet'/>">
        <p>
          <label>Full Name
          <input type="text" name="fullName" id="fullName" value="${fullName}"/>
          </label>
     	</p>
        <p>
          <label>Username
          <input type="text" name="userName" id="username" value="${userName}"/>
          </label>
     	</p>
        <p>
          <label>Initial Password
          <input type="text" name="password" id="password" value="${password}"/>
          </label>
     	</p>
        <p>
          <label>E-Mail
          <input type="text" name="emailAddress" id="eMail" value="${emailAddress}"/>
          </label>
     	</p>
        <p>
          <input type="submit" name="submit" id="submit" value="Submit" />
        </p>
    </form>

    <p>&nbsp;</p>
        </td>
        <td valign="top">

        <h3>Match Papers and Reviewers</h3>
        <form id="assign" name="assign" method="post" action="<c:url value='/EditorPortalAssignReviewerServlet'/>">
        <label>Paper:
            <select name="selectedPapers">
            <c:forEach var="currPaper" items="${papersFromConference}">
                <option value="${currPaper.key.paperID}">${currPaper.key.paperName}</option>
            </c:forEach>
            </select>

            </label>
            <br />
            <label>Reviewers:
            <select name="selectedReviewers">
            <c:forEach var="currReviewer" items="${getReviewers}">
                <option value="${currReviewer.userName}">${currReviewer.fullName}</option>
            </c:forEach>
            </select>
            </label>
	        <p>
	          <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
          </form>
        </p>
        
        <h3>Release Papers With Feedback</h3>
                <form id="assign" name="assign" method="post" action="<c:url value='/EditorPortalReleasePaperServlet'/>">
        <label>Paper :
            <select name="selectedPapers2">
            <c:forEach var="currPaper" items="${papersFromConference}">
                <option value="${currPaper.key.paperID}">${currPaper.key.paperName}</option>
            </c:forEach>
            </select>
            </label>
            <br />
	        <p>
	          <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
          </form>
        </td>
      </tr>
    </table>

    <h3>View Conference Papers and their Current Reviewers</h3>

    <table width="100%" border="0">
    <tr>
            <td>Paper Name</td>
            <td>Paper Author</td>
            <td>File Name</td>
    </tr>

    <c:forEach var="currPaper" items="${papersFromConference}">
    <tr>
         <td>${currPaper.key.paperName}</td>
         <td>${currPaper.key.authorName}</td>
         <td><form id="assign" name="assign" method="post" action="<c:url value='/DownloadPaperServlet'/>"><input type="hidden" name="paperID" value="${currPaper.key.paperID}"><input type="submit" name="submit" id="submit" value="Download" /></form></td>
    </tr>

        <c:forEach var="currFeedback" items="${currPaper.value}">
                <tr colspan="3".
                <td>${currFeedback.reviewerName} is currently reviewing this.</td>
                </tr>
        </c:forEach>

    </c:forEach>

    </table>

    <p>&nbsp;</p>
    <p align="center"><h4>${errMsg}</h4></p>
	<!-- end #mainContent --></div>
<!-- end #container --></div>
</body>
</html>
