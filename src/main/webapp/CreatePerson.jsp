<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<jsp:include page="menu.jsp"/>
<html>
<head>

    <title>
        Create Contact
    </title>
</head>
<body>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="property"/>

<form class="blocks" action="${pageContext.request.contextPath}/servlet?method=formCorrectPerson" method="post"
      enctype="multipart/form-data">
<div class="wrapper">

    <input type="hidden" name="id" value="${person.id}"/>
    <input type="file" name="photoFile" id="photoFileID" style="display: none" accept="image/*,image/jpeg"
           onchange="onFileSelected(event)">
    <input type="hidden" name="photoPath" value="${person.photoPath}"/>
			<span style="position:static;">
				<a href="#selectImageForm" id="#selectImage">
                    <img id="photoPerson" src="${pageContext.request.contextPath}/images/${person.photoPath}"/>
                </a>
			<p>
                <label class="photo"><fmt:message key="surname"/> </label>
                <input type="text" class="text" name="surname" id="surname" value="${requestScope.person.surname}"
                       placeholder="<fmt:message key="enterSurname"/>"/>
            </p>
			<p>
                <label class="photo"><fmt:message key="name"/></label>
                <input type="text" class="text" name="name" id="name" value="${requestScope.person.name}"
                       placeholder="<fmt:message key="enterName"/>"/>
            </p>
			<p>
                <label class="photo"> <fmt:message key="patronymic"/></label>
                <input type="text" class="text" name="patronymic" id="patronymic"
                       value="${requestScope.person.patronymic}" placeholder="<fmt:message key="enterPatronymic"/>"/>
            </p>

			<p>
                <label class="photo"><fmt:message key="dateOfBirth"/></label>
                <input type="text" class="text" name="dateOfBirth" id="dateOfBirth"
                       value="${requestScope.person.dateOfBirth}" placeholder="<fmt:message key="enterDateOfBirth"/>"/>
            </p>
			</span>

    <p>
        <label><fmt:message key="sex"/></label>
        <font color=#666>
            <c:choose>
                <c:when test="${requestScope.person.sex == 'f'}">
                    <input type="radio" name="sex" value="f" checked/> <fmt:message key="female"/>
                    <input type="radio" name="sex" value="m" class="align"/> <fmt:message key="male"/>
                </c:when>
                <c:when test="${requestScope.person.sex == 'm'}">
                    <input type="radio" name="sex" value="f"/> <fmt:message key="female"/>
                    <input type="radio" name="sex" class="align" value="m" checked/> <fmt:message key="male"/>
                </c:when>
                <c:when test="${requestScope.person == NULL}">
                    <input type="radio" name="sex" value="f" checked/> <fmt:message key="female"/>
                    <input type="radio" name="sex" class="align" value="m"/> <fmt:message key="male"/>
                </c:when>
            </c:choose>

        </font>
    </p>

    <p>
        <label><fmt:message key="nationality"/></label>
        <input type="text" class="text" name="nationality" id="nationality" value="${requestScope.person.nationality}"
               placeholder="<fmt:message key="enterNationality"/>"/>
    </p>

    <p>
        <label><fmt:message key="maritalStatus"/></label>
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
        <label><fmt:message key="webSite"/></label>
        <input type="text" class="text" name="webSite" id="webSite" value="${requestScope.person.webSite}"
               placeholder="<fmt:message key="enterWebSite"/>"/>
    </p>

    <p>
        <label><fmt:message key="email"/></label>
        <input type="text" class="text" name="email" id="email" value="${requestScope.person.email}"
               placeholder="<fmt:message key="enterEmail"/>"/>
    </p>

    <p>
        <label><fmt:message key="company"/></label>
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

    <h3 class="textFont"><fmt:message key="address"/></h3>
    </p>

    <p>
        <label class="live"><fmt:message key="country"/></label>
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
        <label class="live"><fmt:message key="city"/></label>
        <input type="text" class="text" name="city" id="city" value="${requestScope.person.city}"
               placeholder="<fmt:message key="enterCity"/>"/>
    </p>

    <p>
        <label class="live"><fmt:message key="street"/></label>
        <input type="text" class="text" name="street" id="street" value="${requestScope.person.street}"
               placeholder="<fmt:message key="enterStreet"/>"/>
    </p>

    <p>
        <label class="live"><fmt:message key="home"/></label>
        <input type="text" class="text" name="home" id="home" value="${requestScope.person.home}"
               placeholder="<fmt:message key="enterHome"/>"/>
    </p>

    <p>
        <label class="live"><fmt:message key="flat"/></label>
        <input type="text" class="text" name="flat" id="flat" value="${requestScope.person.flat}"
               placeholder="<fmt:message key="enterFlat"/>"/>
    </p>

    <p>
        <label class="live"><fmt:message key="index"/></label>
        <input type="text" class="text" name="index" id="index" value="${requestScope.person.index}"
               placeholder="<fmt:message key="enterIndex"/>"/>
    </p>
</div>
<br/>
<br/>

<h2 align="center" class="textFont"><fmt:message key="phoneList"/></h2>

<div align="right">
    <button class="standard" type="button"><a href="#addContactForm" style="color:white"><fmt:message
            key="addPhone"/> </a>
    </button>
    <button class="attention" type="button" onclick="deletePhone()"><fmt:message key="deletePhone"/></button>
</div>


<table class="simple-little-table" cellspacing='0' id="phoneTable">
    <tr>
        <th></th>
        <th><fmt:message key="phoneNumber"/></th>
        <th><fmt:message key="phoneType"/></th>
        <th><fmt:message key="comment"/></th>
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

                <input type="checkbox" name="checkPhone" value=""/>

            </td>
            <td>+${phone.countryCode}${phone.operatorCode}${phone.phoneNumber}</td>
            <td>${phone.phoneType}</td>
            <td>${phone.comment}</td>
            <td>
                <button class="standard" type="button"><span><a href="#correctContactForm"
                                                                onclick="openCorrectPhone(this)"
                                                                style="color:white"><fmt:message
                        key="correctPhone"/></a></span></button>
            </td>
        </tr>
    </c:forEach>
    <!-- Table Row -->
</table>
<br/>

<br/>
<br/>

<h2 align="center" class="textFont"><fmt:message key="fileList"/></h2>

<div align="right">
    <button class="standard" type="button"><a href="#addAccessionForm" style="color:white"><fmt:message
            key="addFile"/> </a>
    </button>
    <button class="attention" type="button" id="deleteFile"><fmt:message key="deleteFile"/></button>
</div>

<table class="simple-little-table" cellspacing='0' id="fileTable">
    <tr>
        <th></th>
        <th><fmt:message key="fileName"/></th>
        <th><fmt:message key="loadDate"/></th>
        <th><fmt:message key="comment"/></th>
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
                                                                onclick="openCorrectFile(this)"
                                                                style="color:white"><fmt:message key="correctFile"/></a></span>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>


<p>
    <label>&nbsp;</label>
    <button type="submit" id="save" class="standard"><fmt:message key="save"/></button>
</p>
</form>
<!-- Форма для модального окна добавить контакт -->
<a href="#x" class="overlay" id="addContactForm"></a>

<div class="popup">
    <div>
        <label><fmt:message key="countryCode"/></label>
        <input type="text" id="countryCodeIDAdd" value="" placeholder="<fmt:message key="enterCountryCode"/>"/>
    </div>
    <div>
        <label><fmt:message key="operatorCode"/></label>
        <input type="text" id="operatorCodeIDAdd" value="" placeholder="<fmt:message key="enterOperatorCode"/>"/>
    </div>
    <div>
        <label><fmt:message key="phoneNumber"/></label>
        <input type="text" id="phoneNumberIDAdd" value="" placeholder="<fmt:message key="enterPhoneNumber"/>"/>
    </div>
    <div>
        <label><fmt:message key="phoneType"/></label>
        <%--<input type="text" id="phoneTypeIDAdd" value="" placeholder="<fmt:message key="enterPhoneType"/>"/>--%>
        <select id="phoneTypeSelectADD">
            <option value="Мобильный" selected>Мобильный</option>
            <option value="Домашний">Домашний</option>
        </select>

    </div>
    <div>
        <label><fmt:message key="comment"/></label>
        <input type="text" id="commentPhoneIDAdd" value="" placeholder="<fmt:message key="enterComment"/>"/>
    </div>

    <button class="btn" id="saveAddPhone"><a href="#close"><fmt:message key="save"/></a></button>
    <a class="close" href="#close"></a>
</div>


<!-- Форма №2 для модального окна редактировать -->
<a href="#x" class="overlay" id="correctContactForm"></a>

<div class="popup">
    <div>
        <label><fmt:message key="countryCode"/></label>
        <input type="text" id="countryCodeIDCorrect" name="countryCode" value=""
               placeholder="<fmt:message key="enterCountryCode"/>"/>
    </div>
    <div>
        <label><fmt:message key="operatorCode"/></label>
        <input type="text" id="operatorCodeIDCorrect" name="operatorCode" value=""
               placeholder="<fmt:message key="enterOperatorCode"/>"/>
    </div>
    <div>
        <label><fmt:message key="phoneNumber"/></label>
        <input type="text" id="phoneNumberIDCorrect" name="phoneNumber" value=""
               placeholder="<fmt:message key="enterPhoneNumber"/>"/>
    </div>
    <div>
        <label><fmt:message key="phoneType"/></label>
        <%--<input type="text" id="phoneTypeIDCorrect" name="phoneType" value=""--%>
               <%--placeholder="<fmt:message key="enterPhoneType"/>"/>--%>
        <select id="phoneTypeSelect">
            <option value="Мобильный">Мобильный</option>
            <option value="Домашний">Домашний</option>
        </select>
    </div>
    <div>
        <label><fmt:message key="comment"/></label>
        <input type="text" id="commentPhoneIDCorrect" name="comment" value=""
               placeholder="<fmt:message key="enterComment"/>"/>
    </div>
    <input type="hidden" id="rowCountPhoneCorrect" value=""/>
    <button class="btn" id="saveCorrectPhone"><a href="#close"><fmt:message key="save"/> </a></button>

    <%--<button class="btn"  onclick="saveCorrectPhone()"> Сохранить </button>--%>
    <a class="close" href="#close"></a>
</div>


<!-- Форма №1 для модального окна добавить -->
<a href="#x" class="overlay" id="addAccessionForm"></a>

<div class="popup">
    <div>
        <label><fmt:message key="fileName"/></label>
        <button type="button" id="addFileButton"><fmt:message key="addFile"/></button>
    </div>
    <div>
        <label><fmt:message key="comment"/></label>
        <input type="text" id="commentFileIDAdd" value="" placeholder="<fmt:message key="enterComment"/>"/>
    </div>
    <button class="btn" id="addFile"><a href="#close"><fmt:message key="save"/></a></button>
    <a class="close" href="#close"></a>
</div>
<!-- Форма №2 для модального окна -->
<a href="#x" class="overlay" id="correctAccessionForm"></a>

<div class="popup">
    <div>
        <label><fmt:message key="fileName"/></label>
        <input type="text" id="fileNameIDCorrect" value="" placeholder="<fmt:message key="enterFileName"/>"/>
    </div>
    <div>
        <label><fmt:message key="comment"/></label>
        <input type="text" id="commentFileIDCorrect" value="" placeholder="<fmt:message key="enterComment"/>"/>
    </div>
    <input type="hidden" id="rowCountFileCorrect" value=""/>
    <button class="btn" id="saveCorrectFileButton"><a href="#close"><fmt:message key="save"/></a></button>
    <a class="close" href="#close"></a>
</div>

<!-- Table Row -->

<!-- Форма №1 для модального окна фотки-->
<a href="#x" class="overlay" id="selectImageForm"></a>

<div class="popup">
    <p>
        <button type="button" id="selectPhotoButton"><fmt:message key="selectPhoto"/></button>
    </p>
    <button class="btn" id="loadPhotoButton"><a href="#close"><fmt:message key="save"/> </a></button>
    <a class="close" href="#close"></a>
</div>

<script type="text/javascript" src="js/jsForCreatePerson.js"></script>
</body>
</html>
