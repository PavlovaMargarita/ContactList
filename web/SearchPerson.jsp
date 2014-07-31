<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Margarita
  Date: 21.07.2014
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title>
        Search Contact
    </title>
</head>
<body>
<div class="wrapper">
    <form id="form" class="blocks" action="${pageContext.request.contextPath}/servlet?method=formSearchPerson" method="post">
        <p>
            <label>Фамилия</label>
            <input type="text" class="text" fileName="surname" />
        </p>
        <p>
            <label>Имя</label>
            <input type="text" class="text" fileName="fileName" />
        </p>
        <p>
            <label>Отчество</label>
            <input type="text" class="text" fileName="patronymic" />
        </p>

        <p>
            <label>Возраст</label>
            <input type="text" class="text" fileName="age" />
            <input type="checkbox" fileName="ageRange" value="moreThan"> и старше
            <input type="checkbox" fileName="ageRange" value="lessThan"> и младше
        </p>
        <p>
            <label>Пол</label>
            <font color=#666>
                <input type="radio" fileName="sex" value="f" checked/> Женский
                <input type="radio" fileName="sex" value="m"/> Мужской
            </font>
        </p>
        <p>
            <label>Гражданство</label>
            <input type="text" class="text" fileName="nationality" />
        </p>
        <p>
            <label>Семейное положение</label>
            <select fileName="maritalStatus">
                <option value=""></option>
                <c:forEach var="maritalStatus" items="${maritalStatuses}">
                    <option value=${maritalStatus.id}>${maritalStatus.maritalStatus}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Страна</label>
            <%--<input type="text" class="text" fileName="country" />--%>
            <select fileName="country">
                <option value=""></option>
                <c:forEach var="country" items="${countries}">
                    <option value=${country.id}>${country.country}</option>
                </c:forEach>
            </select>

        </p>


        <p>
            <label>Город</label>
            <input type="text" class="text" fileName="city" />
        </p>
        <p>
            <label >Улица</label>
            <input type="text" class="text" fileName="street" />
        </p>
        <p>
            <label >Дом</label>
            <input type="text" class="text" fileName="home" />
        </p>
        <p>
            <label >Квартира</label>
            <input type="text" class="text" fileName="flat" />
        </p>

        <p>
            <label >Индекс</label>
            <input type="text" class="text" fileName="index" />
        </p>

        <p>
            <label>&nbsp;</label>
            <button type="submit" class="standard"> Искать </button>
        </p>
    </form>
</div>

</body>
</html>
