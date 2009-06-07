<%-- 
    Document   : index
    Created on : Jun 4, 2009, 11:55:53 PM
    Author     : Piyush
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h3><a href="<c:url value='/Logout'/>"><font color="red">Logout</font></a></h3>
        <h4>Here is the text from the pdf:</h4>
        <p>${pdfText}</p>
    </body>
</html>
