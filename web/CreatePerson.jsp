<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<jsp:include page="menu.jsp"/>
<html>
<head>
    <title>
        Create Contact
    </title>
</head>
<body>
<form class="blocks" action="${pageContext.request.contextPath}/servlet?method=formCorrectPerson" method="post">
    <div class="wrapper">

        <input type="hidden" name="id" value="${requestScope.person.id}">
			<span style="position:static;">
				<a href="#selectImageForm" id="#selectImage">
                    <img src="image/silhouette.jpg"/>
                </a>
			<p>
                <label class="foto">Фамилия</label>
                <input type="text" class="text" name="surname" value="${requestScope.person.surname}"/>
            </p>
			<p>
                <label class="foto">Имя</label>
                <input type="text" class="text" name="name" value="${requestScope.person.name}"/>
            </p>
			<p>
                <label class="foto"> Отчество</label>
                <input type="text" class="text" name="patronymic" value="${requestScope.person.patronymic}"/>
            </p>

			<p>
                <label class="foto">Дата рождения</label>
                <input type="text" class="text" name="dateOfBirth" value="${requestScope.person.dateOfBirth}"/>
            </p>
			</span>

        <p>
            <label>Пол</label>
            <font color=#666>
                <c:choose>
                    <c:when test="${requestScope.person.sex == 'f'}">
                        <input type="radio" name="sex" value="f" checked/> Женский
                        <input type="radio" name="sex" value="m" class="align"/> Мужской
                    </c:when>
                    <c:when test="${requestScope.person.sex == 'm'}">
                        <input type="radio" name="sex" value="f"/> Женский
                        <input type="radio" name="sex" class="align" value="m" checked/> Мужской
                    </c:when>
                    <c:when test="${requestScope.person == NULL}">
                        <input type="radio" name="sex" value="f" checked/> Женский
                        <input type="radio" name="sex" class="align" value="m"/> Мужской
                    </c:when>
                </c:choose>

            </font>
        </p>

        <p>
            <label>Гражданство</label>
            <input type="text" class="text" name="nationality" value="${requestScope.person.nationality}"/>
        </p>

        <p>
            <label>Семейное положение</label>
            <%--<input type="text" class="text" fileName="maritalStatus" value="${requestScope.person.maritalStatus}"/>--%>
            <select name="maritalStatus">
                <c:forEach var="maritalStatus" items="${maritalStatuses}">
                    <c:choose>
                        <c:when test="${maritalStatus.maritalStatus == person.maritalStatus}">
                            <option value="${maritalStatus.id}" selected>${maritalStatus.maritalStatus}</option>
                        </c:when>
                        <c:otherwise>
                            <option value=${maritalStatus.id}>${maritalStatus.maritalStatus}</option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>
        </p>

        <p>
            <label>Web site</label>
            <input type="text" class="text" name="webSite" value="${requestScope.person.webSite}"/>
        </p>

        <p>
            <label>E-mail:</label>
            <input type="text" class="text" name="email" value="${requestScope.person.email}"/>
        </p>

        <p>
            <label>Место работы</label>
            <%--<input type="text" class="text" fileName="company" value="${requestScope.person.company}"/>--%>
            <select name="company">
                <c:forEach var="company" items="${companies}">
                    <c:choose>
                        <c:when test="${company.company == person.company}">
                            <option value="${company.id}" selected>${company.company}</option>
                        </c:when>
                        <c:otherwise>
                            <option value=${company.id}>${company.company}</option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>
        </p>

        <p>

        <h3 class="textFont">Адрес: </h3>
        </p>

        <p>
            <label class="live">Страна</label>
            <%--<input type="text" class="text" fileName="country" value="${requestScope.person.country}"/>--%>
            <select name="country">
                <c:forEach var="country" items="${countries}">
                    <c:choose>
                        <c:when test="${country.country == person.country}">
                            <option value="${country.id}" selected>${country.country}</option>
                        </c:when>
                        <c:otherwise>
                            <option value=${country.id}>${country.country}</option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>
        </p>

        <p>
            <label class="live">Город</label>
            <input type="text" class="text" name="city" value="${requestScope.person.city}"/>
        </p>

        <p>
            <label class="live">Улица</label>
            <input type="text" class="text" name="street" value="${requestScope.person.street}"/>
        </p>

        <p>
            <label class="live">Дом</label>
            <input type="text" class="text" name="home" value="${requestScope.person.home}"/>
        </p>

        <p>
            <label class="live">Квартира</label>
            <input type="text" class="text" name="flat" value="${requestScope.person.flat}"/>
        </p>

        <p>
            <label class="live">Индекс</label>
            <input type="text" class="text" name="index" value="${requestScope.person.index}"/>
        </p>
    </div>
    <br/>
    <br/>

    <h2 align="center" class="textFont">Список контактных телефонов </h2>

    <div align="right">
        <button class="standard" type="button"><a href="#addContactForm">Добавить контакт </a>
        </button>
        <button class="attention" type="button" onclick="deletePhone()"> Удалить</button>
    </div>


    <table class="simple-little-table" cellspacing='0' id="phoneTable">
        <tr>
            <th></th>
            <th>Телефонный номер</th>
            <th>Тип номера</th>
            <th>Комментарий</th>
            <th></th>
        </tr>
        <!-- Table Header -->
        <c:forEach var="phone" items="${person.phone}">
            <tr>
                <input type="hidden" name="idID" value="${phone.id}"/>
                <input type="hidden" name="countryCodeID" value="${phone.countryCode}">
                <input type="hidden" name="operatorCodeID" value="${phone.operatorCode}">
                <input type="hidden" name="phoneNumberID" value="${phone.phoneNumber}">
                <input type="hidden" name="phoneTypeID" value="${phone.phoneType}">
                <input type="hidden" name="commentPhoneID" value="${phone.comment}">
                <input type="hidden" name="idPersonID" value="${phone.idPerson}">
                <td>

                    <input type="checkbox" name="checkPhone" value="${phone.id}"/>

                </td>
                <td>+${phone.countryCode}${phone.operatorCode}${phone.phoneNumber}</td>
                <td>${phone.phoneType}</td>
                <td>${phone.comment}</td>
                <td>
                    <button class="standard" type="button"><span><a href="#correctContactForm"
                                                                    onclick="openCorrectPhone(this)">Редактировать
                        контакт </a></span></button>
                </td>
            </tr>
        </c:forEach>
        <!-- Table Row -->
    </table>
    <br/>

    <p>
        <label>&nbsp;</label>
        <button type="submit" class="standard"> Сохранить</button>
    </p>
</form>
<!-- Форма для модального окна добавить контакт -->
<a href="#x" class="overlay" id="addContactForm"></a>

<div class="popup">
    <div>
        <label>Код страны</label>
        <input type="text" id="countryCodeIDAdd" value=""/>
    </div>
    <div>
        <label>Код оператора</label>
        <input type="text" id="operatorCodeIDAdd" value=""/>
    </div>
    <div>
        <label>Телефонный номер</label>
        <input type="text" id="phoneNumberIDAdd" value=""/>
    </div>
    <div>
        <label>Тип номера</label>
        <input type="text" id="phoneTypeIDAdd" value=""/>
    </div>
    <div>
        <label>Комментарий</label>
        <input type="text" id="commentPhoneIDAdd" value=""/>
    </div>
    <input type="hidden" id="idPersonIDAdd" value="${person.id}"/>
    <input type="hidden" id="idIDAdd" value=""/>

    <button class="btn" onclick="saveAddPhone()"><a href="#close">Сохранить </a></button>
    <a class="close" href="#close"></a>
</div>


<!-- Форма №2 для модального окна редактировать -->
<a href="#x" class="overlay" id="correctContactForm"></a>

<div class="popup">
    <div>
        <label>Код страны</label>
        <input type="text" id="countryCodeIDCorrect" name="countryCode" value=""/>
    </div>
    <div>
        <label>Код оператора</label>
        <input type="text" id="operatorCodeIDCorrect" name="operatorCode" value=""/>
    </div>
    <div>
        <label>Телефонный номер</label>
        <input type="text" id="phoneNumberIDCorrect" name="phoneNumber" value=""/>
    </div>
    <div>
        <label>Тип номера</label>
        <input type="text" id="phoneTypeIDCorrect" name="phoneType" value=""/>
    </div>
    <div>
        <label>Комментарий</label>
        <input type="text" id="commentPhoneIDCorrect" name="comment" value=""/>
    </div>
    <input type="hidden" id="idIDCorrect" name="id" value=""/>
    <input type="hidden" id="idPersonIDCorrect" name="idPerson" value=""/>
    <input type="hidden" id="rowCountPhoneCorrect" value=""/>
    <button class="btn" onclick="saveCorrectPhone()"><a href="#close">Сохранить </a></button>

    <%--<button class="btn"  onclick="saveCorrectPhone()"> Сохранить </button>--%>
    <a class="close" href="#close"></a>
</div>


<br/>
<br/>

<h2 align="center" class="textFont">Список присоединений </h2>

<form>

    <div align="right">
        <button class="standard" type="button"><a href="#addAccessionForm" id="addAccession">Добавить присоединение </a>
        </button>
        <button class="standard" type="button"><a href="#correctAccessionForm" id="correctAccession">Редактировать
            присоединение </a></button>
        <button class="attention" type="button"><a href="#"> Удалить</a></button>
    </div>
    <!-- Форма №1 для модального окна добавить -->
    <a href="#x" class="overlay" id="addAccessionForm"></a>

    <div class="popup">
        <div>
            <label for="fileName">Имя файла</label>
            <input type="file" id="fileNameIDAdd" value=""/>
        </div>
        <div>
            <label for="comment">Комментарий</label>
            <input type="text" id="commentFileIDAdd" value=""/>
        </div>
        <button class="btn" onclick="addFile()"><a href="#close">Сохранить </a></button>
        <a class="close" href="#close"></a>
    </div>
    <!-- Форма №2 для модального окна -->
    <a href="#x" class="overlay" id="correctAccessionForm"></a>

    <div class="popup">
        <div>
            <label for="fileName">Имя файла</label>
            <input type="text" id="fileName" value=""/>
        </div>
        <div>
            <label for="comment">Комментарий</label>
            <input type="text" id="comment" value=""/>
        </div>
        <input type="submit" class="btn" value="Сохранить">
        <input type="button" class="btn" value="Отменить">
        <a class="close" href="#close"></a>
    </div>


    <table class="simple-little-table" cellspacing='0' id="fileTable">
        <tr>
            <th></th>
            <th>Имя файла присоединения</th>
            <th>Дата загрузки</th>
            <th>Комментарий</th>
            <th></th>
        </tr>
        <!-- Table Header -->

        <c:forEach var="file" items="${person.file}">
            <tr>
                <input type="hidden" name="fileName" value="${file.fileName}">
                <input type="hidden" name="fileData" value="${file.data}">
                <input type="hidden" name="commentFile" value="${file.comment}">
                <td>

                    <input type="checkbox" name="checkFile" value=""/>

                </td>
                <td>${file.fileName}</td>
                <td>${file.data}</td>
                <td>${file.comment}</td>
                <td>
                    <button class="standard" type="button"><span><a href="#correctAccessionForm"
                                                                    onclick="openCorrectFile(this)">Редактировать
                        контакт </a></span></button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- Table Row -->

    <!-- Форма №1 для модального окна фотки-->
    <a href="#x" class="overlay" id="selectImageForm"></a>

    <div class="popup">
        <form enctype="multipart/form-data" method="post">
            <p>
                <input type="file" name="image" multiple accept="image/*,image/jpeg">
            </p>
            <input type="submit" class="btn" value="Загрузить">
        </form>
        <a class="close" href="#close"></a>
    </div>


</form>
<script type="text/javascript" src="js/js.js"></script>
</body>
</html>