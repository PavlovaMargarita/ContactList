<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:include page="menu.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>
        Search Contact
    </title>
</head>
<body>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="property"/>
<div class="wrapper">
    <form id="form" class="blocks" action="${pageContext.request.contextPath}/servlet?method=formSearchPerson" method="post">
        <p>
            <label><fmt:message key="surname"/></label>
            <input type="text" class="text" name="surname" placeholder="<fmt:message key="enterSurname"/>"/>
        </p>
        <p>
            <label><fmt:message key="name"/></label>
            <input type="text" class="text" name="name"placeholder="<fmt:message key="enterName"/>" />
        </p>
        <p>
            <label><fmt:message key="patronymic"/></label>
            <input type="text" class="text" name="patronymic"  placeholder="<fmt:message key="enterPatronymic"/>"/>
        </p>

        <p>
            <label><fmt:message key="age"/></label>
            <input type="text" class="text" name="age" id="age" placeholder="<fmt:message key="enterAge"/>"/>
            <input type="checkbox" name="ageRange" value="moreThan"> <fmt:message key="moreThan"/>
            <input type="checkbox" name="ageRange" value="lessThan"> <fmt:message key="lessThan"/>
        </p>
        <p>
            <label><fmt:message key="sex"/></label>
            <font color=#666>
                <input type="radio" name="sex" value="f" checked/> <fmt:message key="female"/>
                <input type="radio" name="sex" value="m"/> <fmt:message key="male"/>
            </font>
        </p>
        <p>
            <label><fmt:message key="nationality"/></label>
            <input type="text" class="text" name="nationality"  placeholder="<fmt:message key="enterNationality"/>"/>
        </p>
        <p>
            <label><fmt:message key="maritalStatus"/></label>
            <select name="maritalStatus">
                <option value=""></option>
                <c:forEach var="maritalStatus" items="${maritalStatuses}">
                    <option value=${maritalStatus.id}>${maritalStatus.maritalStatus}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label><fmt:message key="country"/></label>
            <select name="country">
                <option value=""></option>
                <c:forEach var="country" items="${countries}">
                    <option value=${country.id}>${country.country}</option>
                </c:forEach>
            </select>

        </p>


        <p>
            <label><fmt:message key="city"/></label>
            <input type="text" class="text" name="city" placeholder="<fmt:message key="enterCity"/>"/>
        </p>
        <p>
            <label ><fmt:message key="street"/></label>
            <input type="text" class="text" name="street" placeholder="<fmt:message key="enterStreet"/>" />
        </p>
        <p>
            <label ><fmt:message key="home"/></label>
            <input type="text" class="text" name="home" id="home" placeholder="<fmt:message key="enterHome"/>"/>
        </p>
        <p>
            <label ><fmt:message key="flat"/></label>
            <input type="text" class="text" name="flat" id="flat" placeholder="<fmt:message key="enterFlat"/>"/>
        </p>

        <p>
            <label ><fmt:message key="index"/></label>
            <input type="text" class="text" name="index" placeholder="<fmt:message key="enterIndex"/>"/>
        </p>

        <p>
            <label>&nbsp;</label>
            <button type="submit" class="standard"> <fmt:message key="search"/> </button>
        </p>
    </form>
</div>

<script type="text/javascript" src="js/jsForSearchPerson.js"></script>
</body>
</html>
