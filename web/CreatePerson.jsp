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
<form class="blocks" action="${pageContext.request.contextPath}/servlet?method=formCorrectPerson" method="post"
      enctype="multipart/form-data">
<div class="wrapper">

    <input type="hidden" name="id" value="${person.id}"/>
    <input type="file" name="photoFile" id="photoFileID" style="display: none" accept="image/*,image/jpeg"
           onchange="onFileSelected(event)">
			<span style="position:static;">
				<a href="#selectImageForm" id="#selectImage">
                    <c:set var="photoPath" value="image/" scope="page"/>
                    <c:set var="photoPath" value="${photoPath}${person.photoPath}" scope="page"/>

                        <img id="photoPerson" src="http://localhost/images/1.jpg"/>
                    <%--image/silhouette.jpg--%>
                </a>
			<p>
                <label class="photo">Фамилия</label>

                <input type="text" class="text" name="surname" id="surname" value="${requestScope.person.surname}" placeholder="Введите фамилию"/>
            </p>
			<p>
                <label class="photo">Имя</label>
                <input type="text" class="text" name="name" id="name" value="${requestScope.person.name}" placeholder="Введите имя"/>
            </p>
			<p>
                <label class="photo"> Отчество</label>
                <input type="text" class="text" name="patronymic" id="patronymic" value="${requestScope.person.patronymic}" placeholder="Введите отчество"/>
            </p>

			<p>
                <label class="photo">Дата рождения</label>
                <input type="text" class="text" name="dateOfBirth" id="dateOfBirth" value="${requestScope.person.dateOfBirth}" placeholder="Введите дату рождения(дд-мм-гггг)"/>
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
        <input type="text" class="text" name="nationality" id="nationality" value="${requestScope.person.nationality}" placeholder="Введите национальность"/>
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
        <input type="text" class="text" name="webSite" id="webSite" value="${requestScope.person.webSite}" placeholder="Введите web site"/>
    </p>

    <p>
        <label>E-mail:</label>
        <input type="text" class="text" name="email" id="email" value="${requestScope.person.email}" placeholder="Введите email"/>
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
        <input type="text" class="text" name="city" id="city" value="${requestScope.person.city}" placeholder="Введите город"/>
    </p>

    <p>
        <label class="live">Улица</label>
        <input type="text" class="text" name="street" id="street" value="${requestScope.person.street}" placeholder="Введите улицу"/>
    </p>

    <p>
        <label class="live">Дом</label>
        <input type="text" class="text" name="home" id="home" value="${requestScope.person.home}" placeholder="Введите номер дома"/>
    </p>

    <p>
        <label class="live">Квартира</label>
        <input type="text" class="text" name="flat" id="flat" value="${requestScope.person.flat}" placeholder="Введите номер квартиры"/>
    </p>

    <p>
        <label class="live">Индекс</label>
        <input type="text" class="text" name="index" id="index" value="${requestScope.person.index}" placeholder="Введите индекс"/>
    </p>
</div>
<br/>
<br/>

<h2 align="center" class="textFont">Список контактных телефонов </h2>

<div align="right">
    <button class="standard" type="button"><a href="#addContactForm" style="color:white">Добавить контакт </a>
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
            <input type="hidden" name="countryCodeID" value="${phone.countryCode}">
            <input type="hidden" name="operatorCodeID" value="${phone.operatorCode}">
            <input type="hidden" name="phoneNumberID" value="${phone.phoneNumber}">
            <input type="hidden" name="phoneTypeID" value="${phone.phoneType}">
            <input type="hidden" name="commentPhoneID" value="${phone.comment}">
            <td>

                <input type="checkbox" name="checkPhone" value="${phone.id}"/>

            </td>
            <td>+${phone.countryCode}${phone.operatorCode}${phone.phoneNumber}</td>
            <td>${phone.phoneType}</td>
            <td>${phone.comment}</td>
            <td>
                <button class="standard" type="button"><span><a href="#correctContactForm"
                                                                onclick="openCorrectPhone(this)" style="color:white">Редактировать
                    контакт </a></span></button>
            </td>
        </tr>
    </c:forEach>
    <!-- Table Row -->
</table>
<br/>

<br/>
<br/>

<h2 align="center" class="textFont">Список присоединений </h2>

<div align="right">
    <button class="standard" type="button"><a href="#addAccessionForm" style="color:white">Добавить присоединение </a>
    </button>
    <button class="attention" type="button" id="deleteFile"> Удалить</button>
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
            <input type="hidden" name="fileHash" value="${file.fileHash}">
            <input type="hidden" name="fileName" value="${file.fileName}">
            <input type="hidden" name="fileDate" value="${file.fileDate}">
            <input type="hidden" name="commentFile" value="${file.comment}">
            <td>

                <input type="checkbox" name="checkFile" value=""/>

            </td>
            <td>${file.fileName}</td>
            <td>${file.fileDate}</td>
            <td>${file.comment}</td>
            <td>
                <button class="standard" type="button"><span><a href="#correctAccessionForm"
                                                                onclick="openCorrectFile(this)" style="color:white">Редактировать
                    файл </a></span></button>
            </td>
        </tr>
    </c:forEach>
</table>


<p>
    <label>&nbsp;</label>
    <button type="submit" id="save" class="standard"> Сохранить</button>
</p>
</form>
<!-- Форма для модального окна добавить контакт -->
<a href="#x" class="overlay" id="addContactForm"></a>

<div class="popup">
    <div>
        <label>Код страны</label>
        <input type="text" id="countryCodeIDAdd" value="" placeholder="Введите код страны"/>
    </div>
    <div>
        <label>Код оператора</label>
        <input type="text" id="operatorCodeIDAdd" value="" placeholder="Введите код оператора"/>
    </div>
    <div>
        <label>Телефонный номер</label>
        <input type="text" id="phoneNumberIDAdd" value="" placeholder="Введите номер телефона"/>
    </div>
    <div>
        <label>Тип номера</label>
        <input type="text" id="phoneTypeIDAdd" value="" placeholder="Введите тип номера"/>
    </div>
    <div>
        <label>Комментарий</label>
        <input type="text" id="commentPhoneIDAdd" value="" placeholder="Добавьте комментарий"/>
    </div>

    <button class="btn" id="saveAddPhone"><a href="#close">Сохранить </a></button>
    <a class="close" href="#close"></a>
</div>


<!-- Форма №2 для модального окна редактировать -->
<a href="#x" class="overlay" id="correctContactForm"></a>

<div class="popup">
    <div>
        <label>Код страны</label>
        <input type="text" id="countryCodeIDCorrect" name="countryCode" value="" placeholder="Введите код страны"/>
    </div>
    <div>
        <label>Код оператора</label>
        <input type="text" id="operatorCodeIDCorrect" name="operatorCode" value="" placeholder="Введите код оператора"/>
    </div>
    <div>
        <label>Телефонный номер</label>
        <input type="text" id="phoneNumberIDCorrect" name="phoneNumber" value="" placeholder="Введите номер телефона"/>
    </div>
    <div>
        <label>Тип номера</label>
        <input type="text" id="phoneTypeIDCorrect" name="phoneType" value="" placeholder="Введите тип номера"/>
    </div>
    <div>
        <label>Комментарий</label>
        <input type="text" id="commentPhoneIDCorrect" name="comment" value="" placeholder="Добавьте комментарий"/>
    </div>
    <input type="hidden" id="rowCountPhoneCorrect" value=""/>
    <button class="btn" id="saveCorrectPhone"><a href="#close">Сохранить </a></button>

    <%--<button class="btn"  onclick="saveCorrectPhone()"> Сохранить </button>--%>
    <a class="close" href="#close"></a>
</div>


<!-- Форма №1 для модального окна добавить -->
<a href="#x" class="overlay" id="addAccessionForm"></a>

<div class="popup">
    <div>
        <label>Имя файла</label>
        <button type="button" id="addFileButton"> Добавить файл</button>
    </div>
    <div>
        <label>Комментарий</label>
        <input type="text" id="commentFileIDAdd" value="" placeholder="Добавьте комментарий"/>
    </div>
    <button class="btn" id="addFile"><a href="#close">Сохранить </a></button>
    <a class="close" href="#close"></a>
</div>
<!-- Форма №2 для модального окна -->
<a href="#x" class="overlay" id="correctAccessionForm"></a>

<div class="popup">
    <div>
        <label>Имя файла</label>
        <input type="text" id="fileNameIDCorrect" value=""/>
    </div>
    <div>
        <label>Комментарий</label>
        <input type="text" id="commentFileIDCorrect" value=""/>
    </div>
    <input type="hidden" id="rowCountFileCorrect" value="" placeholder="Добавьте комментарий"/>
    <button class="btn" id="saveCorrectFileButton"><a href="#close">Сохранить </a></button>
    <a class="close" href="#close"></a>
</div>


<!-- Table Row -->

<!-- Форма №1 для модального окна фотки-->
<a href="#x" class="overlay" id="selectImageForm"></a>

<div class="popup">
    <p>
        <button type="button" id="selectPhotoButton"> Выбрать фото</button>
    </p>
    <button class="btn" id="loadPhotoButton"><a href="#close">Сохранить </a></button>
    <a class="close" href="#close"></a>
</div>

<script type="text/javascript" src="js/js.js"></script>
</body>
</html>