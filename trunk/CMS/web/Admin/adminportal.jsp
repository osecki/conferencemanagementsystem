<%--
    Document   : adminportal
    Group      : 3, Java Team Hunger Force
    Class      : CS575, Spring 2009
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMS - Admin Portal</title>
<script src="<c:url value='includes\date-functions.js'/>" type="text/javascript"></script>
<script src="<c:url value='includes\datechooser.js'/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='includes\datechooser.css'/>">
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
    <h2>Admin Portal</h2>
    <h3><a href="<c:url value='/Logout'/>"><font color="red">Logout</font></a></h3>
    <table width="100%" border="0">
      <tr>
        <td valign="top"><h3>Create Editor</h3>
    <form id="form1" name="form1" method="post" action="">
        <p>
          <label>Student Name
          <input type="text" name="studentName" id="studentName" />

          </label>
     	</p>
        <p>
          <label>Username
          <input type="text" name="userName" id="username" />
          </label>
     	</p>
        <p>
          <label>Initial Password
          <input type="text" name="password" id="password" />

          </label>
     	</p>
        <p>
          <label>E-Mail
          <input type="text" name="emailAddress" id="eMail" />
          </label>
     	</p>
        <p>
          <input type="submit" name="submit" id="submit" value="Submit" />

        </p>
    </form>
    <br />
        </td>
       <td valign="top"><h3>Create Conference</h3>
            <form id="createConference" name="createConference" method="post" action="">
	        <p>
    	      <label>Conference  Name
        	  <input type="text" name="conference" id="conference" />
	          </label>
    	 	</p>
	        <p>
    	      <label>Location
        	  <input type="text" name="location" id="location" />
	          </label>
	        </p>
	        <label>Event Date
        	  <input id="datetime2" size="10" maxlength="10" name="eventDate" type="text" /><img src="<c:url value='includes\calendar.gif'/>" onclick="showChooser(this, 'datetime2', 'chooserSpan3', 1950, 2010, Date.patterns.ShortDatePattern, false);">
			  <div id="chooserSpan3" class="dateChooser select-free" style="display: none; visibility: hidden; width: 160px;"></div>
	          </label>
    	      <label>Due Date
        	  <input id="datetime3" size="10" maxlength="10" name="dueDate" type="text" /><img src="<c:url value='includes\calendar.gif'/>" onclick="showChooser(this, 'datetime3', 'chooserSpan3', 1950, 2010, Date.patterns.ShortDatePattern, false);">
			  <div id="chooserSpan3" class="dateChooser select-free" style="display: none; visibility: hidden; width: 160px;"></div>
	          </label>
	        <p>
	          <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
            </form>
        </td>
      </tr>
      <tr valign="top">

        <td><h3>Assign Editor to Conference</h3>
            <form action="">
            <label>Editor :
            <select name="selectedEditor">
            <c:forEach var="currEditor" items="${availableEditors}">
                <option value="${currEditor.userName}">${currEditor.fullName}</option>
            </c:forEach>
            </select>
            </label>

            <br />
            <label>Conference :
            <select name="selectedConference">
            <c:forEach var="currConference" items="${availableConferences}">
                <option value="${currConference.conferenceID}">${currConference.name}</option>
            </c:forEach>
            </select>
            </label>
	        <p>

	        <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
            </form>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p align="center"><h4>${errMsg}</h4></p>
  <!-- end #mainContent --></div>
<!-- end #container --></div>

</body>
</html>
