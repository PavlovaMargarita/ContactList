<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
            <input type="text" class="text" name="surname" placeholder="Введите фамилию"/>
        </p>
        <p>
            <label>Имя</label>
            <input type="text" class="text" name="name"placeholder="Введите имя" />
        </p>
        <p>
            <label>Отчество</label>
            <input type="text" class="text" name="patronymic"  placeholder="Введите отчество"/>
        </p>

        <p>
            <label>Возраст</label>
            <input type="text" class="text" name="age" id="age" placeholder="Введите полное количество лет"/>
            <input type="checkbox" name="ageRange" value="moreThan"> и старше
            <input type="checkbox" name="ageRange" value="lessThan"> и младше
        </p>
        <p>
            <label>Пол</label>
            <font color=#666>
                <input type="radio" name="sex" value="f" checked/> Женский
                <input type="radio" name="sex" value="m"/> Мужской
            </font>
        </p>
        <p>
            <label>Гражданство</label>
            <input type="text" class="text" name="nationality"  placeholder="Введите гражданство"/>
        </p>
        <p>
            <label>Семейное положение</label>
            <select name="maritalStatus">
                <option value=""></option>
                <c:forEach var="maritalStatus" items="${maritalStatuses}">
                    <option value=${maritalStatus.id}>${maritalStatus.maritalStatus}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Страна</label>
            <%--<input type="text" class="text" fileName="country" />--%>
            <select name="country">
                <option value=""></option>
                <c:forEach var="country" items="${countries}">
                    <option value=${country.id}>${country.country}</option>
                </c:forEach>
            </select>

        </p>


        <p>
            <label>Город</label>
            <input type="text" class="text" name="city" placeholder="Введите город"/>
        </p>
        <p>
            <label >Улица</label>
            <input type="text" class="text" name="street" placeholder="Введите улицу" />
        </p>
        <p>
            <label >Дом</label>
            <input type="text" class="text" name="home" id="home" placeholder="Введите номер дома"/>
        </p>
        <p>
            <label >Квартира</label>
            <input type="text" class="text" name="flat" id="flat" placeholder="Введите номер квартиры"/>
        </p>

        <p>
            <label >Индекс</label>
            <input type="text" class="text" name="index" placeholder="Введите индекс"/>
        </p>

        <p>
            <label>&nbsp;</label>
            <button type="submit" class="standard"> Искать </button>
        </p>
    </form>
</div>

<script type="text/javascript" src="js/jsForSearchPerson.js"></script>
</body>
</html>
