<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form method="post" action="${pageContext.request.contextPath}/servlet">
    <div align="right">
        <button type="submit" class="standard" name="method" value="sendEmail">Послать e-mail</button>
        <button type="submit" class="attention" name="method" value="deletePerson"> Удалить</button>
        <button type="reset" class="attention" name="method" value="reset"> Отмена </button>
    </div>


    <table class="simple-little-table" cellspacing='0'>
        <tr>
            <th>

            </th>
            <th>Полное имя</th>
            <th>Дата рождения</th>
            <th>Адрес</th>
            <th>Место работы</th>
        </tr>
        <!-- Table Header -->
        <c:forEach var="person" items="${persons}">

            <tr>
                <th>
                    <div class="squaredThree">
                        <input type="checkbox"  name="checkPerson" value=${person.id}>
                        <%--<label for="squaredThree"></label>--%>
                    </div>
                </th>
                <td>
                    <a href="/servlet?method=correctPerson&id=${person.id}">
                            ${person.surname} ${person.name} ${person.patronymic}</a></td>
                    <%--${requestScope.persons.size}--%>
                <td>${person.dateOfBirth}</td>
                <td>${person.country} г.${person.city} ул.${person.street} д.${person.home} кв.${person.flat}</td>
                <td>${person.company}</td>
            </tr>
            <!-- Table Row -->
        </c:forEach>
        <%--<tr>--%>
        <%--<th>--%>
        <%--<div class="squaredThree">--%>
        <%--<input type="checkbox" value="None" id="squaredThree" fileName="check" />--%>
        <%--<label for="squaredThree"></label>--%>
        <%--</div>--%>
        <%--</th>--%>
        <%--<td> <a href="/servlet?method=correctPerson">Шиманкевич Сергей Викторович </a> </td>--%>
        <%--<td>23.03.1985</td>--%>
        <%--<td>г.Минск ул.Жилуновича д.73 кв.44</td>--%>
        <%--<td>Exadel</td>--%>
        <%--</tr><!-- Table Row -->--%>


    </table>
</form>

</body>
</html>
