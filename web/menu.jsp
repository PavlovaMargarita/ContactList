<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Margarita
  Date: 21.07.2014
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="property"/>
<div id="menu">
    <ul>
        <li><a href="/servlet?method=showAllPersons&goToPage=1"><span><fmt:message key="contactList" /></span></a></li>
        <li><a href="/servlet?method=searchPerson"><span><fmt:message key="search" /></span></a></li>
        <li><a href="/servlet?method=createPerson"><span><fmt:message key="addContact" /></span></a></li>
    </ul>
</div>
</body>
</html>
