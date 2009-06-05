<%--
    Document   : authorportal
    Group      : 3, Java Team Hunger Force
    Class      : CS575, Spring 2009
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMS - Author Portal</title>

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
.style1 {color: #FF0000}
-->
</style></head>

<body class="oneColFixCtr">

<div id="container">
  <div id="mainContent">
    <h1> Conference Management System</h1>
    <h2>Author Portal</h2>
    <h3><a href="<c:url value='/Logout'/>"><font color="red">Logout</font></a></h3>

    <table width="100%" border="0">

    <tr>
        <td colspan="2">
            <h3>Upload Paper</h3>
            <form enctype="multipart/form-data" action="<c:url value='/AuthorPortalUploadFileServlet'/>" method="post">
                <table>
                    <tr>
                        <td width="25%">Paper Name</td>
                        <td width="25%">Conference</td>
                        <td width="25%">PDF Upload</td>
                        <td width="25%"></td>
                    </tr>
                    <tr>
                        <td width="25%"><input type="text" name="paperName" id="paperName" value="${paperName}"></td>
                        <td width="25%">
                        <select name="selectedConference">
                            <c:forEach var="currConference" items="${allConferences}">
                                <option value="${currConference.conferenceID}">${currConference.name}</option>
                            </c:forEach>
                        </select>
                        </td>
                        <td width="25%"><input type="file" name="dataFile" size="40" value="${dataFile}"></td>
                        <td width="25%"><input type="submit" value="Upload"></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    </table>

<p align="center"><h4><font>${au_errMsg}</font></h4></p>

<h3>View Papers Uploaded by Me</h3>

    <c:forEach var="currPaper" items="${papersFromAuthor}">
    <table width="100%" border="1">
        <tr>
                <td align="center" width ="25%"><b>Paper Name</b></td>
                <td align="center" width ="25%"><b>Paper Author</b></td>
                <td align="center" width ="25%"><b>Conference</b></td>
                <td align="center" width ="25%"><b>File Name</b></td>
        </tr>
    </table>
    <table width="100%" border="1">
        <tr>
             <td align="center" width ="25%">${currPaper.key.paperName}</td>
             <td align="center" width ="25%">${currPaper.key.authorName}</td>
             <td align="center" width ="25%">${currPaper.key.conference.name}</td>
             <td align="center" width ="25%"><form id="assign" name="assign" method="post" action="<c:url value='/DownloadPaperServlet'/>"><input type="hidden" name="paperID" value="${currPaper.key.paperID}"><input type="submit" name="submit" id="submit" value="Download" /></form></td>
        </tr>
        <tr><td colspan="4"><b>Keywords:</b>  ${currPaper.key.paperKeywords}</td></tr>
        <tr><td colspan="4"><b>Abstract:</b>  ${currPaper.key.paperAbstract}</td></tr>
        <c:forEach var="currFeedback" items="${currPaper.value}">
            <c:if test="${currFeedback != null}">
                <tr><td colspan="4">&nbsp;&nbsp;**<b>Reviewer:</b>  ${currFeedback.reviewerName}, <b>Content:</b>  ${currFeedback.contentRate}, <b>Innovative:</b>  ${currFeedback.innovativeRate}, <b>Quality:</b>  ${currFeedback.qualityRate}, <b>Depth:</b>  ${currFeedback.depthRate}</td></tr>
                <tr><td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Comments:</b>  ${currFeedback.commentsBox}</td></tr>
            </c:if>
        </c:forEach>
    </table>
    <br /><br />
    </c:forEach>

    <p>&nbsp;</p>   
</div>
</div>
</body>
</html>
