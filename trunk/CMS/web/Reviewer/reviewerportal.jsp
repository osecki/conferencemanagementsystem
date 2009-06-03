<%-- 
    Document   : reviewerportal
    Group      : 3, Java Team Hunger Force
    Class      : CS575, Spring 2009
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMS - Reviewer Portal</title>
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
    <h2>Reviewer Portal</h2>
    <h3><a href="<c:url value='/Logout'/>"><font color="red">Logout</font></a></h3>

    <table width="100%" border="0">
      <tr>
        <td width="50%" valign="top">
        <h3>View Assigned Papers</h3>
        <form id="assign" name="assign" method="post" action="">
        <label>Paper :
            <select name="papers">
            <option value="paper1">Paper 1</option>
            <option value="paper2">Paper 2</option>
            </select>
            </label>
            <br />
	        <p>
	          <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
          </form>
        </td>
        <td width="50%" valign="top">
        <h3>Submit Feedback For Paper</h3>
        <form id="assign" name="assign" method="post" action="">
        <p>Content: <br />
          <input type="radio" name="cRanking" id="veryBad" value="1" />
          1
          <input type="radio" name="cRanking" id="bad" value="2" />
          2
          <input type="radio" name="rRanking" id="okay" value="3" />
          3
          <input type="radio" name="cRanking" id="good" value="4" />
          4
          <input type="radio" name="cRanking" id="veryGood" value="5" />
          5</p>
        <p>Innovation: <br />
            <input type="radio" name="iRanking" id="veryBad" value="1" />
          1
          <input type="radio" name="iRanking" id="bad" value="2" />
          2
          <input type="radio" name="iRanking" id="okay" value="3" />
          3
          <input type="radio" name="iRanking" id="good" value="4" />
          4
          <input type="radio" name="iRanking" id="veryGood" value="5" />
          5</p>
        <p>Quality: <br />
            <input type="radio" name="qRanking" id="veryBad" value="1" />
          1
          <input type="radio" name="qRranking" id="bad" value="2" />
          2
          <input type="radio" name="qRanking" id="okay" value="3" />
          3
          <input type="radio" name="qRanking" id="good" value="4" />
          4
          <input type="radio" name="qRanking" id="veryGood" value="5" />
          5</p>
          <p>Depth: <br />
            <input type="radio" name="dRanking" id="veryBad" value="1" />
          1
          <input type="radio" name="dRanking" id="bad" value="2" />
          2
          <input type="radio" name="dRanking" id="okay" value="3" />
          3
          <input type="radio" name="dRanking" id="good" value="4" />
          4
          <input type="radio" name="dRanking" id="veryGood" value="5" />
          5</p>
        <label>Feedback :
        <textarea name="giveFeedback" id="giveFeedback" cols="45" rows="5"></textarea>
        <br />
        Paper :
            <select name="papers">
            <option value="paper1">Paper 1</option>
            <option value="paper2">Paper 2</option>
            </select>
        </label>
            <br />
	        <p>
	          <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
          </form>
        </p></td>
      </tr>
      <tr>
        <td valign="top">
        <h3>View Paper Feedback        </h3>
        <form id="assign" name="assign" method="post" action="">
        <label>Paper :
            <select name="papers">
            <option value="paper1">Paper 1</option>
            <option value="paper2">Paper 2</option>
            </select>
            </label>
            <br />
	        <p>
	          <input type="submit" name="submit" id="submit" value="Submit" />
	        </p>
          </form>
        </p>
        </td>
        <td valign="top">
        <h3>Edit Paper Feedback</h3>
        <form id="assign" name="assign" method="post" action="">
        <label>Feedback :
        <textarea name="giveFeedback" id="giveFeedback" cols="45" rows="5"></textarea>
        <br />
        Paper :
            <select name="papers">
            <option value="paper1">Paper 1</option>
            <option value="paper2">Paper 2</option>
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

    <p>&nbsp;</p>
    <p align="center"><h4>${errMsg}</h4></p>
	<!-- end #mainContent --></div>
<!-- end #container --></div>
</body>
</html>
