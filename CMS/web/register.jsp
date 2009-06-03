<%-- 
    Document   : register
    Group      : 3, Java Team Hunger Force
    Class      : CS575, Spring 2009
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Author Registration</title>
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
    <h2>Author Registration</h2>
    <form id="form1" name="form1" method="post" action="<c:url value='/RegisterAuthorServlet'/>">
      <label>Full Name
          <input type="text" name="r_fullName" id="r_fullName" value="${r_fullName}" />
      </label>
      
      <p>
      <label>Username
      <input type="text" name="r_userName" id="r_userName" value="${r_userName}"/>
      </label>
      </p>
        <p>
          <label>Password
          <input type="password" name="r_password" id="r_password" value="${r_password}"/>
          </label>
      </p>
        <p>
          <label>Re-Type Password
          <input type="password" name="r_repeatPassword" id="r_repeatPassword" value="${r_repeatPassword}"/>
          </label>
        </p>
        <p>
          <label>E-Mail Address
          <input type="text" name="r_emailAddress" id="r_emailAddress" value="${r_emailAddress}"/>
          </label>
        </p>
        <p>
          <input type="submit" name="Register" id="submit" value="Submit" />
        </p>
    </form>
    <p>&nbsp;</p>
    <p align="center"><h4>${registerErrMsg}</h4></p>
    <form action="<c:url value='/Logout'/>">
    <p align="center"><h4><font color="black">Click here to return to the login screen: <input type="submit" value="Return"></font></h4></p>
    </form>
	<!-- end #mainContent --></div>
<!-- end #container --></div>
</body>
</html>
