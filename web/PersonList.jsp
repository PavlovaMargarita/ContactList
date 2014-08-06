<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Margarita
  Date: 21.07.2014
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title>
        Contact List
    </title>
</head>
<body>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="property"/>
<form method="post" action="${pageContext.request.contextPath}/servlet">
    <div align="right">
        <button class="standard" type="button"><a href="#sendEmailForm" style="color:white" id="sendEmailPopup"><fmt:message key="sendEmail" /></a> </button>
        <button type="submit" class="attention" name="method" value="deletePerson"> <fmt:message key="deletePerson" /></button>
        <button type="submit" style="display: none;" name="method" value="sendEmail" id="send"></button>
        <button type="reset" class="attention" name="method" value="reset"> <fmt:message key="cancel" /> </button>
    </div>


    <table class="simple-little-table" cellspacing='0'>
        <tr>
            <th>

            </th>
            <th><fmt:message key="fullName" /></th>
            <th><fmt:message key="dateOfBirth" /></th>
            <th><fmt:message key="address" /></th>
            <th><fmt:message key="company" /></th>
        </tr>
        <!-- Table Header -->
        <c:forEach var="person" items="${persons}">

            <tr>
                <input type="hidden" name="email" value="${person.email}"/>
                <input type="hidden" name="id" value="${person.id}"/>
                <th>
                    <div class="squaredThree">
                        <input type="checkbox"  name="checkPerson" value=${person.id}>
                        <%--<label for="squaredThree"></label>--%>
                    </div>
                </th>
                <td>
                    <a href="/servlet?method=correctPerson&id=${person.id}">
                            ${person.surname} ${person.name} ${person.patronymic}</a></td>
                <td>${person.dateOfBirth}</td>
                <td>${person.country} г.${person.city} ул.${person.street} д.${person.home} кв.${person.flat}</td>
                <td>${person.company}</td>
            </tr>
            <!-- Table Row -->
        </c:forEach>

    </table>
    <a href="#x" class="overlay" id="sendEmailForm"></a>

    <div class="popup">
        <input id="personsIDForEmail" name="personsIDForEmail" type="hidden"/>
            <div>
                <label style="width: 100px;"><fmt:message key="whom" /></label>
                <input type="text" id="emails" name="emails" value="" style="width: 300px;"/>
            </div>
            <div>
                <label style="width: 100px;"><fmt:message key="subject" /></label>
                <input type="text" id="subject" name="subject" value="" placeholder="<fmt:message key="enterSubject"/>" style="width: 300px;"/>
            </div>
            <div>
                <label style="width: 100px;"><fmt:message key="template" /></label>
                <select name="template" id="template" style="width: 300px;" onchange="changeTemplate(this)">
                    <option value="none"></option>
                    <c:forEach var="templateVar" items="${templates}">
                        <option value="${templateVar.id}/${templateVar.template}">${templateVar.templateName}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label style="width: 100px;"><fmt:message key="message"/></label>
                <textarea class="textarea" id="message" name="message" value="" placeholder="<fmt:message key="enterMessage"/>"></textarea>
            </div>
            <button class="btn" id="popupSendEmailButton"><a href="#close">Отправить email </a></button>

            <%--<button class="btn"  onclick="saveCorrectPhone()"> Сохранить </button>--%>

            <a class="close" href="#close" id="closeID"></a>

    </div>
</form>
<div class="tableForNavigation" align="right">
    <a href="/servlet?method=showAllPersons&goToPage=${previousPage}">${previousPage}</a>
    <a href="/servlet?method=showAllPersons&goToPage=${currentPage}" style="font-size: 160%">${currentPage}</a>
    <a href="/servlet?method=showAllPersons&goToPage=${nextPage}">${nextPage}</a>
</div>


<script type="text/javascript" src="js/jsForPersonList.js"></script>

</body>
</html>
